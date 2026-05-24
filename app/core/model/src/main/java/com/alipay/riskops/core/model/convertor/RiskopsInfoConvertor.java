package com.alipay.riskops.core.model.convertor;

import com.alipay.riskops.common.dal.auto.dataobject.RiskopsInfoDO;
import com.alipay.riskops.common.service.facade.item.RiskopsInfoItem;
import com.alipay.riskops.core.model.domain.RiskopsInfo;

/**
 * @author adam
 * @date 25/4/2026 6:31 PM
 */
public class RiskopsInfoConvertor {

    public static RiskopsInfoItem convertToItem(RiskopsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskopsInfoItem riskopsInfoItem = new RiskopsInfoItem();
        riskopsInfoItem.setRiskopsId(riskopsInfo.getRiskopsId());
        riskopsInfoItem.setRiskopsName(riskopsInfo.getRiskopsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskopsCategory(riskopsInfo.getRiskopsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

    public static RiskopsInfoItem convertToDomain(RiskopsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskopsInfoItem riskopsInfoItem = new RiskopsInfoItem();
        riskopsInfoItem.setRiskopsId(riskopsInfo.getRiskopsId());
        riskopsInfoItem.setRiskopsName(riskopsInfo.getRiskopsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskopsCategory(riskopsInfo.getRiskopsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

    public static RiskopsInfoDO convertToDO(RiskopsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskopsInfoDO riskopsInfoDO = new RiskopsInfoDO();
        riskopsInfoDO.setRiskopsId(riskopsInfo.getRiskopsId());
        riskopsInfoDO.setRiskopsName(riskopsInfo.getRiskopsName());
        riskopsInfoDO.setRiskopsCategory(riskopsInfo.getRiskopsCategory());
        riskopsInfoDO.setStatus(riskopsInfo.getStatus());
        riskopsInfoDO.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoDO.setUpdatedAt(riskopsInfo.getUpdatedAt());
        return riskopsInfoDO;
    }

    public static RiskopsInfo convertToModel(RiskopsInfoDO riskopsInfoDO) {
        if (riskopsInfoDO == null) {
            return null;
        }
        RiskopsInfo riskopsInfo = new RiskopsInfo();
        riskopsInfo.setRiskopsId(riskopsInfoDO.getRiskopsId());
        riskopsInfo.setRiskopsName(riskopsInfoDO.getRiskopsName());
        riskopsInfo.setCreatedAt(riskopsInfoDO.getCreatedAt());
        riskopsInfo.setUpdatedAt(riskopsInfoDO.getUpdatedAt());
        riskopsInfo.setRiskopsCategory(riskopsInfoDO.getRiskopsCategory());
        riskopsInfo.setStatus(riskopsInfoDO.getStatus());
        return riskopsInfo;
    }
}