package com.alipay.riskops.biz.service.impl.strategy.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author adam
 * @date 12/6/2026 1:39 PM
 */
public abstract class AbstractRiskBehaviourStrategy {

    @Autowired
    protected RedisTemplate<String, String> redisTemplate;

    protected int getInt(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) return 0;

        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}