package com.alipay.riskops.common.service.facade.enums;

/**
 * @author adam
 * @date 28/5/2026 9:45 PM
 */
public enum RiskDecisionOutcome {
    APPROVE("APPROVE", "approve risk"),
    STEP_UP("STEP_UP", "risk decision require confirmation"),
    BLOCK("BLOCK", "risk decision to block"),
    REVIEW("REVIEW", "require review" );
    private String code;
    private String desc;

    RiskDecisionOutcome(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}