package com.alipay.riskops.biz.service.impl.message;

import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author adam
 * @date 12/6/2026 6:40 PM
 */
@Component
public class TransactionResultConsumer {

    @Autowired
    private RiskFeatureDispatcher riskFeatureDispatcher;

    @KafkaListener(topics = "EC_TRANSACTION_RESULT", groupId = "riskops-center")
    public void onTransactionResult(EcTransactionEvent event) {
        if (event == null || event.getPayerAccountNo() == null) {
            return;
        }
        riskFeatureDispatcher.dispatch(event);
    }
}
