package com.alipay.riskops.common.service.facade.baseresult;

import java.io.Serializable;

public class RiskOpsBaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;
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
