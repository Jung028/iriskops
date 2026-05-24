package com.alipay.riskops.core.model.exception;


import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;

public class BaseSlipException extends RuntimeException {

    public BaseSlipException(RiskopsResultCode slipResultEnum) {
        super(slipResultEnum.getDescription());

    }

    public BaseSlipException(RiskopsResultCode RiskopsResultCode, String resultMsg) {
        super(RiskopsResultCode.getDescription() + ":" + resultMsg);
    }
}
