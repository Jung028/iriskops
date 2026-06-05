package com.alipay.riskops.biz.service.impl.policy;

import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.core.model.util.AssertUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adam
 * @date 28/5/2026 9:32 PM
 */
@Component
public class RiskDecisionPolicyProvider {

    private final Map<String, RiskDecisionPolicy> policyMap = new HashMap<>();

    public RiskDecisionPolicy getPolicy(String businessType) {
        AssertUtil.notBlank(businessType, RiskOpsResultCode.PARAM_ILLEGAL, "Business type cannot be blank");
        RiskDecisionPolicy policy = policyMap.get(businessType);
        AssertUtil.notNull(policy, RiskOpsResultCode.PARAM_ILLEGAL, "No policy found for business type: " + businessType);
        return policy;
    }

    public Map<String, RiskDecisionPolicy> getPolicyMap() {
        return policyMap;
    }
}