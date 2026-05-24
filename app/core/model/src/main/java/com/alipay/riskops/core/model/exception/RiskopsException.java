package com.alipay.riskops.core.model.exception;


import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;

public class RiskopsException extends RuntimeException {

  private static final long seralVersionUID = 9187623791824214L;

  private RiskopsResultCode resultCode;

  public RiskopsException(RiskopsResultCode resultCode, String message) {
    super(message);
    this.resultCode = resultCode;
  }

  public RiskopsException(RiskopsResultCode resultCode) {
    this(resultCode, resultCode.getDescription());
  }

  public RiskopsResultCode getResultCode() {
    return resultCode;
  }

  public void setResultCode(RiskopsResultCode resultCode) {
    this.resultCode = resultCode;
  }
}
