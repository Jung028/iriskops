package com.alipay.riskops.common.dal.auto.custom;

import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiskDecisionDAO {

    void insertRiskDecision(RiskDecisionDO riskDecisionDO);
}
