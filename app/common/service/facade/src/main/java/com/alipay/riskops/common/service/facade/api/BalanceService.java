package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBizResult;

/**
 * ibalance — bookkeeping and automatic reconciliation controls.
 */
public interface BalanceService {

    RiskopsBizResult<Boolean> enableAutomaticBookkeeping(String riskopsId, boolean enable);
}
