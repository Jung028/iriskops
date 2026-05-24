package com.alipay.riskops.biz.service.impl.checker;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;
import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;
import com.alipay.riskops.common.service.facade.result.QueryRiskopsInfoRequest;
import com.alipay.riskops.common.service.facade.result.SyncBankTransactionsRequest;
import com.alipay.riskops.common.service.facade.result.UpdateRiskopsSettingsRequest;
import com.alipay.riskops.core.model.exception.RiskopsException;
import com.alipay.riskops.common.service.facade.request.CreateRiskopsAccountRequest;

/**
 * @author adam
 * @date 25/4/2026 6:24 PM
 */
public class RiskopsRequestChecker {

    public static void checkQueryRiskopsInfoRequest(RiskopsBaseRequest request) {
    }

    public static void checkCreateRiskopsAccountRequest(CreateRiskopsAccountRequest request) {
        if (request == null) {
            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "request is null");
        }
        if (isBlank(request.getRiskopsName())) {
            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "riskopsName is required");
        }
    }

    public static void checkRiskopsIdRequest(QueryRiskopsInfoRequest request) {
        if (request == null || isBlank(request.getRiskopsId())) {
            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "riskopsId is required");
        }
    }

    public static void checkUpdateRiskopsSettingsRequest(UpdateRiskopsSettingsRequest request) {
        if (request == null) {
            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "request is null");
        }
    }

    public static void checkSyncBankTransactionsRequest(SyncBankTransactionsRequest request) {
        if (request == null || isBlank(request.getBankAccountId())) {
            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "bankAccountId is required");
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
