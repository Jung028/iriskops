package com.alipay.riskops.common.service.facade.enums;

/**
 * @author adam
 * @date 26/4/2026 11:02 AM
 */
public enum RiskopsStatus {
    ACTIVE("ACTIVE", "Active Account"),
    INACTIVE("INACTIVE", "Inactive Account")
    ;

    private String code;
    private String desc;

    RiskopsStatus(String code, String desc) {
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