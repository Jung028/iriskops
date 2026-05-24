package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;

import java.math.BigDecimal;

public class GenerateDynamicQrRequest extends RiskopsBaseRequest {
    private BigDecimal amount;
    private String currency;
    private String label;
    private Integer expiresInMinutes;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Integer getExpiresInMinutes() { return expiresInMinutes; }
    public void setExpiresInMinutes(Integer expiresInMinutes) { this.expiresInMinutes = expiresInMinutes; }
}
