package com.alipay.riskops.common.service.facade.request;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;

/**
 * @author adam
 * @date 19/5/2026 4:32 PM
 */
public class CreateRiskopsAccountRequest extends RiskopsBaseRequest {
    private String riskopsName;
    private String riskopsCategory;

    public String getRiskopsName() { return riskopsName; }
    public void setRiskopsName(String riskopsName) { this.riskopsName = riskopsName; }

    public String getRiskopsCategory() { return riskopsCategory; }
    public void setRiskopsCategory(String riskopsCategory) { this.riskopsCategory = riskopsCategory; }
}
