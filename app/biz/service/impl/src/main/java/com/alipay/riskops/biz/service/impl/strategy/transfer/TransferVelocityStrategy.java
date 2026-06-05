package com.alipay.riskops.biz.service.impl.strategy.transfer;

import com.alipay.riskops.biz.service.impl.strategy.RiskStrategy;
import com.alipay.riskops.common.service.facade.enums.RiskRuleCode;
import com.alipay.riskops.common.service.facade.enums.RiskSeverity;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.enums.RiskSignalType;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @author adam
 * @date 25/5/2026 3:42 PM
 */
@Component
public class TransferVelocityStrategy implements RiskStrategy {

    @Override
    public boolean supports(RiskDecisionRequest request) {
        return "TRANSFER".equalsIgnoreCase(request.getBusinessType());
    }

    @Override
    public RiskSignal evaluate(RiskDecisionRequest request) {
        String payerAccountNo = request.getPayerAccountNo();

        // 1 minutes, how many transfers
        String key1m = "risk:velocity:" + payerAccountNo + ":TRANSFER:1m";
        // 5 minutes, how many transfers
        String key5m = "risk:velocity:" + payerAccountNo + ":TRANSFER:5m";

        int count1m = getInt(key1m);
        int count5m = getInt(key5m);

        int score;
        String severity;
        String reason;

        if (count1m >= 5 || count5m >= 10) {
            score = 85;
            severity = "HIGH";
            reason = "High transfer velocity detected";
        } else if (count1m >= 3 || count5m >= 6) {
            score = 80;
            severity = "MEDIUM";
            reason = "Medium transfer velocity detected";
        } else {
            score = 10;
            severity = "LOW";
            reason = "Normal transfer velocity detected";
        }

        // then put them in evidence hashmap
        Map<String, Object> evidence = new HashMap<>();
        evidence.put("count1m", count1m);
        evidence.put("count5m", count5m);
        evidence.put("threshold1m", 5);
        evidence.put("threshold5m", 10);

        // add the evidence to the risk signal and return it
        RiskSignal riskSignal = new RiskSignal();
        riskSignal.setEvidence(evidence);
        riskSignal.setScore(score);
        riskSignal.setSeverity(RiskSeverity.valueOf(severity));
        riskSignal.setReason(reason);
        riskSignal.setSignalType(RiskSignalType.TRANSFER_VELOCITY);
        riskSignal.setRuleCode(RiskRuleCode.TRANSFER_VELOCITY_CHECK.getCode());
        riskSignal.setWeight(25);
        riskSignal.setBlockingSignal(false);

        return riskSignal;
    }

    private int getInt(String key) {
        return 0;
    }
}
