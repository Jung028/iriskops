package com.alipay.riskops.common.dal.auto.custom;

import com.alipay.riskops.common.dal.auto.dataobject.RiskOpsInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RiskOpsInfoDAO {

    RiskOpsInfoDO queryRiskOpsInfo(@Param("riskopsId") String riskopsId);

    int insertRiskOpsInfo(RiskOpsInfoDO riskopsInfoDO);

    int updateRiskOpsInfo(RiskOpsInfoDO riskopsInfoDO);
}
