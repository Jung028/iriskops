package com.alipay.riskops.common.service.facade.enums;

/**
 * @author adam
 * @date 27/5/2026 11:25 PM
 */
public enum RiskRuleCode {
    TRANSFER_VELOCITY_CHECK("TRANSFER_VELOCITY_CHECK", "Risk rule transfer velocity"),
    TOP_UP_VELOCITY_CHECK("TOP_UP_VELOCITY_CHECK", "Risk rule top-up velocity"),
    NEW_PAYEE_CHECK("NEW_PAYEE_CHECK", "Risk rule new payee detection"),
    LARGE_AMOUNT_CHECK("LARGE_AMOUNT_CHECK", "Risk rule large outbound amount"),
    FAILED_TXN_CHECK("FAILED_TXN_CHECK", "Risk rule failed transaction pattern");
    private String code;
    private String desc;

    RiskRuleCode(String code, String desc) {
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