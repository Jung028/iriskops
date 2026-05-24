package com.alipay.riskops.common.service.facade.enums;

public enum BusinessTypeEnum {
    SOLE_TRADER("SOLE_TRADER", "Sole trader"),
    COMPANY("COMPANY", "Registered company (Pty Ltd etc.)"),
    PARTNERSHIP("PARTNERSHIP", "Partnership"),
    TRUST("TRUST", "Trust");

    private final String code;
    private final String desc;

    BusinessTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
