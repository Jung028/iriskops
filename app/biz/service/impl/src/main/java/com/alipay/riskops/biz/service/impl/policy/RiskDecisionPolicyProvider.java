package com.alipay.riskops.biz.service.impl.policy;

import com.alipay.riskops.biz.service.impl.policy.rule.RiskDecisionRule;
import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.core.model.util.AssertUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adam
 * @date 28/5/2026 9:32 PM
 */
@Component
public class RiskDecisionPolicyProvider {

    private final Map<String, RiskDecisionPolicy> policyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        policyMap.put("TRANSFER", buildTransferPolicy());
    }

    public RiskDecisionPolicy getPolicy(String businessType) {
        AssertUtil.notBlank(businessType, RiskOpsResultCode.PARAM_ILLEGAL, "Business type cannot be blank");
        RiskDecisionPolicy policy = policyMap.get(businessType);
        AssertUtil.notNull(policy, RiskOpsResultCode.PARAM_ILLEGAL, "No policy found for business type: " + businessType);
        return policy;
    }

    public Map<String, RiskDecisionPolicy> getPolicyMap() {
        return policyMap;
    }

    private RiskDecisionPolicy buildTransferPolicy() {
        RiskDecisionPolicy policy = new RiskDecisionPolicy();
        policy.setBusinessType("TRANSFER");

        RiskDecisionRule approveRule = new RiskDecisionRule();
        approveRule.setMinScore(0);
        approveRule.setMaxScore(40);
        approveRule.setOutcome("APPROVE");
        approveRule.setReason("Low risk transfer");

        RiskDecisionRule stepUpRule = new RiskDecisionRule();
        stepUpRule.setMinScore(40);
        stepUpRule.setMaxScore(75);
        stepUpRule.setOutcome("STEP_UP");
        stepUpRule.setReason("Medium risk transfer — additional verification required");

        RiskDecisionRule blockRule = new RiskDecisionRule();
        blockRule.setMinScore(75);
        blockRule.setMaxScore(101);
        blockRule.setOutcome("BLOCK");
        blockRule.setReason("High risk transfer");

        policy.setRules(Arrays.asList(approveRule, stepUpRule, blockRule));
        return policy;
    }
}
