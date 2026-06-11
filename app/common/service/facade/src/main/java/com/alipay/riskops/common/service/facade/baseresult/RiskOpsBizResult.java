package com.alipay.riskops.common.service.facade.baseresult;

public class RiskOpsBizResult<T> extends RiskOpsBaseResult {

    private static final long serialVersionUID = 1L;
    private T result;
    private boolean success;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
