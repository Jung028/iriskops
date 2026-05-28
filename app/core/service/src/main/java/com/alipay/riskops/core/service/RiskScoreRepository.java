package com.alipay.riskops.core.service;

import com.alipay.riskops.common.service.facade.result.RiskScoreResult;

/**
 * @author adam
 * @date 27/5/2026 11:11 PM
 */
public interface RiskScoreRepository {

    void insertRiskScore(RiskScoreResult riskScoreResult);
}