package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;
import com.alipay.riskops.common.service.facade.enums.RiskOutcome;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;

import java.util.Date;
import java.util.List;

/**
 * @author adam
 * @date 27/5/2026 9:33 PM
 */
public class RiskDecisionResult extends RiskOpsBaseResult {

    /**
     * risk decision id
     */
    private String riskDecisionId;

    /**
     * risk score id
     */
    private String riskScoreId;

    /**
     * business id
     */
    private String businessId;

    /**
     * business type
     */
    private String businessType;

    /**
     * user id
     */
    private String userId;

    /**
     * account no
     */
    private String accountNo;

    /**
     * final score
     */
    private Integer finalScore;

    /**
     * APPROVE, STEP_UP, BLOCK, REVIEW.
     */
    private RiskOutcome outcome;

    /**
     * reason
     */
    private String reason;

    /**
     * Example:
     * 0-49, 50-79, 80-100.
     */
    private String thresholdApplied;

    /**
     * Only used when outcome = STEP_UP.
     */
    private String riskSessionId;

    /**
     * Explainable risk breakdown.
     */
    private List<RiskSignal> signals;

    /**
     * decided at time
     */
    private Date decidedAt;

    public String getRiskDecisionId() {
        return riskDecisionId;
    }

    public void setRiskDecisionId(String riskDecisionId) {
        this.riskDecisionId = riskDecisionId;
    }

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

    public RiskOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(RiskOutcome outcome) {
        this.outcome = outcome;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getThresholdApplied() {
        return thresholdApplied;
    }

    public void setThresholdApplied(String thresholdApplied) {
        this.thresholdApplied = thresholdApplied;
    }

    public String getRiskSessionId() {
        return riskSessionId;
    }

    public void setRiskSessionId(String riskSessionId) {
        this.riskSessionId = riskSessionId;
    }

    public List<RiskSignal> getSignals() {
        return signals;
    }

    public void setSignals(List<RiskSignal> signals) {
        this.signals = signals;
    }

    public Date getDecidedAt() {
        return decidedAt;
    }

    public void setDecidedAt(Date decidedAt) {
        this.decidedAt = decidedAt;
    }
}