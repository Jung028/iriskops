package com.alipay.riskops.common.service.facade.enums;

public enum BankAccountStatusEnum {
    ACTIVE("ACTIVE", "Bank account linked and syncing normally"),
    PENDING("PENDING", "Awaiting consent confirmation from the bank"),
    DISCONNECTED("DISCONNECTED", "Consent revoked or connection removed"),
    FAILED("FAILED", "Last sync or connection attempt failed");

    private final String code;
    private final String desc;

    BankAccountStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
