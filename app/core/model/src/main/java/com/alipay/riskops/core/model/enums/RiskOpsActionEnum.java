package com.alipay.riskops.core.model.enums;

import java.util.Date;

public enum RiskOpsActionEnum {
    QUERY_RISKOPS_INFO("QUERY_RISKOPS_INFO", "Query riskops info"),
    CREATE_RISKOPS_ACCOUNT("CREATE_RISKOPS_ACCOUNT", "Create riskops account"),
    QUERY_RISKOPS_BY_ID("QUERY_RISKOPS_BY_ID", "Query riskops by id"),
    QUERY_RISKOPS_BALANCE("QUERY_RISKOPS_BALANCE", "Query riskops balance"),
    QUERY_RISKOPS_SETTINGS("QUERY_RISKOPS_SETTINGS", "Query riskops settings"),
    UPDATE_RISKOPS_SETTINGS("UPDATE_RISKOPS_SETTINGS", "Update riskops settings"),
    UPDATE_RISKOPS_PROFILE("UPDATE_RISKOPS_PROFILE", "Update riskops profile"),
    CREATE_BANK_CONNECTION_SESSION("CREATE_BANK_CONNECTION_SESSION", "Create bank connection session"),
    HANDLE_BANK_CONNECTION_CALLBACK("HANDLE_BANK_CONNECTION_CALLBACK", "Handle bank connection callback"),
    QUERY_CONNECTED_BANK_ACCOUNTS("QUERY_CONNECTED_BANK_ACCOUNTS", "Query connected bank accounts"),
    SYNC_BANK_TRANSACTIONS("SYNC_BANK_TRANSACTIONS", "Sync bank transactions"),
    DISCONNECT_BANK_ACCOUNT("DISCONNECT_BANK_ACCOUNT", "Disconnect bank account"),
    EVALUATE_TRANSFER_RISK("EVALUATE_TRANSFER_RISK", "Evaluate riskops transfer"),

    ;

    private String code;
    private String desc;
    RiskOpsActionEnum(String code, String desc) {
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
