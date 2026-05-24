package com.alipay.riskops.biz.service.impl.template;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;
import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseResult;

public abstract class RiskopsBizCallback<T extends RiskopsBaseRequest, R extends RiskopsBaseResult>{

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
