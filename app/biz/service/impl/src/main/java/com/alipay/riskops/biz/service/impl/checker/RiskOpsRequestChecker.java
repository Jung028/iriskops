package com.alipay.riskops.biz.service.impl.checker;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;
import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.QueryRiskOpsInfoRequest;
import com.alipay.riskops.common.service.facade.result.SyncBankTransactionsRequest;
import com.alipay.riskops.common.service.facade.result.UpdateRiskOpsSettingsRequest;
import com.alipay.riskops.core.model.exception.RiskOpsException;
import com.alipay.riskops.common.service.facade.request.CreateRiskOpsAccountRequest;

/**
 * @author adam
 * @date 25/4/2026 6:24 PM
 */
public class RiskOpsRequestChecker {

    public static void checkQueryRiskOpsInfoRequest(RiskOpsBaseRequest request) {
    }

    public static void checkCreateRiskOpsAccountRequest(CreateRiskOpsAccountRequest request) {
        if (request == null) {
            throw new RiskOpsException(RiskOpsResultCode.PARAM_ILLEGAL, "request is null");
        }
        if (isBlank(request.getRiskOpsName())) {
            throw new RiskOpsException(RiskOpsResultCode.PARAM_ILLEGAL, "riskopsName is required");
        }
    }

    public static void checkRiskOpsIdRequest(QueryRiskOpsInfoRequest request) {
        if (request == null || isBlank(request.getRiskOpsId())) {
            throw new RiskOpsException(RiskOpsResultCode.PARAM_ILLEGAL, "riskopsId is required");
        }
    }

    public static void checkUpdateRiskOpsSettingsRequest(UpdateRiskOpsSettingsRequest request) {
        if (request == null) {
            throw new RiskOpsException(RiskOpsResultCode.PARAM_ILLEGAL, "request is null");
        }
    }

    public static void checkSyncBankTransactionsRequest(SyncBankTransactionsRequest request) {
        if (request == null || isBlank(request.getBankAccountId())) {
            throw new RiskOpsException(RiskOpsResultCode.PARAM_ILLEGAL, "bankAccountId is required");
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static void checkRiskDecisionRequest(RiskDecisionRequest request) {
    }
}
