package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;

public class GenerateStaticQrRequest extends RiskopsBaseRequest {
    private String currency;
    private String label;

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
