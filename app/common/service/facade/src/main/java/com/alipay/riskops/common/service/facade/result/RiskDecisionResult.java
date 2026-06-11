package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;
import com.alipay.riskops.common.service.facade.enums.RiskDecisionOutcome;
import com.alipay.riskops.common.service.facade.enums.RiskOutcome;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;

import java.util.Date;
import java.util.List;

/**
 * @author adam
 * @date 27/5/2026 9:33 PM
 */
public class RiskDecisionResult extends RiskOpsBaseResult {

    private static final long serialVersionUID = 1L;

    /**
     * risk_decision table id.
     */
    private String riskDecisionId;

    /**
     * risk_score table id.
     */
    private String riskScoreId;

    /**
     * Business record id.
     * Example: txnId, topUpId, withdrawalId.
     */
    private String businessId;

    /**
     * Business type.
     * Example: TRANSFER, TOP_UP, WITHDRAWAL, LOGIN, KYC.
     */
    private String businessType;

    /**
     * Actor user id.
     */
    private String userId;

    /**
     * Main account checked by risk.
     */
    private String accountNo;

    /**
     * Final score from RiskScoreEngine.
     * Range: 0-100.
     */
    private Integer finalScore;

    /**
     * APPROVE, STEP_UP, BLOCK, REVIEW.
     */
    private String outcome;

    /**
     * Human-readable reason.
     */
    private String reason;

    /**
     * Example:
     * 0-49, 50-79, 80-100, BLOCKING_SIGNAL, FALLBACK.
     */
    private String thresholdApplied;

    /**
     * Only used when outcome = STEP_UP.
     * Frontend can use this to continue verification.
     */
    private String riskSessionId;

    /**
     * Explainable breakdown from strategies.
     */
    private List<RiskSignal> signals;

    /**
     * When decision was made.
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

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
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