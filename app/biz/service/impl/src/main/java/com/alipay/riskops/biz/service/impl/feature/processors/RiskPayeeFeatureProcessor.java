package com.alipay.riskops.biz.service.impl.feature.processors;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.AbstractFeatureProcessor;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureProcessor;
import com.alipay.riskops.core.model.constants.RiskRuleConfigKeys;
import com.alipay.riskops.core.model.enums.RiskRuleConfigCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Tracks payee relationship novelty per payer.
 * Increments a configurable-window new-payee counter the first time a payer sends to a given payee,
 * and maintains a rolling set of known payees per payer account.
 *
 * @author adam
 * @date 12/6/2026 7:29 PM
 */
@Component
public class RiskPayeeFeatureProcessor extends AbstractFeatureProcessor implements RiskFeatureProcessor {

    @Override
    protected String getRuleCode() {
        return RiskRuleConfigCode.NEW_PAYEE_CHECK.getCode();
    }

    @Override
    public void process(EcTransactionEvent event) {
        if (isRuleDisabled()) {
            return;
        }

        if (!"TRANSFER".equalsIgnoreCase(event.getTxnEventType())) {
            return;
        }

        String payerAccountNo = event.getPayerAccountNo();
        String payeeAccountNo = event.getPayeeAccountNo();

        if (!StringUtils.hasText(payeeAccountNo)) {
            return;
        }

        String knownPayeesKey = "risk:payee:" + payerAccountNo + ":known";
        String newPayeeCountKey = "risk:payee:" + payerAccountNo + ":new_count:24h";

        int newCountTtlHours = getConfigValue(RiskRuleConfigKeys.NEW_COUNT_TTL_HOURS, 24);
        int knownPayeesTtlDays = getConfigValue(RiskRuleConfigKeys.KNOWN_PAYEES_TTL_DAYS, 30);

        Boolean alreadyKnown = redisTemplate.opsForSet().isMember(knownPayeesKey, payeeAccountNo);

        if (!Boolean.TRUE.equals(alreadyKnown)) {
            redisTemplate.opsForValue().increment(newPayeeCountKey);
            redisTemplate.expire(newPayeeCountKey, newCountTtlHours, TimeUnit.HOURS);

            redisTemplate.opsForSet().add(knownPayeesKey, payeeAccountNo);
            redisTemplate.expire(knownPayeesKey, knownPayeesTtlDays, TimeUnit.DAYS);
        }
    }
}
