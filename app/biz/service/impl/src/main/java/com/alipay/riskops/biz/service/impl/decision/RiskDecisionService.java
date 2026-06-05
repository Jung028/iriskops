package com.alipay.riskops.biz.service.impl.decision;

import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;

/**
 * @author adam
 * @date 28/5/2026 9:23 PM
 */
public interface RiskDecisionService {

    RiskDecisionResult decide(RiskScoreResult riskScoreResult);
}