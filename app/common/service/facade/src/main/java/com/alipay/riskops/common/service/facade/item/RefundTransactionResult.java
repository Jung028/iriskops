package com.alipay.riskops.common.service.facade.item;

public class RefundTransactionResult {
    private String transactionId;
    private String refundStatus;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getRefundStatus() { return refundStatus; }
    public void setRefundStatus(String refundStatus) { this.refundStatus = refundStatus; }
}
