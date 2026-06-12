package com.alipay.riskops.core.model.enums;

/**
 * @author adam
 * @date 12/6/2026 11:12 PM
 */
public enum RiskRuleConfigCode {

    FAILED_TXN_CHECK("FAILED_TXN_CHECK", "failed to check transaction"),
    LARGE_AMOUNT_CHECK("LARGE_AMOUNT_CHECK", "Large amount check"),
    NEW_PAYEE_CHECK("NEW_PAYEE_CHECK", "new payee check"),
    TOP_UP_VELOCITY_CHECK("TOP_UP_VELOCITY_CHECK", "top-up velocity check"),
    TRANSFER_VELOCITY_CHECK("TRANSFER_VELOCITY_CHECK", "transfer velocity check");

    private String code;
    private String desc;

    RiskRuleConfigCode(String code, String desc) {
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