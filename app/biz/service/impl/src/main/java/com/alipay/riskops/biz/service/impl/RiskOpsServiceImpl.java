package com.alipay.riskops.biz.service.impl;

import com.alipay.riskops.biz.service.impl.checker.RiskOpsRequestChecker;
import com.alipay.riskops.biz.service.impl.decision.RiskDecisionService;
import com.alipay.riskops.biz.service.impl.engine.RiskScoreEngine;
import com.alipay.riskops.biz.service.impl.helper.ResponseBuilder;
import com.alipay.riskops.biz.service.impl.helper.RiskOpsResultHelper;
import com.alipay.riskops.biz.service.impl.template.RiskOpsBizCallback;
import com.alipay.riskops.biz.service.impl.template.RiskOpsServiceTemplate;
import com.alipay.riskops.common.service.facade.api.RiskOpsService;
import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import com.alipay.riskops.core.model.enums.RiskOpsActionEnum;
import com.alipay.riskops.core.service.RiskScoreRepository;
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
    private RiskOpsServiceTemplate riskopsServiceTemplate;

    @Autowired
    private RiskScoreEngine riskScoreEngine;

    @Autowired
    private RiskScoreRepository riskScoreRepository;

    @Autowired
    private RiskDecisionService riskDecisionService;

    @Override
    public RiskOpsBizResult<RiskDecisionResult> evaluateTransferRisk(RiskDecisionRequest request) {
        return riskopsServiceTemplate.execute(request, RiskOpsActionEnum.EVALUATE_TRANSFER_RISK,
                new RiskOpsBizCallback<>() {
                    @Override
                    protected RiskOpsBizResult<RiskDecisionResult> createDefaultResponse() {
                        return new RiskOpsBizResult<>();
                    }

                    @Override
                    protected void checkParams(RiskDecisionRequest request) {
                        RiskOpsRequestChecker.checkRiskDecisionRequest(request);
                    }

                    @Override
                    protected void process(RiskDecisionRequest request, RiskOpsBizResult<RiskDecisionResult> response) {
                        // call asynchronous risk scoring.
                        RiskScoreResult riskScore = riskScoreEngine.evaluate(request);

                        // update the risk_score table
                        RiskScoreResult riskScoreResult = new RiskScoreResult();
                        riskScoreResult.setRiskScoreId(riskScore.getRiskScoreId());
                        riskScoreResult.setAccountNo(riskScore.getAccountNo());
                        riskScoreResult.setFinalScore(riskScore.getFinalScore());
                        riskScoreResult.setBusinessId(riskScore.getBusinessId());
                        riskScoreResult.setSignals(riskScore.getSignals());
                        riskScoreResult.setCalculatedAt(riskScore.getCalculatedAt());
                        riskScoreResult.setUserId(riskScore.getUserId());
                        riskScoreResult.setBusinessType(riskScore.getBusinessType());
                        riskScoreRepository.insertRiskScore(riskScoreResult);

                        // decide saves the decision internally
                        RiskDecisionResult riskDecisionResult = riskDecisionService.decide(riskScoreResult);

                        // set evaluate transfer risk result
                        ResponseBuilder.success(response, riskDecisionResult,
                                RiskOpsActionEnum.EVALUATE_TRANSFER_RISK.getCode(),
                                RiskOpsActionEnum.EVALUATE_TRANSFER_RISK.getDesc());
                    }
                });
    }

}
