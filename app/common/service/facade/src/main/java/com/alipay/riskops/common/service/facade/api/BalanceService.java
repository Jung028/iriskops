package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;

/**
 * ibalance — bookkeeping and automatic reconciliation controls.
 */
public interface BalanceService {

    RiskOpsBizResult<Boolean> enableAutomaticBookkeeping(String riskopsId, boolean enable);
}
