package com.alipay.riskops.common.service.facade.item;

import java.math.BigDecimal;

public class QrCodeItem {
    private String qrId;
    private String riskopsId;
    private String type;
    private BigDecimal amount;
    private String currency;
    private String createdAt;
    private String expiresAt;
    private String label;
    private int usageCount;

    public String getQrId() { return qrId; }
    public void setQrId(String qrId) { this.qrId = qrId; }

    public String getRiskopsId() { return riskopsId; }
    public void setRiskopsId(String riskopsId) { this.riskopsId = riskopsId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getExpiresAt() { return expiresAt; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public int getUsageCount() { return usageCount; }
    public void setUsageCount(int usageCount) { this.usageCount = usageCount; }
}
