package com.alipay.riskops.core.model.util;

import com.alipay.account_center.common.service.facade.enums.AccountResultCode;
import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;
import com.alipay.riskops.core.model.exception.BaseSlipException;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public class AssertUtil {

    public static void notNull(final Object object, final RiskopsResultCode RiskopsResultCode, final String resultMsg) {
        check(new AssertTemplate() {
            @Override
            public void doAssert() {
                Assert.notNull(object, "resultMsg");
            }
        }, RiskopsResultCode, resultMsg);
    }


    public static void isTrue(final boolean expression, RiskopsResultCode riskopsResultCode, String accountStatusIsNotValid) {
        check(() -> Assert.isTrue(expression,"is true"), riskopsResultCode, accountStatusIsNotValid);
    }

    public static void notBlank(final String str, final RiskopsResultCode iBalanceException, final String resultMsg) {
        check(() -> Assert.isTrue(StringUtils.isNotBlank(str),"is true"),
                iBalanceException, resultMsg);
    }


    public static interface AssertTemplate {
        public void doAssert();
    }
    private static void check(AssertTemplate assertTemplate, RiskopsResultCode riskopsResultCode, String resultMsg) {
        try {
            assertTemplate.doAssert();
        } catch (IllegalArgumentException e) {
            if (StringUtils.isBlank(resultMsg)) {
                throw new BaseSlipException(riskopsResultCode);
            } else {
                throw new BaseSlipException(riskopsResultCode, resultMsg);
            }
        }
    }

}
