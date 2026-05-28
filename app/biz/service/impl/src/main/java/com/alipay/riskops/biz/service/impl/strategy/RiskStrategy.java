package com.alipay.riskops.biz.service.impl.strategy;

import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;

/**
 * @author adam
 * @date 25/5/2026 3:41 PM
 */
public interface RiskStrategy {

    boolean supports(RiskDecisionRequest request);

    RiskSignal evaluate(RiskDecisionRequest request);
}