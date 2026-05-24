package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;

/**
 * @author adam
 * @date 25/4/2026 6:13 PM
 */
public class QueryRiskopsInfoRequest extends RiskopsBaseRequest {
    private String riskopsId;

    public String getRiskopsId() {
        return riskopsId;
    }

    public void setRiskopsId(String riskopsId) {
        this.riskopsId = riskopsId;
    }
}