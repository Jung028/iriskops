package com.alipay.riskops.biz.service.impl.template;

import com.alipay.riskops.biz.service.impl.helper.RiskopsResultHelper;
import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseRequest;
import com.alipay.riskops.common.service.facade.baseresult.RiskopsBaseResult;
import com.alipay.riskops.common.service.facade.constant.LoggerConstant;
import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;
import com.alipay.riskops.common.util.EventContext;
import com.alipay.riskops.common.util.LogUtil;
import com.alipay.riskops.common.util.TenantUtil;
import com.alipay.riskops.common.util.enums.IpayTenantEnum;
import com.alipay.riskops.core.model.context.RiskopsContextHolder;
import com.alipay.riskops.core.model.enums.RiskopsActionEnum;
import com.alipay.riskops.core.model.exception.RiskopsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RiskopsServiceTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerConstant.RISKOPS_BIZ_SERVICE);

    /**
     * execute
     *
     * @param request digital risk request
     * @param action digital risk action
     * @param callback digital risk biz callback
     * @return digital risk result
     */
    public <T extends RiskopsBaseRequest, R extends RiskopsBaseResult> R execute(
            final T request,
            final RiskopsActionEnum action,
            final RiskopsBizCallback<T, R> callback) {

        R result = callback.createDefaultResponse();

        LogUtil.info(LOGGER, "service request[", request, "]");

        try {
            callback.checkParams(request);

            initContext(action, request);

            callback.process(request, result);

            RiskopsResultHelper.fillSuccessResultCode(result);

        } catch (RiskopsException e) {

            LogUtil.warn(LOGGER, e, "service process exception[", request, "]", ", code = "
                    , e.getResultCode(), ", msg= ", e.getMessage());

            RiskopsResultHelper.fillExceptionResultCode(result, e.getResultCode());

        } catch (Throwable e) {
            LogUtil.error(LOGGER, e, "service process unexpected exception[", request, "]");

            RiskopsResultHelper.fillExceptionResultCode(result, RiskopsResultCode.SYSTEM_EXCEPTION);

        } finally {
            printDigestLog(result);

            LogUtil.info(LOGGER, "service result[" , result , "] [request =", request, "]" );

            RiskopsContextHolder.clear();
        }


        return result;
    }

    private <R extends RiskopsBaseResult> void printDigestLog(R result) {
    }

    private <T extends RiskopsBaseRequest, R extends RiskopsBaseResult> void initContext(RiskopsActionEnum action, T request) {
        EventContext context = TenantUtil.getCurrentEventContext();
        context.setTntInstId(IpayTenantEnum.IPAY_SG.getTntInstId());
        TenantUtil.setCurrentEventContext(context);
       // BusinessContextHolder.set(action, slipExtraDAO.updateAndGetSystemDate(),request.getOperatorId(), request.getOperatorName());

    }
}
