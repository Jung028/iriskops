package com.alipay.riskops.core.model.context;

import com.alipay.riskops.core.model.enums.RiskopsActionEnum;

import java.util.Date;

public class RiskopsContext {

    private static final long serialVersionUID = 1L;

    private Date time;
    private RiskopsActionEnum action;
    private String operatorId;
    private String operatorName;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public RiskopsActionEnum getAction() {
        return action;
    }

    public void setAction(RiskopsActionEnum action) {
        this.action = action;
    }

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

    public RiskopsContext(RiskopsActionEnum action, Date time, String operatorId, String operatorName) {
        this.action = action;
        this.time = time;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }
}
