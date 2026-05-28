package com.alipay.riskops.core.model.util;

import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.core.model.exception.BaseSlipException;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public class AssertUtil {

    public static void notNull(final Object object, final RiskOpsResultCode RiskOpsResultCode, final String resultMsg) {
        check(new AssertTemplate() {
            @Override
            public void doAssert() {
                Assert.notNull(object, "resultMsg");
            }
        }, RiskOpsResultCode, resultMsg);
    }
    public static interface AssertTemplate {
        public void doAssert();
    }
    private static void check(AssertTemplate assertTemplate, RiskOpsResultCode RiskOpsResultCode, String resultMsg) {
        try {
            assertTemplate.doAssert();
        } catch (IllegalArgumentException e) {
            if (StringUtils.isBlank(resultMsg)) {
                throw new BaseSlipException(RiskOpsResultCode);
            } else {
                throw new BaseSlipException(RiskOpsResultCode, resultMsg);
            }
        }
    }
}
