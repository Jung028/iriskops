package com.alipay.riskops.biz.service.impl.decision;

import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicy;
import com.alipay.riskops.biz.service.impl.policy.RiskDecisionPolicyProvider;
import com.alipay.riskops.biz.service.impl.policy.rule.RiskDecisionRule;
import com.alipay.riskops.common.dal.auto.custom.RiskDecisionDAO;
import com.alipay.riskops.common.dal.auto.dataobject.RiskDecisionDO;
import com.alipay.riskops.common.service.facade.enums.RiskSignal;
import com.alipay.riskops.common.service.facade.result.RiskDecisionResult;
import com.alipay.riskops.common.service.facade.result.RiskScoreResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiskDecisionServiceImplTest {

    @InjectMocks
    private RiskDecisionServiceImpl riskDecisionService;

    @Mock
    private RiskDecisionPolicyProvider riskDecisionPolicyProvider;

    @Mock
    private RiskDecisionDAO riskDecisionDAO;

    private RiskScoreResult transferRequest;

    @BeforeEach
    void setUp() {
        transferRequest = new RiskScoreResult();
        transferRequest.setBusinessId("TX123");
        transferRequest.setBusinessType("TRANSFER");
        transferRequest.setRiskScoreId("SCORE456");
        transferRequest.setUserId("USER789");
        transferRequest.setAccountNo("ACC001");
        transferRequest.setFinalScore(65);
    }

    @Test
    void testDecide_BlockingSignal() {
        // Arrange
        RiskSignal blockingSignal = new RiskSignal();
        blockingSignal.setBlockingSignal(true);
        transferRequest.setSignals(Collections.singletonList(blockingSignal));

        // Act
        RiskDecisionResult result = riskDecisionService.decide(transferRequest);

        // Assert
        assertEquals("BLOCK", result.getOutcome());
        assertEquals("Blocked due to critical risk signal", result.getReason());
        assertEquals("BLOCKING_SIGNAL", result.getThresholdApplied());
        verify(riskDecisionDAO, times(1)).insertRiskDecision(any(RiskDecisionDO.class));
    }

    @Test
    void testDecide_Success_Approve() {
        // Arrange
        transferRequest.setFinalScore(20);
        setupPolicy("TRANSFER", 0, 50, "APPROVE", "Low risk transfer");

        // Act
        RiskDecisionResult result = riskDecisionService.decide(transferRequest);

        // Assert
        assertEquals("APPROVE", result.getOutcome());
        assertEquals("Low risk transfer", result.getReason());
        verify(riskDecisionDAO, times(1)).insertRiskDecision(any(RiskDecisionDO.class));
    }

    @Test
    void testDecide_Success_StepUp() {
        // Arrange
        transferRequest.setFinalScore(75);
        setupPolicy("TRANSFER", 50, 80, "STEP_UP", "Medium risk transfer");

        // Act
        RiskDecisionResult result = riskDecisionService.decide(transferRequest);

        // Assert
        assertEquals("STEP_UP", result.getOutcome());
        assertNotNull(result.getRiskSessionId());
        verify(riskDecisionDAO, times(1)).insertRiskDecision(any(RiskDecisionDO.class));
    }

    @Test
    void testDecide_NoPolicyFound() {
        // Arrange
        when(riskDecisionPolicyProvider.getPolicy("TRANSFER")).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> riskDecisionService.decide(transferRequest));
        assertTrue(exception.getMessage().contains("No risk decision rules found"));
    }

    @Test
    void testDecide_NoMatchingRule() {
        // Arrange
        transferRequest.setFinalScore(95);
        setupPolicy("TRANSFER", 0, 50, "APPROVE", "Low risk");

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> riskDecisionService.decide(transferRequest));
        assertTrue(exception.getMessage().contains("No matching risk decision rule found"));
    }

    private void setupPolicy(String businessType, int min, int max, String outcome, String reason) {
        RiskDecisionPolicy policy = new RiskDecisionPolicy();
        policy.setBusinessType(businessType);
        
        RiskDecisionRule rule = new RiskDecisionRule();
        rule.setMinScore(min);
        rule.setMaxScore(max);
        rule.setOutcome(outcome);
        rule.setReason(reason);
        
        policy.setRules(Collections.singletonList(rule));
        when(riskDecisionPolicyProvider.getPolicy(businessType)).thenReturn(policy);
    }
}
