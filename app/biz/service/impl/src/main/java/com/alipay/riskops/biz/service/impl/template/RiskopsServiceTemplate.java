package com.alipay.riskops.biz.service.impl.template;

import com.alipay.riskops.biz.service.impl.helper.RiskOpsResultHelper;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseRequest;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;
import com.alipay.riskops.common.service.facade.constant.LoggerConstant;
import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.common.util.EventContext;
import com.alipay.riskops.common.util.LogUtil;
import com.alipay.riskops.common.util.TenantUtil;
import com.alipay.riskops.common.util.enums.IpayTenantEnum;
import com.alipay.riskops.core.model.context.RiskOpsContextHolder;
import com.alipay.riskops.core.model.enums.RiskOpsActionEnum;
import com.alipay.riskops.core.model.exception.RiskOpsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RiskOpsServiceTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerConstant.RISKOPS_BIZ_SERVICE);

    /**
     * execute
     *
     * @param request digital risk request
     * @param action digital risk action
     * @param callback digital risk biz callback
     * @return digital risk result
     */
    public <T extends RiskOpsBaseRequest, R extends RiskOpsBaseResult> R execute(
            final T request,
            final RiskOpsActionEnum action,
            final RiskOpsBizCallback<T, R> callback) {

        R result = callback.createDefaultResponse();

        LogUtil.info(LOGGER, "service request[", request, "]");

        try {
            callback.checkParams(request);

            initContext(action, request);

            callback.process(request, result);

            RiskOpsResultHelper.fillSuccessResultCode(result);

        } catch (RiskOpsException e) {

            LogUtil.warn(LOGGER, e, "service process exception[", request, "]", ", code = "
                    , e.getResultCode(), ", msg= ", e.getMessage());

            RiskOpsResultHelper.fillExceptionResultCode(result, e.getResultCode());

        } catch (Throwable e) {
            LogUtil.error(LOGGER, e, "service process unexpected exception[", request, "]");

            RiskOpsResultHelper.fillExceptionResultCode(result, RiskOpsResultCode.SYSTEM_EXCEPTION);

        } finally {
            printDigestLog(result);

            LogUtil.info(LOGGER, "service result[" , result , "] [request =", request, "]" );

            RiskOpsContextHolder.clear();
        }


        return result;
    }

    private <R extends RiskOpsBaseResult> void printDigestLog(R result) {
    }

    private <T extends RiskOpsBaseRequest, R extends RiskOpsBaseResult> void initContext(RiskOpsActionEnum action, T request) {
        EventContext context = TenantUtil.getCurrentEventContext();
        context.setTntInstId(IpayTenantEnum.IPAY_SG.getTntInstId());
        TenantUtil.setCurrentEventContext(context);
       // BusinessContextHolder.set(action, slipExtraDAO.updateAndGetSystemDate(),request.getOperatorId(), request.getOperatorName());

    }
}
