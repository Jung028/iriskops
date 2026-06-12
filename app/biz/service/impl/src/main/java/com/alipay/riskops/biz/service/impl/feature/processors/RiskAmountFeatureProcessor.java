package com.alipay.riskops.biz.service.impl.feature.processors;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.AbstractFeatureProcessor;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureProcessor;
import com.alipay.riskops.core.model.enums.RiskRuleConfigCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Tracks rolling outbound amount totals (stored as integer cents) per account
 * over configurable 1-hour and 24-hour windows. Only counts successful transactions.
 *
 * @author adam
 * @date 12/6/2026 7:29 PM
 */
@Component
public class RiskAmountFeatureProcessor extends AbstractFeatureProcessor implements RiskFeatureProcessor {

    @Override
    protected String getRuleCode() {
        return RiskRuleConfigCode.LARGE_AMOUNT_CHECK.getCode();
    }

    @Override
    public void process(EcTransactionEvent event) {
        if (isRuleDisabled()) {
            return;
        }

        if (event.getAmount() == null || event.getTxnEventType() == null) {
            return;
        }

        if (!"SUCCESS".equalsIgnoreCase(event.getTxnStatus())) {
            return;
        }

        String accountNo = event.getPayerAccountNo();
        long amountCents = event.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .longValue();

        String key1h = "risk:amount:" + accountNo + ":total_cents:1h";
        String key24h = "risk:amount:" + accountNo + ":total_cents:24h";

        long ttl1h = getWindowTtlSeconds(0, 3600L);
        long ttl24h = getWindowTtlSeconds(1, 86400L);

        redisTemplate.opsForValue().increment(key1h, amountCents);
        redisTemplate.opsForValue().increment(key24h, amountCents);

        redisTemplate.expire(key1h, ttl1h, TimeUnit.SECONDS);
        redisTemplate.expire(key24h, ttl24h, TimeUnit.SECONDS);
    }
}
