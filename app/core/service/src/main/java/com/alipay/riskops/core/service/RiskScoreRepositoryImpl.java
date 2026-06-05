package com.alipay.riskops.core.service;

import com.alipay.riskops.common.dal.auto.custom.RiskScoreDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskScoreDO;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.core.model.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.alipay.riskops.core.model.convertor.RiskOpsInfoConvertor.convertToDO;

/**
 * @author adam
 */
@Repository
public class RiskScoreRepositoryImpl implements RiskScoreRepository {

    @Autowired
    private RiskScoreDAO riskScoreDAO;

    @Override
    public void insertRiskScore(RiskScoreResult riskScoreResult) {
        try {
            RiskScoreDO riskScoreDO = convertToDO(riskScoreResult);
            riskScoreDAO.insertRiskScore(riskScoreDO);
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
