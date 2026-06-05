package com.alipay.riskops.biz.service.impl.decision;

import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicyProvider;
import com.alipay.riskops.biz.service.impl.policy.rule.RiskDecisionRule;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author adam
 * @date 28/5/2026 9:23 PM
 */
@Service
public class RiskDecisionServiceImpl implements RiskDecisionService {

    @Autowired
    private RiskDecisionPolicyProvider riskDecisionPolicyProvider;

    @Override
    public RiskDecisionResult decide(RiskScoreResult riskScoreResult) {
        if (hasBlockingSignal(riskScoreResult.getSignals())) {
            return decideResult(
                    riskScoreResult,
                    "BLOCK",
                    "BLOCKING_SIGNAL",
                    "Blocked due to critical risk signal",
                    null
            );
        }
        RiskDecisionPolicy policy = riskDecisionPolicyProvider.getPolicy(riskScoreResult.getBusinessType());
        RiskDecisionRule matchedRule = policy.getRules().stream().filter(rule ->
                        riskScoreResult.getFinalScore() >= rule.getMinScore() && riskScoreResult.getFinalScore() < rule.getMaxScore())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching risk decision rule found"));
        String riskSessionId = null;

        if ("STEP_UP".equals(matchedRule.getOutcome())) {
            riskSessionId = UUID.randomUUID().toString();
        }

        return decideResult(
                riskScoreResult,
                matchedRule.getOutcome(),
                matchedRule.getMinScore() + "-" + matchedRule.getMaxScore(),
                matchedRule.getReason(),
                null
                );


    }

    private RiskDecisionResult decideResult(RiskScoreResult riskScoreResult, String block, String blockingSignal, String s, Object o) {
        RiskDecisionResult riskDecisionResult = new RiskDecisionResult();
        riskDecisionResult.setBusinessType(riskScoreResult.getBusinessType());
        riskDecisionResult.setOutcome(block);
        riskDecisionResult.setReason(s);
        riskDecisionResult.setFinalScore(riskScoreResult.getFinalScore());
        riskDecisionResult.setDecidedAt(new Date());
        return riskDecisionResult;
    }

    private boolean hasBlockingSignal(List<RiskSignal> signals) {
        return false;
    }



}