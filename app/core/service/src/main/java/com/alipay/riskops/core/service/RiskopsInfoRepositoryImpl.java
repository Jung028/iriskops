package com.alipay.riskops.core.service;

import com.alipay.riskops.common.dal.auto.custom.RiskOpsInfoDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskOpsInfoDO;
import com.alipay.riskops.core.model.convertor.RiskOpsInfoConvertor;
import com.alipay.riskops.core.model.domain.RiskOpsInfo;
import com.alipay.riskops.core.model.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * @author adam
 * @date 25/4/2026 5:20 PM
 */
@Repository
public class RiskOpsInfoRepositoryImpl implements RiskOpsInfoRepository {

    @Autowired
    private RiskOpsInfoDAO riskopsInfoDAO;

    @Override
    public RiskOpsInfo queryRiskOpsInfo(String riskopsId) {
        try {
            RiskOpsInfoDO riskopsInfoDO = riskopsInfoDAO.queryRiskOpsInfo(riskopsId);
            if (riskopsInfoDO == null) {
                throw new RepositoryException("RiskOpsInfoDO is null");
            }
            return RiskOpsInfoConvertor.convertToModel(riskopsInfoDO);
        } catch (RepositoryException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public String createRiskOpsInfo(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo.getRiskOpsId() == null) {
            riskopsInfo.setRiskOpsId(UUID.randomUUID().toString().replace("-", ""));
        }
        Date now = new Date();
        riskopsInfo.setCreatedAt(now);
        riskopsInfo.setUpdatedAt(now);

        RiskOpsInfoDO riskopsInfoDO = RiskOpsInfoConvertor.convertToDO(riskopsInfo);
        int rows = riskopsInfoDAO.insertRiskOpsInfo(riskopsInfoDO);
        if (rows != 1) {
            throw new RepositoryException("Failed to insert riskops info, rows=" + rows);
        }
        return riskopsInfo.getRiskOpsId();
    }

    @Override
    public void updateRiskOpsInfo(RiskOpsInfo riskopsInfo) {
        riskopsInfo.setUpdatedAt(new Date());
        RiskOpsInfoDO riskopsInfoDO = RiskOpsInfoConvertor.convertToDO(riskopsInfo);
        int rows = riskopsInfoDAO.updateRiskOpsInfo(riskopsInfoDO);
        if (rows != 1) {
            throw new RepositoryException("Failed to update riskops info, rows=" + rows);
        }
    }
}
