package com.alipay.riskops.core.service;

import com.alipay.riskops.common.dal.auto.custom.RiskDecisionDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.core.model.domain.RiskDecision;
import com.alipay.riskops.core.model.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.alipay.riskops.core.model.convertor.RiskOpsInfoConvertor.convertToDO;

/**
 * @author adam
 * @date 27/5/2026 11:12 PM
 */
@Repository
public class RiskDecisionRepositoryImpl implements RiskDecisionRepository{

    @Autowired
    private RiskDecisionDAO riskDecisionDAO;

    @Override
    public void insertRiskDecision(RiskDecision riskDecision) {
        try {
            RiskDecisionDO riskDecisionDO = convertToDO(riskDecision);
            riskDecisionDAO.insertRiskDecision(riskDecisionDO);
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}