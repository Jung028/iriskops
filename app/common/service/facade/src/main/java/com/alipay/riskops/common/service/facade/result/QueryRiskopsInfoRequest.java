package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;

/**
 * @author adam
 * @date 25/4/2026 6:13 PM
 */
public class QueryRiskOpsInfoRequest extends RiskOpsBaseRequest {
    private String riskopsId;

    public String getRiskOpsId() {
        return riskopsId;
    }

    public void setRiskOpsId(String riskopsId) {
        this.riskopsId = riskopsId;
    }
}