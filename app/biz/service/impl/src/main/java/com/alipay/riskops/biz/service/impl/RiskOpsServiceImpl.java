package com.alipay.riskops.biz.service.impl;

import com.alipay.riskops.biz.service.impl.checker.RiskOpsRequestChecker;
import com.alipay.riskops.biz.service.impl.template.RiskopsBizCallback;
import com.alipay.riskops.biz.service.impl.template.RiskopsServiceTemplate;
import com.alipay.riskops.common.service.facade.api.RiskOpsService;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBaseResult;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.core.model.enums.RiskOpsActionEnum;
import com.alipay.riskops.core.service.RiskopsInfoRepository;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adam
 * @date 25/4/2026 6:12 PM
 */
@SofaService(
        interfaceType = RiskOpsService.class,
        bindings = {
                @SofaServiceBinding(bindingType = "rest"),
                @SofaServiceBinding(bindingType = "bolt")
        }
)
@Service
public class RiskOpsServiceImpl implements RiskOpsService {

    @Autowired
    private RiskopsServiceTemplate riskopsServiceTemplate;

    @Autowired
    private RiskopsInfoRepository riskopsInfoRepository;

    @Override
    public RiskOpsBizResult<RiskDecisionResult> evaluateTransferRisk(RiskDecisionRequest request) {
        return riskopsServiceTemplate.execute(request, RiskOpsActionEnum.EVALUATE_TRANSFER_RISK,
                new RiskopsBizCallback<>() {
                    @Override
                    protected RiskOpsBizResult<RiskDecisionResult> createDefaultResponse() {
                        return new RiskOpsBizResult<>() {};
                    }

                    @Override
                    protected void checkParams(RiskDecisionRequest request) {
                        RiskOpsRequestChecker.checkRiskDecisionRequest(request);
                    }

                    @Override
                    protected void process(RiskDecisionRequest request, RiskOpsBizResult<RiskDecisionResult> response) {
                        // call asynchronous risk scoring.
                        // update the risk_score table
                        // get average
                        // update risk_decision table. 
                    }
                });
    }
}
