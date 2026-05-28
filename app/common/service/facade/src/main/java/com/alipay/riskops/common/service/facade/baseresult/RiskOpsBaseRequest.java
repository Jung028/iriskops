package com.alipay.riskops.common.service.facade.baseresult;

public class RiskOpsBaseRequest {
    private String operatorId;
    private String operatorName;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
