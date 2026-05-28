package com.alipay.riskops.common.service.facade.request;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adam
 * @date 27/5/2026 9:33 PM
 */
public class RiskDecisionRequest extends RiskOpsBaseRequest {

    /**
     * The business record id.
     * Example: txnId, topUpId, refundId.
     */
    private String businessId;

    /**
     * Business action type.
     * Example: TRANSFER, TOP_UP, WITHDRAWAL, PAYMENT.
     */
    private String businessType;

    /**
     * Actor user.
     */
    private String userId;

    /**
     * Main account performing the action.
     */
    private String accountNo;

    /**
     * Transfer-specific fields.
     */
    private String payerAccountNo;

    /**
     * payee account no
     */
    private String payeeAccountNo;

    /**
     * Amount and currency.
     */
    private BigDecimal amount;

    /**
     * currency
     */
    private String currency;

    /**
     * Request-level idempotency key.
     */
    private String uniqueRequestId;

    /**
     * Runtime context signals.
     */
    private String deviceId;

    /**
     * ip address
     */
    private String ipAddress;

    /**
     * country
     */
    private String country;

    /**
     * Trace id for debugging across services.
     */
    private String traceId;

    /**
     * When the business action occurred/requested.
     */
    private Date occurredAt;

    /**
     * Extra fields for different business types.
     *
     * Example:
     * - receiverUserId
     * - cardCountry
     * - merchantId
     * - bankAccountNo
     */
    private Map<String, Object> attributes = new HashMap<>();

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPayerAccountNo() {
        return payerAccountNo;
    }

    public void setPayerAccountNo(String payerAccountNo) {
        this.payerAccountNo = payerAccountNo;
    }

    public String getPayeeAccountNo() {
        return payeeAccountNo;
    }

    public void setPayeeAccountNo(String payeeAccountNo) {
        this.payeeAccountNo = payeeAccountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String uniqueRequestId) {
        this.uniqueRequestId = uniqueRequestId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Date getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Date occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}