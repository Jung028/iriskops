package com.alipay.riskops.common.service.facade.item;

import java.math.BigDecimal;

public class TransactionHistory {
    private String transactionId;
    private String riskopsId;
    private BigDecimal amount;
    private String currency;
    private String direction;
    private String status;
    private String paymentMethod;
    private String createdAt;
    private boolean refundable;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getRiskOpsId() { return riskopsId; }
    public void setRiskOpsId(String riskopsId) { this.riskopsId = riskopsId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public boolean isRefundable() { return refundable; }
    public void setRefundable(boolean refundable) { this.refundable = refundable; }
}
