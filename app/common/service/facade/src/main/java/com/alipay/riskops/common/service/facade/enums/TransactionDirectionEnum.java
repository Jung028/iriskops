package com.alipay.riskops.common.service.facade.enums;

public enum TransactionDirectionEnum {
    INCOMING("INCOMING", "Money received by the riskops"),
    OUTGOING("OUTGOING", "Money paid out by the riskops");

    private final String code;
    private final String desc;

    TransactionDirectionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
