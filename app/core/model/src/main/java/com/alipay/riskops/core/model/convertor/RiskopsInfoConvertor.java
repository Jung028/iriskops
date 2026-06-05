package com.alipay.riskops.core.model.convertor;

import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskScoreDO;
import com.alipay.riskops.common.service.facade.item.RiskOpsInfoItem;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.core.model.domain.RiskDecision;
import com.alipay.riskops.core.model.domain.RiskOpsInfo;

/**
 * @author adam
 * @date 25/4/2026 6:31 PM
 */
public class RiskOpsInfoConvertor {

    public static RiskOpsInfoItem convertToItem(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskOpsInfoItem riskopsInfoItem = new RiskOpsInfoItem();
        riskopsInfoItem.setRiskOpsId(riskopsInfo.getRiskOpsId());
        riskopsInfoItem.setRiskOpsName(riskopsInfo.getRiskOpsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskOpsCategory(riskopsInfo.getRiskOpsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

    public static RiskOpsInfoItem convertToDomain(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskOpsInfoItem riskopsInfoItem = new RiskOpsInfoItem();
        riskopsInfoItem.setRiskOpsId(riskopsInfo.getRiskOpsId());
        riskopsInfoItem.setRiskOpsName(riskopsInfo.getRiskOpsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskOpsCategory(riskopsInfo.getRiskOpsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

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
        riskScoreDO.setCalculatedAt(result.getCalculatedAt());
        return riskScoreDO;
    }

}