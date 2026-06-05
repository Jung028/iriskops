package com.alipay.riskops.biz.service.impl.policy;

import com.alipay.riskops.biz.service.impl.policy.rule.RiskDecisionRule;

import java.util.List;

/**
 * @author adam
 * @date 28/5/2026 9:31 PM
 */
public class RiskDecisionPolicy {

    private String businessType;

    private List<RiskDecisionRule> rules;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public List<RiskDecisionRule> getRules() {
        return rules;
    }

    public void setRules(List<RiskDecisionRule> rules) {
        this.rules = rules;
    }
}