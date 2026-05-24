package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;

public class QueryTransactionDetailRequest extends RiskopsBaseRequest {
    private String transactionId;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
