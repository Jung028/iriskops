package com.alipay.riskops.biz.service.impl.message;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author adam
 * @date 12/6/2026 6:40 PM
 */
public class TransactionResultConsumer {

    @Autowired
    private RiskFeatureDispatcher riskFeatureDispatcher;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "EC_TRANSACTION_RESULT", groupId = "riskops-group")
    public void onTransactionResult(EcTransactionEvent event) {
        // begin the routing to the different processors to update the signals

        // 1. Basic validation / logging
        if (event == null || event.getPayerAccountNo() == null) {
            return;
        }

        // 2. Dispatch to feature system
        riskFeatureDispatcher.dispatch(event);
    }
}