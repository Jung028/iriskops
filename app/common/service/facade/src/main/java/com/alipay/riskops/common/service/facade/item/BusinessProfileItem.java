package com.alipay.riskops.common.service.facade.item;

/**
 * @author adam
 * @date 19/5/2026 6:34 PM
 */
public class BusinessProfileItem {
    private String riskopsId;
    private String businessName;
    private String abn;
    private String businessType;
    private String email;
    private String phone;
    private String address;
    private String status;

    public String getRiskOpsId() { return riskopsId; }
    public void setRiskOpsId(String riskopsId) { this.riskopsId = riskopsId; }
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public String getAbn() { return abn; }
    public void setAbn(String abn) { this.abn = abn; }
    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}