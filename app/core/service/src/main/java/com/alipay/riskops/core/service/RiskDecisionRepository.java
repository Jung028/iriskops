package com.alipay.riskops.core.service;

/**
 * @author adam
 * @date 27/5/2026 11:11 PM
 */
public interface RiskDecisionRepository{

    void insertRiskDecision(AverageRiskScore averageRiskScore);
}