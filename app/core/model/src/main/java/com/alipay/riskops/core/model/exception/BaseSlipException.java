package com.alipay.riskops.core.model.exception;


import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;

public class BaseSlipException extends RuntimeException {

    public BaseSlipException(RiskOpsResultCode slipResultEnum) {
        super(slipResultEnum.getDescription());

    }

    public BaseSlipException(RiskOpsResultCode RiskOpsResultCode, String resultMsg) {
        super(RiskOpsResultCode.getDescription() + ":" + resultMsg);
    }
}
