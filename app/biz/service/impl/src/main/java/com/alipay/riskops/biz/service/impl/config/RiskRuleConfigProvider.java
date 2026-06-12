package com.alipay.riskops.biz.service.impl.config;

import com.alipay.riskops.core.model.domain.RiskRuleConfig;
import com.alipay.riskops.core.service.RiskRuleConfigRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Cache-aside provider for risk rule configuration.
 *
 * Lookup order: Redis → DB → write Redis → return.
 * Cached entries expire after 30 minutes, so DB changes propagate without restart.
 * Fail-open: if neither Redis nor DB has a rule, the processor runs as if enabled.
 *
 * @author adam
 * @date 12/6/2026
 */
@Component
public class RiskRuleConfigProvider {

    private static final Logger log = LoggerFactory.getLogger(RiskRuleConfigProvider.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String REDIS_KEY_PREFIX = "risk:rule_config:";
    private static final long CACHE_TTL_MINUTES = 30;

    @Autowired
    private RiskRuleConfigRepository riskRuleConfigRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Returns true when the rule is enabled (or when not found — fail-open).
     */
    public boolean isEnabled(String ruleCode) {
        CachedConfig cached = getCachedConfig(ruleCode);
        return cached == null || cached.enabled;
    }

    /**
     * Returns the parsed configJson map for the rule, or an empty map if not found.
     */
    public Map<String, Object> getConfigMap(String ruleCode) {
        CachedConfig cached = getCachedConfig(ruleCode);
        if (cached == null || cached.configMap == null) {
            return Collections.emptyMap();
        }
        return cached.configMap;
    }

    /**
     * Cache-aside core: Redis → DB → write Redis → return.
     */
    private CachedConfig getCachedConfig(String ruleCode) {
        String redisKey = REDIS_KEY_PREFIX + ruleCode;

        // 1. Check Redis
        String cachedValue = redisTemplate.opsForValue().get(redisKey);
        if (cachedValue != null) {
            try {
                return MAPPER.readValue(cachedValue, CachedConfig.class);
            } catch (Exception e) {
                log.warn("Failed to deserialize cached config for {}, falling back to DB: {}", ruleCode, e.getMessage());
            }
        }

        // 2. Query DB
        RiskRuleConfig config;
        try {
            config = riskRuleConfigRepository.findByRuleCode(ruleCode);
        } catch (Exception e) {
            log.warn("Failed to fetch rule config for {} from DB: {}", ruleCode, e.getMessage());
            return null;
        }

        if (config == null) {
            return null;
        }

        // 3. Build serializable DTO
        CachedConfig cached = buildCachedConfig(config);

        // 4. Write to Redis
        try {
            String json = MAPPER.writeValueAsString(cached);
            redisTemplate.opsForValue().set(redisKey, json, CACHE_TTL_MINUTES, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.warn("Failed to write config for {} to Redis: {}", ruleCode, e.getMessage());
        }

        return cached;
    }

    private CachedConfig buildCachedConfig(RiskRuleConfig config) {
        CachedConfig cached = new CachedConfig();
        cached.enabled = Boolean.TRUE.equals(config.getEnabled());
        cached.score = config.getScore() != null ? config.getScore() : 0;
        cached.weight = config.getWeight() != null ? config.getWeight() : 0;
        cached.signalType = config.getSignalType();

        Object rawJson = config.getConfigJson();
        if (rawJson != null) {
            try {
                String jsonStr = extractJsonString(rawJson);
                if (jsonStr != null) {
                    cached.configMap = MAPPER.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
                }
            } catch (Exception e) {
                log.warn("Failed to parse configJson for {}: {}", config.getRuleCode(), e.getMessage());
                cached.configMap = Collections.emptyMap();
            }
        }
        return cached;
    }

    /**
     * Extracts the JSON string from either a plain String or a PostgreSQL PGobject.
     * Uses reflection to avoid a hard compile-time dependency on the JDBC driver.
     */
    private String extractJsonString(Object raw) {
        if (raw instanceof String) {
            return (String) raw;
        }
        try {
            return (String) raw.getClass().getMethod("getValue").invoke(raw);
        } catch (Exception e) {
            return raw.toString();
        }
    }

    /**
     * Serializable DTO stored as a JSON string in Redis.
     */
    public static class CachedConfig {
        public boolean enabled;
        public int score;
        public int weight;
        public String signalType;
        public Map<String, Object> configMap;
    }
}
