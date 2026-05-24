package com.alipay.riskops.common.dal.auto.custom;

import com.alipay.riskops.common.dal.auto.dataobject.RiskopsInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RiskopsInfoDAO {

    RiskopsInfoDO queryRiskopsInfo(@Param("riskopsId") String riskopsId);

    int insertRiskopsInfo(RiskopsInfoDO riskopsInfoDO);

    int updateRiskopsInfo(RiskopsInfoDO riskopsInfoDO);
}
