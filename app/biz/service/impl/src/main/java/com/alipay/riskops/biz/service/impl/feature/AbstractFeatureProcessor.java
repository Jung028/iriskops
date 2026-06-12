package com.alipay.riskops.biz.service.impl.feature;

import com.alipay.riskops.biz.service.impl.config.RiskRuleConfigProvider;
import com.alipay.riskops.common.service.integration.iaccount.AccountServiceClient;
import com.alipay.riskops.core.model.constants.RiskRuleConfigKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @date 12/6/2026 7:31 PM
 */
public abstract class AbstractFeatureProcessor {

    @Autowired
    protected AccountServiceClient accountServiceClient;

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Autowired
    protected RiskRuleConfigProvider ruleConfigProvider;

    /**
     * Returns the rule code this processor is governed by.
     */
    protected abstract String getRuleCode();

    /**
     * Returns true when the rule is disabled (i.e. processing should be skipped).
     * Fail-open: returns false when no config entry is found.
     */
    protected boolean isRuleDisabled() {
        return !ruleConfigProvider.isEnabled(getRuleCode());
    }

    /**
     * Returns config_json.windows[index].ttlSeconds, or defaultSeconds if not configured.
     */
    @SuppressWarnings("unchecked")
    protected long getWindowTtlSeconds(int index, long defaultSeconds) {
        Map<String, Object> cfg = ruleConfigProvider.getConfigMap(getRuleCode());
        Object windows = cfg.get(RiskRuleConfigKeys.WINDOWS);
        if (windows instanceof List) {
            List<Map<String, Object>> windowList = (List<Map<String, Object>>) windows;
            if (index < windowList.size()) {
                Object ttl = windowList.get(index).get(RiskRuleConfigKeys.TTL_SECONDS);
                if (ttl instanceof Number) {
                    return ((Number) ttl).longValue();
                }
            }
        }
        return defaultSeconds;
    }

    /**
     * Returns a typed value from config_json by key, falling back to defaultValue.
     */
    @SuppressWarnings("unchecked")
    protected <T> T getConfigValue(String key, T defaultValue) {
        Map<String, Object> cfg = ruleConfigProvider.getConfigMap(getRuleCode());
        Object value = cfg.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (T) value;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }
}
