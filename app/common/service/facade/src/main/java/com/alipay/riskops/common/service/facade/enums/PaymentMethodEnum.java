package com.alipay.riskops.common.service.facade.enums;

public enum PaymentMethodEnum {
    QR("QR", "QR code payment"),
    BANK_TRANSFER("BANK_TRANSFER", "Direct bank transfer"),
    CARD("CARD", "Card payment"),
    CASH("CASH", "Cash payment");

    private final String code;
    private final String desc;

    PaymentMethodEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
