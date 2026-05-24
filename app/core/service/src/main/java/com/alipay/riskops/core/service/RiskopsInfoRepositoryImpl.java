package com.alipay.riskops.core.service;

import com.alipay.riskops.common.dal.auto.custom.RiskopsInfoDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskopsInfoDO;
import com.alipay.riskops.core.model.convertor.RiskopsInfoConvertor;
import com.alipay.riskops.core.model.domain.RiskopsInfo;
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
public class RiskopsInfoRepositoryImpl implements RiskopsInfoRepository {

    @Autowired
    private RiskopsInfoDAO riskopsInfoDAO;

    @Override
    public RiskopsInfo queryRiskopsInfo(String riskopsId) {
        try {
            RiskopsInfoDO riskopsInfoDO = riskopsInfoDAO.queryRiskopsInfo(riskopsId);
            if (riskopsInfoDO == null) {
                throw new RepositoryException("RiskopsInfoDO is null");
            }
            return RiskopsInfoConvertor.convertToModel(riskopsInfoDO);
        } catch (RepositoryException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public String createRiskopsInfo(RiskopsInfo riskopsInfo) {
        if (riskopsInfo.getRiskopsId() == null) {
            riskopsInfo.setRiskopsId(UUID.randomUUID().toString().replace("-", ""));
        }
        Date now = new Date();
        riskopsInfo.setCreatedAt(now);
        riskopsInfo.setUpdatedAt(now);

        RiskopsInfoDO riskopsInfoDO = RiskopsInfoConvertor.convertToDO(riskopsInfo);
        int rows = riskopsInfoDAO.insertRiskopsInfo(riskopsInfoDO);
        if (rows != 1) {
            throw new RepositoryException("Failed to insert riskops info, rows=" + rows);
        }
        return riskopsInfo.getRiskopsId();
    }

    @Override
    public void updateRiskopsInfo(RiskopsInfo riskopsInfo) {
        riskopsInfo.setUpdatedAt(new Date());
        RiskopsInfoDO riskopsInfoDO = RiskopsInfoConvertor.convertToDO(riskopsInfo);
        int rows = riskopsInfoDAO.updateRiskopsInfo(riskopsInfoDO);
        if (rows != 1) {
            throw new RepositoryException("Failed to update riskops info, rows=" + rows);
        }
    }
}
