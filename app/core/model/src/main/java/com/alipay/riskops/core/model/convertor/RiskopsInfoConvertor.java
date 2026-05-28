package com.alipay.riskops.core.model.convertor;

import com.alipay.riskops.common.dal.auto.dataobject.RiskOpsInfoDO;
import com.alipay.riskops.common.service.facade.item.RiskOpsInfoItem;
import com.alipay.riskops.core.model.domain.RiskOpsInfo;

/**
 * @author adam
 * @date 25/4/2026 6:31 PM
 */
public class RiskOpsInfoConvertor {

    public static RiskOpsInfoItem convertToItem(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskOpsInfoItem riskopsInfoItem = new RiskOpsInfoItem();
        riskopsInfoItem.setRiskOpsId(riskopsInfo.getRiskOpsId());
        riskopsInfoItem.setRiskOpsName(riskopsInfo.getRiskOpsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskOpsCategory(riskopsInfo.getRiskOpsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

    public static RiskOpsInfoItem convertToDomain(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskOpsInfoItem riskopsInfoItem = new RiskOpsInfoItem();
        riskopsInfoItem.setRiskOpsId(riskopsInfo.getRiskOpsId());
        riskopsInfoItem.setRiskOpsName(riskopsInfo.getRiskOpsName());
        riskopsInfoItem.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoItem.setUpdatedAt(riskopsInfo.getUpdatedAt());
        riskopsInfoItem.setRiskOpsCategory(riskopsInfo.getRiskOpsCategory());
        riskopsInfoItem.setStatus(riskopsInfo.getStatus());
        return riskopsInfoItem;
    }

    public static RiskOpsInfoDO convertToDO(RiskOpsInfo riskopsInfo) {
        if (riskopsInfo == null) {
            return null;
        }
        RiskOpsInfoDO riskopsInfoDO = new RiskOpsInfoDO();
        riskopsInfoDO.setRiskOpsId(riskopsInfo.getRiskOpsId());
        riskopsInfoDO.setRiskOpsName(riskopsInfo.getRiskOpsName());
        riskopsInfoDO.setRiskOpsCategory(riskopsInfo.getRiskOpsCategory());
        riskopsInfoDO.setStatus(riskopsInfo.getStatus());
        riskopsInfoDO.setCreatedAt(riskopsInfo.getCreatedAt());
        riskopsInfoDO.setUpdatedAt(riskopsInfo.getUpdatedAt());
        return riskopsInfoDO;
    }

    public static RiskOpsInfo convertToModel(RiskOpsInfoDO riskopsInfoDO) {
        if (riskopsInfoDO == null) {
            return null;
        }
        RiskOpsInfo riskopsInfo = new RiskOpsInfo();
        riskopsInfo.setRiskOpsId(riskopsInfoDO.getRiskOpsId());
        riskopsInfo.setRiskOpsName(riskopsInfoDO.getRiskOpsName());
        riskopsInfo.setCreatedAt(riskopsInfoDO.getCreatedAt());
        riskopsInfo.setUpdatedAt(riskopsInfoDO.getUpdatedAt());
        riskopsInfo.setRiskOpsCategory(riskopsInfoDO.getRiskOpsCategory());
        riskopsInfo.setStatus(riskopsInfoDO.getStatus());
        return riskopsInfo;
    }
}