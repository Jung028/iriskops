package com.alipay.riskops.core.service;

import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.core.model.domain.RiskDecision;

/**
 * @author adam
 * @date 27/5/2026 11:11 PM
 */
public interface RiskDecisionRepository{

    void insertRiskDecision(RiskDecision riskDecision);
}