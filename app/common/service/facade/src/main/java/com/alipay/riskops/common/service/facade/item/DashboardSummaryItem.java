package com.alipay.riskops.common.service.facade.item;

import java.math.BigDecimal;
import java.util.List;

public class DashboardSummaryItem {
    private BigDecimal todayRevenue;
    private BigDecimal monthlyRevenue;
    private int totalTransactions;
    private int failedPayments;
    private int refundedPayments;
    private String bankSyncStatus;
    private String lastBankSync;
    private List<RevenueChartPoint> revenueChart;

    public BigDecimal getTodayRevenue() { return todayRevenue; }
    public void setTodayRevenue(BigDecimal todayRevenue) { this.todayRevenue = todayRevenue; }

    public BigDecimal getMonthlyRevenue() { return monthlyRevenue; }
    public void setMonthlyRevenue(BigDecimal monthlyRevenue) { this.monthlyRevenue = monthlyRevenue; }

    public int getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(int totalTransactions) { this.totalTransactions = totalTransactions; }

    public int getFailedPayments() { return failedPayments; }
    public void setFailedPayments(int failedPayments) { this.failedPayments = failedPayments; }

    public int getRefundedPayments() { return refundedPayments; }
    public void setRefundedPayments(int refundedPayments) { this.refundedPayments = refundedPayments; }

    public String getBankSyncStatus() { return bankSyncStatus; }
    public void setBankSyncStatus(String bankSyncStatus) { this.bankSyncStatus = bankSyncStatus; }

    public String getLastBankSync() { return lastBankSync; }
    public void setLastBankSync(String lastBankSync) { this.lastBankSync = lastBankSync; }

    public List<RevenueChartPoint> getRevenueChart() { return revenueChart; }
    public void setRevenueChart(List<RevenueChartPoint> revenueChart) { this.revenueChart = revenueChart; }

    public static class RevenueChartPoint {
        private String date;
        private BigDecimal revenue;
        private int transactions;

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public BigDecimal getRevenue() { return revenue; }
        public void setRevenue(BigDecimal revenue) { this.revenue = revenue; }

        public int getTransactions() { return transactions; }
        public void setTransactions(int transactions) { this.transactions = transactions; }
    }
}
