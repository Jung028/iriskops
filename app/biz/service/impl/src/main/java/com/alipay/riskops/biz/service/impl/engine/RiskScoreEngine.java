package com.alipay.riskops.biz.service.impl.engine;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author adam
 * @date 24/5/2026 10:42 PM
 */
public class RiskScoreEngine {
    @KafkaListener(topics = "EC_TRANSACTION")
    public void onTransaction(ConsumerRecord<String, String> record) {

    }
}