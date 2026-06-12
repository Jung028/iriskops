package com.alipay.riskops.biz.service.impl.feature;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;

import java.util.List;

/**
 * @author adam
 * @date 12/6/2026 7:24 PM
 */
public class RiskFeatureDispatcher {

    private final List<RiskFeatureProcessor> riskFeatureProcessors;

    public RiskFeatureDispatcher(List<RiskFeatureProcessor> riskFeatureProcessors) {
        this.riskFeatureProcessors = riskFeatureProcessors;
    }

    /**
     * dispatch
     * @param event
     */
    public void dispatch(EcTransactionEvent event) {
        for (RiskFeatureProcessor riskFeatureProcessor : riskFeatureProcessors) {
            riskFeatureProcessor.process(event);
        }
    }
}