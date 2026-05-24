package com.alipay.riskops.common.service.facade.item;

import java.util.List;

public class TransactionPageResult {
    private List<TransactionHistory> transactions;
    private int total;
    private int page;
    private int pageSize;

    public List<TransactionHistory> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionHistory> transactions) { this.transactions = transactions; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
}
