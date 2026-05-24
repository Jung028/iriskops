package com.alipay.riskops.core.service;

import com.alipay.riskops.core.model.domain.RiskopsInfo;

/**
 * @author adam
 * @date 25/4/2026 5:19 PM
 */
public interface RiskopsInfoRepository {
    RiskopsInfo queryRiskopsInfo(String riskopsId);
    String createRiskopsInfo(RiskopsInfo riskopsInfo);
    void updateRiskopsInfo(RiskopsInfo riskopsInfo);
}
