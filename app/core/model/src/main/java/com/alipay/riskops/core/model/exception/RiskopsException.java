package com.alipay.riskops.core.model.exception;


import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;

public class RiskOpsException extends RuntimeException {

  private static final long seralVersionUID = 9187623791824214L;

  private RiskOpsResultCode resultCode;

  public RiskOpsException(RiskOpsResultCode resultCode, String message) {
    super(message);
    this.resultCode = resultCode;
  }

  public RiskOpsException(RiskOpsResultCode resultCode) {
    this(resultCode, resultCode.getDescription());
  }

  public RiskOpsResultCode getResultCode() {
    return resultCode;
  }

  public void setResultCode(RiskOpsResultCode resultCode) {
    this.resultCode = resultCode;
  }
}
