package com.alipay.riskops.common.dal.auto.custom;

import com.alipay.riskops.common.dal.auto.dataobject.RiskRuleConfigDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiskRuleConfigDAO {

    int insertRiskRuleConfig(RiskRuleConfigDO riskRuleConfigDO);

    RiskRuleConfigDO findByRuleCode(String ruleCode);

    List<RiskRuleConfigDO> findAllEnabled();
}
