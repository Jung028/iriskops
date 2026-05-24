package com.alipay.riskops.common.service.facade.item;

import java.math.BigDecimal;

public class TransactionDetail extends TransactionHistory {
    private String refundReason;
    private String refundedAt;

    public String getRefundReason() { return refundReason; }
    public void setRefundReason(String refundReason) { this.refundReason = refundReason; }

    public String getRefundedAt() { return refundedAt; }
    public void setRefundedAt(String refundedAt) { this.refundedAt = refundedAt; }
}
