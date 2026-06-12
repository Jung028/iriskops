package com.alipay.riskops.biz.service.impl.feature.processors;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.AbstractFeatureProcessor;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureProcessor;
import com.alipay.riskops.core.model.constants.RiskRuleConfigKeys;
import com.alipay.riskops.core.model.enums.RiskRuleConfigCode;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Tracks consecutive failed transactions per payer within a configurable window.
 * A successful transaction resets the counter when resetOnSuccess is true (default).
 * Detects card-testing and brute-force patterns.
 *
 * @author adam
 * @date 12/6/2026 7:29 PM
 */
@Component
public class RiskFailedTransactionFeatureProcessor extends AbstractFeatureProcessor implements RiskFeatureProcessor {

    @Override
    protected String getRuleCode() {
        return RiskRuleConfigCode.FAILED_TXN_CHECK.getCode();
    }

    @Override
    public void process(EcTransactionEvent event) {
        if (isRuleDisabled()) {
            return;
        }

        String txnStatus = event.getTxnStatus();
        if (txnStatus == null) {
            return;
        }

        String accountNo = event.getPayerAccountNo();
        String failedCountKey = "risk:failed:" + accountNo + ":count:15m";

        int ttlMinutes = getConfigValue(RiskRuleConfigKeys.TTL_MINUTES, 15);
        boolean resetOnSuccess = getConfigValue(RiskRuleConfigKeys.RESET_ON_SUCCESS, Boolean.TRUE);

        if (resetOnSuccess && "SUCCESS".equalsIgnoreCase(txnStatus)) {
            redisTemplate.delete(failedCountKey);
        } else if (!"SUCCESS".equalsIgnoreCase(txnStatus)) {
            redisTemplate.opsForValue().increment(failedCountKey);
            redisTemplate.expire(failedCountKey, ttlMinutes, TimeUnit.MINUTES);
        }
    }
}
