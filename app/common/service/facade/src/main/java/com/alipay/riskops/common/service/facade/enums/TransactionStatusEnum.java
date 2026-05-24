package com.alipay.riskops.common.service.facade.enums;

public enum TransactionStatusEnum {
    SUCCESS("SUCCESS", "Transaction completed successfully"),
    FAILED("FAILED", "Transaction failed"),
    PENDING("PENDING", "Transaction awaiting processing"),
    REFUNDED("REFUNDED", "Transaction fully refunded"),
    REFUND_PENDING("REFUND_PENDING", "Refund initiated but not yet settled");

    private final String code;
    private final String desc;

    TransactionStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
