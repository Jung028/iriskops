package com.alipay.riskops.core.model.context;

import com.alipay.riskops.core.model.enums.RiskOpsActionEnum;

import java.util.Date;

public final class RiskOpsContextHolder {

    private final static ThreadLocal<RiskOpsContext> contextLocal = new ThreadLocal<>();

    public static void set(RiskOpsContext context){
        contextLocal.set(context);
    }

    public static void set(RiskOpsActionEnum action, Date time, String operatorId, String operatorName) {
        set(new RiskOpsContext(action, time, operatorId, operatorName));
    }

    public static void clear() {
        contextLocal.remove();
    }
}
