package com.alipay.riskops.biz.service.impl.policy.rule;

/**
 * @author adam
 * @date 28/5/2026 9:32 PM
 */
public class RiskDecisionRule {

    private Integer minScore;
    private Integer maxScore;
    private String outcome;
    private String reason;

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
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
}