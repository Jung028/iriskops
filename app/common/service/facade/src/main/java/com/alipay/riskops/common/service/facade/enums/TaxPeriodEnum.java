package com.alipay.riskops.common.service.facade.enums;

public enum TaxPeriodEnum {
    MONTHLY("MONTHLY", "Monthly BAS / tax period"),
    QUARTERLY("QUARTERLY", "Quarterly BAS / tax period"),
    ANNUALLY("ANNUALLY", "Annual tax period");

    private final String code;
    private final String desc;

    TaxPeriodEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
