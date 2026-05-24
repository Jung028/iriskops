package com.alipay.riskops.common.service.facade.item;

/**
 * @author adam
 * @date 19/5/2026 6:35 PM
 */

public class PaymentSettingsItem {
    private String defaultCurrency;
    private boolean qrPaymentsEnabled;
    private boolean refundsEnabled;

    public String getDefaultCurrency() { return defaultCurrency; }
    public void setDefaultCurrency(String defaultCurrency) { this.defaultCurrency = defaultCurrency; }
    public boolean isQrPaymentsEnabled() { return qrPaymentsEnabled; }
    public void setQrPaymentsEnabled(boolean qrPaymentsEnabled) { this.qrPaymentsEnabled = qrPaymentsEnabled; }
    public boolean isRefundsEnabled() { return refundsEnabled; }
    public void setRefundsEnabled(boolean refundsEnabled) { this.refundsEnabled = refundsEnabled; }
}