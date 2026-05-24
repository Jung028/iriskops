package com.alipay.riskops.common.service.facade.enums;

public enum QrTypeEnum {
    STATIC("STATIC", "Static QR — no fixed amount, reusable"),
    DYNAMIC("DYNAMIC", "Dynamic QR — fixed amount, single-use or expiring");

    private final String code;
    private final String desc;

    QrTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
