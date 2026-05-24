package com.alipay.riskops.common.service.facade.item;

public class SyncBankTransactionsResult {
    private String bankAccountId;
    private int transactionsSynced;
    private String syncedAt;

    public String getBankAccountId() { return bankAccountId; }
    public void setBankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; }

    public int getTransactionsSynced() { return transactionsSynced; }
    public void setTransactionsSynced(int transactionsSynced) { this.transactionsSynced = transactionsSynced; }

    public String getSyncedAt() { return syncedAt; }
    public void setSyncedAt(String syncedAt) { this.syncedAt = syncedAt; }
}
