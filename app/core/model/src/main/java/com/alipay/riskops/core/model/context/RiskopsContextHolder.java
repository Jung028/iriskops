package com.alipay.riskops.core.model.context;

import com.alipay.riskops.core.model.enums.RiskopsActionEnum;

import java.util.Date;

public final class RiskopsContextHolder {

    private final static ThreadLocal<RiskopsContext> contextLocal = new ThreadLocal<>();

    public static void set(RiskopsContext context){
        contextLocal.set(context);
    }

    public static void set(RiskopsActionEnum action, Date time, String operatorId, String operatorName) {
        set(new RiskopsContext(action, time, operatorId, operatorName));
    }

    public static void clear() {
        contextLocal.remove();
    }
}
