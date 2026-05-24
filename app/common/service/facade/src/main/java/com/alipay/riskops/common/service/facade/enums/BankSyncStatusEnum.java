package com.alipay.riskops.common.service.facade.enums;

public enum BankSyncStatusEnum {
    SYNCED("SYNCED", "All connected accounts synced successfully"),
    SYNCING("SYNCING", "Sync in progress"),
    ERROR("ERROR", "Last sync encountered an error"),
    NEVER_SYNCED("NEVER_SYNCED", "No sync has been performed yet");

    private final String code;
    private final String desc;

    BankSyncStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
