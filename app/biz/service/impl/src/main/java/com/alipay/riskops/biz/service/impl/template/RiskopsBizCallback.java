package com.alipay.riskops.biz.service.impl.template;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;

public abstract class RiskOpsBizCallback<T extends RiskOpsBaseRequest, R extends RiskOpsBaseResult>{

    /**
     * define the default response object
     */
    protected abstract R createDefaultResponse();

    /**
     * check params
     */
    protected abstract void checkParams(T request);

    /**
     * execute
     */
    protected abstract void process(T request, R response);



}
