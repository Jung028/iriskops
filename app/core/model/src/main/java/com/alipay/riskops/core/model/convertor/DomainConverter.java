package com.alipay.riskops.core.model.convertor;

import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskRuleConfigDO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskScoreDO;
import com.alipay.riskops.common.service.facade.item.RiskOpsInfoItem;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.core.model.domain.RiskDecision;
import com.alipay.riskops.core.model.domain.RiskOpsInfo;
import com.alipay.riskops.core.model.domain.RiskRuleConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author adam
 * @date 25/4/2026 6:31 PM
 */
public class DomainConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static RiskDecisionDO convertToDO(RiskDecision riskDecision) {
        if (riskDecision == null) {
            return null;
        }
        RiskDecisionDO riskDecisionDO = new RiskDecisionDO();
        riskDecisionDO.setId(riskDecision.getId());
        riskDecisionDO.setRiskScoreId(riskDecision.getRiskScoreId());
        riskDecisionDO.setDecidedAt(riskDecision.getDecidedAt());
        riskDecisionDO.setOutcome(riskDecision.getOutcome());
        riskDecisionDO.setThresholdApplied(riskDecision.getThresholdApplied());
        riskDecisionDO.setTransactionId(riskDecision.getTransactionId());
        return riskDecisionDO;
    }

    public static RiskDecisionDO convertToDO(RiskDecisionResult result) {
        if (result == null) {
            return null;
        }
        RiskDecisionDO riskDecisionDO = new RiskDecisionDO();
        riskDecisionDO.setId(result.getRiskDecisionId());
        riskDecisionDO.setRiskScoreId(result.getRiskScoreId());
        riskDecisionDO.setTransactionId(result.getBusinessId());
        riskDecisionDO.setOutcome(result.getOutcome());
        riskDecisionDO.setReason(result.getReason());
        riskDecisionDO.setThresholdApplied(result.getThresholdApplied());
        riskDecisionDO.setDecidedAt(result.getDecidedAt());
        return riskDecisionDO;
    }

    public static RiskScoreDO convertToDO(RiskScoreResult result) {
        if (result == null) {
            return null;
        }
        RiskScoreDO riskScoreDO = new RiskScoreDO();
        riskScoreDO.setId(result.getRiskScoreId());
        riskScoreDO.setTransactionId(result.getBusinessId());
        riskScoreDO.setScore(result.getFinalScore() != null ? result.getFinalScore().shortValue() : null);
        riskScoreDO.setSignals(serializeSignals(result));
        riskScoreDO.setCalculatedAt(result.getCalculatedAt());
        return riskScoreDO;
    }

    public static RiskRuleConfigDO convertToDO(RiskRuleConfig riskRuleConfig) {
        if (riskRuleConfig == null) {
            return null;
        }
        RiskRuleConfigDO riskRuleConfigDO = new RiskRuleConfigDO();
        riskRuleConfigDO.setId(riskRuleConfig.getId());
        riskRuleConfigDO.setRuleCode(riskRuleConfig.getRuleCode());
        riskRuleConfigDO.setSignalType(riskRuleConfig.getSignalType());
        riskRuleConfigDO.setScore(riskRuleConfig.getScore());
        riskRuleConfigDO.setWeight(riskRuleConfig.getWeight());
        riskRuleConfigDO.setEnabled(riskRuleConfig.getEnabled());
        riskRuleConfigDO.setConfigJson(riskRuleConfig.getConfigJson());
        riskRuleConfigDO.setDescription(riskRuleConfig.getDescription());
        riskRuleConfigDO.setCreatedAt(riskRuleConfig.getCreatedAt());
        riskRuleConfigDO.setUpdatedAt(riskRuleConfig.getUpdatedAt());
        return riskRuleConfigDO;
    }

    public static RiskRuleConfig convertFromDO(RiskRuleConfigDO riskRuleConfigDO) {
        if (riskRuleConfigDO == null) {
            return null;
        }
        RiskRuleConfig riskRuleConfig = new RiskRuleConfig();
        riskRuleConfig.setId(riskRuleConfigDO.getId());
        riskRuleConfig.setRuleCode(riskRuleConfigDO.getRuleCode());
        riskRuleConfig.setSignalType(riskRuleConfigDO.getSignalType());
        riskRuleConfig.setScore(riskRuleConfigDO.getScore());
        riskRuleConfig.setWeight(riskRuleConfigDO.getWeight());
        riskRuleConfig.setEnabled(riskRuleConfigDO.getEnabled());
        riskRuleConfig.setConfigJson(riskRuleConfigDO.getConfigJson());
        riskRuleConfig.setDescription(riskRuleConfigDO.getDescription());
        riskRuleConfig.setCreatedAt(riskRuleConfigDO.getCreatedAt());
        riskRuleConfig.setUpdatedAt(riskRuleConfigDO.getUpdatedAt());
        return riskRuleConfig;
    }

    private static String serializeSignals(RiskScoreResult result) {
        try {
            return OBJECT_MAPPER.writeValueAsString(
                    result.getSignals() != null ? result.getSignals() : java.util.Collections.emptyList());
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

}