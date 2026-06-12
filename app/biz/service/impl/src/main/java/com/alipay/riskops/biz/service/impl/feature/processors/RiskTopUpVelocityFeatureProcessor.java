package com.alipay.riskops.biz.service.impl.feature.processors;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.AbstractFeatureProcessor;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureProcessor;
import com.alipay.riskops.core.model.enums.RiskRuleConfigCode;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author adam
 * @date 12/6/2026 7:29 PM
 */
@Component
public class RiskTopUpVelocityFeatureProcessor extends AbstractFeatureProcessor implements RiskFeatureProcessor {

    @Override
    protected String getRuleCode() {
        return RiskRuleConfigCode.TOP_UP_VELOCITY_CHECK.getCode();
    }

    @Override
    public void process(EcTransactionEvent event) {
        if (isRuleDisabled()) {
            return;
        }

        if (!"TOP_UP".equalsIgnoreCase(event.getTxnEventType())) {
            return;
        }

        String accountNo = event.getPayerAccountNo();
        String key1m = "risk:velocity:" + accountNo + ":TOP_UP:1m";
        String key5m = "risk:velocity:" + accountNo + ":TOP_UP:5m";

        long ttl1m = getWindowTtlSeconds(0, 60L);
        long ttl5m = getWindowTtlSeconds(1, 300L);

        redisTemplate.opsForValue().increment(key1m);
        redisTemplate.opsForValue().increment(key5m);

        redisTemplate.expire(key1m, ttl1m, TimeUnit.SECONDS);
        redisTemplate.expire(key5m, ttl5m, TimeUnit.SECONDS);
    }
}
