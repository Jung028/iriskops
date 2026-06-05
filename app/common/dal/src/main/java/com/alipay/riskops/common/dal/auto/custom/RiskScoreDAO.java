package com.alipay.riskops.common.dal.auto.custom;

import com.alipay.riskops.common.dal.auto.dataobject.RiskScoreDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiskScoreDAO {

    void insertRiskScore(RiskScoreDO riskScoreDO);
}
