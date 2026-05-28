package com.alipay.riskops.common.service.facade.enums;

import java.io.Serializable;
import java.util.Map;

/**
 * @author adam
 * @date 27/5/2026 10:06 PM
 */
public class RiskSignal implements Serializable {

    private static final long serialVersionUID = 1L;

    private RiskSignalType signalType;

    private Integer score;

    private RiskSeverity severity;

    private String ruleCode;

    private String reason;

    private Map<String, Object> evidence;

    private Integer weight;

    private Boolean blockingSignal;

    public RiskSignalType getSignalType() {
        return signalType;
    }

    public void setSignalType(RiskSignalType signalType) {
        this.signalType = signalType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public RiskSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(RiskSeverity severity) {
        this.severity = severity;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map<String, Object> getEvidence() {
        return evidence;
    }

    public void setEvidence(Map<String, Object> evidence) {
        this.evidence = evidence;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getBlockingSignal() {
        return blockingSignal;
    }

    public void setBlockingSignal(Boolean blockingSignal) {
        this.blockingSignal = blockingSignal;
    }
}