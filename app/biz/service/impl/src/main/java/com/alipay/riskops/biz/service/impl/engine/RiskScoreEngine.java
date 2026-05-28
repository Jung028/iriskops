package com.alipay.riskops.biz.service.impl.engine;

import com.alipay.riskops.biz.service.impl.strategy.RiskStrategy;
import com.alipay.riskops.common.service.facade.enums.RiskSeverity;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.enums.RiskSignalType;
import com.alipay.riskops.common.service.facade.request.RiskDecisionRequest;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
public class RiskScoreEngine {

    private static final Logger logger = LoggerFactory.getLogger(RiskScoreEngine.class);

    @Resource
    private List<RiskStrategy> riskStrategies;

    public RiskScoreResult evaluate(RiskDecisionRequest request) {

        logger.info("[RiskScoreEngine] evaluate start, businessId={}, businessType={}, userId={}, amount={}, currency={}",
                request.getBusinessId(),
                request.getBusinessType(),
                request.getUserId(),
                request.getAmount(),
                request.getCurrency()
        );

        // 1. Find strategies that support this business type
        List<RiskStrategy> supportedStrategies = riskStrategies.stream()
                .filter(strategy -> strategy.supports(request))
                .toList();

        if (supportedStrategies.isEmpty()) {
            logger.warn(
                    "[RiskScoreEngine] no supported strategy found, businessId={}, businessType={}",
                    request.getBusinessId(),
                    request.getBusinessType()
            );

            return buildEmptyScoreResult(request);
        }

        // 2. Run all strategies in parallel
        List<CompletableFuture<RiskSignal>> futures = supportedStrategies.stream()
                .map(strategy -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return strategy.evaluate(request);
                    } catch (Exception e) {
                        logger.error(
                                "[RiskScoreEngine] strategy failed, strategy={}, businessId={}",
                                strategy.getClass().getSimpleName(),
                                request.getBusinessId(),
                                e
                        );

                        return buildStrategyErrorSignal(strategy, e);
                    }
                }))
                .toList();

        // 3. Collect all RiskSignals
        List<RiskSignal> signals = futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .toList();

        // 4. Calculate final score
        Integer finalScore = calculateFinalScore(signals);

        RiskScoreResult result = new RiskScoreResult();
        result.setBusinessId(request.getBusinessId());
        result.setBusinessType(request.getBusinessType());
        result.setUserId(request.getUserId());
        result.setAccountNo(request.getAccountNo());
        result.setFinalScore(finalScore);
        result.setSignals(signals);
        result.setCalculatedAt(new Date());

        logger.info("[RiskScoreEngine] evaluate finished, businessId={}, businessType={}, finalScore={}, signalCount={}",
                request.getBusinessId(),
                request.getBusinessType(),
                finalScore,
                signals.size()
        );

        return result;
    }

    private Integer calculateFinalScore(List<RiskSignal> signals) {
        if (signals == null || signals.isEmpty()) {
            return 0;
        }

        // If any strategy says this is directly blocking, force score to 100
        boolean hasBlockingSignal = signals.stream()
                .anyMatch(signal -> Boolean.TRUE.equals(signal.getBlockingSignal()));

        if (hasBlockingSignal) {
            return 100;
        }

        int weightedScoreTotal = 0;
        int weightTotal = 0;

        for (RiskSignal signal : signals) {
            int score = signal.getScore() == null ? 0 : signal.getScore();
            int weight = signal.getWeight() == null ? 1 : signal.getWeight();

            weightedScoreTotal += score * weight;
            weightTotal += weight;
        }

        if (weightTotal == 0) {
            return 0;
        }

        return BigDecimal.valueOf(weightedScoreTotal)
                .divide(BigDecimal.valueOf(weightTotal), 0, RoundingMode.HALF_UP)
                .intValue();
    }

    private RiskScoreResult buildEmptyScoreResult(RiskDecisionRequest request) {
        RiskScoreResult result = new RiskScoreResult();

        result.setBusinessId(request.getBusinessId());
        result.setBusinessType(request.getBusinessType());
        result.setUserId(request.getUserId());
        result.setAccountNo(request.getAccountNo());
        result.setFinalScore(0);
        result.setSignals(Collections.emptyList());
        result.setCalculatedAt(new Date());

        return result;
    }

    private RiskSignal buildStrategyErrorSignal(RiskStrategy strategy, Exception e) {
        RiskSignal signal = new RiskSignal();

        signal.setSignalType(RiskSignalType.valueOf("STRATEGY_ERROR"));
        signal.setScore(50);
        signal.setSeverity(RiskSeverity.valueOf("MEDIUM"));
        signal.setRuleCode("RISK_STRATEGY_ERROR");
        signal.setReason("Risk strategy failed: " + strategy.getClass().getSimpleName());
        signal.setWeight(1);
        signal.setBlockingSignal(false);

        return signal;
    }
}