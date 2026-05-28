package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.enums.RiskSignal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author adam
 * @date 27/5/2026 11:00 PM
 */
public class RiskScoreResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String riskScoreId;

    private String businessId;
    private String businessType;

    private String userId;
    private String accountNo;

    private Integer finalScore;

    private List<RiskSignal> signals;

    private Date calculatedAt;

    public String getRiskScoreId() {
        return riskScoreId;
    }

    public void setRiskScoreId(String riskScoreId) {
        this.riskScoreId = riskScoreId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public List<RiskSignal> getSignals() {
        return signals;
    }

    public void setSignals(List<RiskSignal> signals) {
        this.signals = signals;
    }

    public Date getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(Date calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}