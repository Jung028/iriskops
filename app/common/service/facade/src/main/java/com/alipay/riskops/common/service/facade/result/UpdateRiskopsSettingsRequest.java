package com.alipay.riskops.common.service.facade.result;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;
import com.alipay.riskops.common.service.facade.item.BusinessProfileItem;
import com.alipay.riskops.common.service.facade.item.RiskOpsSettingsItem;
import com.alipay.riskops.common.service.facade.item.PaymentSettingsItem;
import com.alipay.riskops.common.service.facade.item.TaxSettingsItem;

public class UpdateRiskOpsSettingsRequest extends RiskOpsBaseRequest {
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
