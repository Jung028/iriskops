package com.alipay.riskops.biz.service.impl.feature;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;

/**
 * @author adam
 * @date 12/6/2026 7:28 PM
 */
public interface RiskFeatureProcessor {

    void process(EcTransactionEvent event);
}