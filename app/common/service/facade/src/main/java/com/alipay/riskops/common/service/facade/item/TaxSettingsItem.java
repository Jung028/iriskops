package com.alipay.riskops.common.service.facade.item;

/**
 * @author adam
 * @date 19/5/2026 6:35 PM
 */

public class TaxSettingsItem {
    private boolean gstRegistered;
    private String taxPeriod;

    public boolean isGstRegistered() { return gstRegistered; }
    public void setGstRegistered(boolean gstRegistered) { this.gstRegistered = gstRegistered; }
    public String getTaxPeriod() { return taxPeriod; }
    public void setTaxPeriod(String taxPeriod) { this.taxPeriod = taxPeriod; }
}