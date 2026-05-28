package com.alipay.riskops.core.service;

import com.alipay.riskops.core.model.domain.RiskOpsInfo;

/**
 * @author adam
 * @date 25/4/2026 5:19 PM
 */
public interface RiskOpsInfoRepository {
    RiskOpsInfo queryRiskOpsInfo(String riskopsId);
    String createRiskOpsInfo(RiskOpsInfo riskopsInfo);
    void updateRiskOpsInfo(RiskOpsInfo riskopsInfo);
}
