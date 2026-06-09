package com.alipay.riskops.biz.service.impl.decision;

import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicyProvider;
import com.alipay.riskops.biz.service.impl.policy.rule.RiskDecisionRule;
import com.alipay.riskops.common.dal.auto.custom.RiskDecisionDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private RiskDecisionDAO riskDecisionDAO;

    @Override
    public RiskDecisionResult decide(RiskScoreResult riskScoreResult) {
        if (hasBlockingSignal(riskScoreResult.getSignals())) {
            RiskDecisionResult result = decideResult(
                    riskScoreResult,
                    "BLOCK",
                    "Blocked due to critical risk signal",
                    "BLOCKING_SIGNAL",
                    null
            );
            saveDecision(riskScoreResult, result);
            return result;
        }
        RiskDecisionPolicy policy = riskDecisionPolicyProvider.getPolicy(riskScoreResult.getBusinessType());
        
        if (policy == null || CollectionUtils.isEmpty(policy.getRules())) {
            throw new RuntimeException("No risk decision rules found for policy: " + riskScoreResult.getBusinessType());
        }

        RiskDecisionRule matchedRule = policy.getRules().stream().filter(rule ->
                        riskScoreResult.getFinalScore() >= rule.getMinScore() && riskScoreResult.getFinalScore() < rule.getMaxScore())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching risk decision rule found for score: " + riskScoreResult.getFinalScore()));

        String riskSessionId = null;
        if ("STEP_UP".equals(matchedRule.getOutcome())) {
            riskSessionId = UUID.randomUUID().toString();
        }

        RiskDecisionResult result = decideResult(
                riskScoreResult,
                matchedRule.getOutcome(),
                matchedRule.getReason(),
                matchedRule.getMinScore() + "-" + matchedRule.getMaxScore(),
                riskSessionId
        );
        saveDecision(riskScoreResult, result);
        return result;
    }

    private void saveDecision(RiskScoreResult riskScoreResult, RiskDecisionResult result) {
        RiskDecisionDO riskDecisionDO = new RiskDecisionDO();
        String id = UUID.randomUUID().toString();
        riskDecisionDO.setId(id);
        riskDecisionDO.setTransactionId(riskScoreResult.getBusinessId());
        riskDecisionDO.setRiskScoreId(riskScoreResult.getRiskScoreId());
        riskDecisionDO.setOutcome(result.getOutcome());
        riskDecisionDO.setReason(result.getReason());
        riskDecisionDO.setThresholdApplied(result.getThresholdApplied());
        riskDecisionDO.setDecidedAt(new Date());
        riskDecisionDAO.insertRiskDecision(riskDecisionDO);
        result.setRiskDecisionId(id);
    }

    private RiskDecisionResult decideResult(RiskScoreResult riskScoreResult,
                                            String outcome,
                                            String reason,
                                            String threshold,
                                            String riskSessionId) {
        RiskDecisionResult riskDecisionResult = new RiskDecisionResult();
        riskDecisionResult.setBusinessId(riskScoreResult.getBusinessId());
        riskDecisionResult.setBusinessType(riskScoreResult.getBusinessType());
        riskDecisionResult.setUserId(riskScoreResult.getUserId());
        riskDecisionResult.setAccountNo(riskScoreResult.getAccountNo());
        riskDecisionResult.setRiskScoreId(riskScoreResult.getRiskScoreId());
        riskDecisionResult.setOutcome(outcome);
        riskDecisionResult.setReason(reason);
        riskDecisionResult.setThresholdApplied(threshold);
        riskDecisionResult.setFinalScore(riskScoreResult.getFinalScore());
        riskDecisionResult.setRiskSessionId(riskSessionId);
        riskDecisionResult.setSignals(riskScoreResult.getSignals());
        riskDecisionResult.setDecidedAt(new Date());
        return riskDecisionResult;
    }

    private boolean hasBlockingSignal(List<RiskSignal> signals) {
        if (signals == null) {
            return false;
        }
        return signals.stream().anyMatch(signal -> Boolean.TRUE.equals(signal.getBlockingSignal()));
    }
}
