package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;

public class RefundTransactionRequest extends RiskOpsBaseRequest {
    private String transactionId;
    private String reason;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
