package com.alipay.riskops.common.service.facade.item;

import java.math.BigDecimal;

public class BankAccountItem {
    private String bankAccountId;
    private String bankName;
    private String accountName;
    private String bsb;
    private String status;
    private String lastSyncedAt;
    private BigDecimal balance;
    private String currency;

    public String getBankAccountId() { return bankAccountId; }
    public void setBankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getBsb() { return bsb; }
    public void setBsb(String bsb) { this.bsb = bsb; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLastSyncedAt() { return lastSyncedAt; }
    public void setLastSyncedAt(String lastSyncedAt) { this.lastSyncedAt = lastSyncedAt; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
