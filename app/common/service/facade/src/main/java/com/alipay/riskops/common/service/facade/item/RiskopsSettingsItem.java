package com.alipay.riskops.common.service.facade.item;

public class RiskopsSettingsItem {

    private BusinessProfileItem profile;
    private TaxSettingsItem tax;
    private PaymentSettingsItem payment;

    public BusinessProfileItem getProfile() { return profile; }
    public void setProfile(BusinessProfileItem profile) { this.profile = profile; }

    public TaxSettingsItem getTax() { return tax; }
    public void setTax(TaxSettingsItem tax) { this.tax = tax; }

    public PaymentSettingsItem getPayment() { return payment; }
    public void setPayment(PaymentSettingsItem payment) { this.payment = payment; }

}
