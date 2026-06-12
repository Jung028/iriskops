package com.alipay.riskops.core.service;

import com.alipay.riskops.common.dal.auto.custom.RiskRuleConfigDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskRuleConfigDO;
import com.alipay.riskops.core.model.convertor.DomainConverter;
import com.alipay.riskops.core.model.domain.RiskRuleConfig;
import com.alipay.riskops.core.model.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adam
 * @date 12/6/2026 10:48 PM
 */
@Repository
public class RiskRuleConfigRepositoryImpl implements RiskRuleConfigRepository {

    @Autowired
    private RiskRuleConfigDAO riskRuleConfigDAO;

    @Override
    public void insertRiskRuleConfig(RiskRuleConfig riskRuleConfig) {
        try {
            RiskRuleConfigDO riskRuleConfigDO = DomainConverter.convertToDO(riskRuleConfig);
            int rows = riskRuleConfigDAO.insertRiskRuleConfig(riskRuleConfigDO);
            if (rows == 0) {
                throw new RepositoryException("Insert risk rule config failed, no record exists");
            }
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public RiskRuleConfig findByRuleCode(String ruleCode) {
        try {
            RiskRuleConfigDO riskRuleConfigDO = riskRuleConfigDAO.findByRuleCode(ruleCode);
            return DomainConverter.convertFromDO(riskRuleConfigDO);
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Failed to find risk rule config by ruleCode: " + ruleCode + ", " + e.getMessage());
        }
    }

    @Override
    public List<RiskRuleConfig> findAllEnabled() {
        try {
            List<RiskRuleConfigDO> dos = riskRuleConfigDAO.findAllEnabled();
            if (dos == null || dos.isEmpty()) {
                return Collections.emptyList();
            }
            return dos.stream()
                    .map(DomainConverter::convertFromDO)
                    .collect(Collectors.toList());
        } catch (RepositoryException e) {
            throw e;
        } catch (Exception e) {
            throw new RepositoryException("Failed to load enabled risk rule configs: " + e.getMessage());
        }
    }
}
