package com.alipay.riskops.core.service;

import com.alipay.riskops.core.model.domain.RiskRuleConfig;

import java.util.List;

/**
 * @author adam
 * @date 27/5/2026 11:11 PM
 */
public interface RiskRuleConfigRepository {

    void insertRiskRuleConfig(RiskRuleConfig riskRuleConfig);

    RiskRuleConfig findByRuleCode(String ruleCode);

    List<RiskRuleConfig> findAllEnabled();
}
