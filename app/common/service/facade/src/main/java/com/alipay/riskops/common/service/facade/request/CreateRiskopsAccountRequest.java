package com.alipay.riskops.common.service.facade.request;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;

/**
 * @author adam
 * @date 19/5/2026 4:32 PM
 */
public class CreateRiskOpsAccountRequest extends RiskOpsBaseRequest {
    private String riskopsName;
    private String riskopsCategory;

    public String getRiskOpsName() { return riskopsName; }
    public void setRiskOpsName(String riskopsName) { this.riskopsName = riskopsName; }

    public String getRiskOpsCategory() { return riskopsCategory; }
    public void setRiskOpsCategory(String riskopsCategory) { this.riskopsCategory = riskopsCategory; }
}
