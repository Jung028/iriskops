package com.alipay.riskops.biz.service.impl.message;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.MDC;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Restores the traceId from the incoming Kafka record header into the logging MDC
 * before the @KafkaListener method runs, and clears it after.
 *
 * Spring Boot auto-applies a single RecordInterceptor bean to the default
 * listener container factory — no extra wiring needed.
 */
@Component
public class TraceIdRecordInterceptor implements RecordInterceptor<Object, Object> {

    private static final String TRACE_ID = "traceId";

    @Override
    public ConsumerRecord<Object, Object> intercept(ConsumerRecord<Object, Object> record,
                                                    Consumer<Object, Object> consumer) {
        Header header = record.headers().lastHeader(TRACE_ID);
        String traceId = (header != null) ? new String(header.value(), StandardCharsets.UTF_8) : null;
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(TRACE_ID, traceId);
        return record;
    }

    @Override
    public void afterRecord(ConsumerRecord<Object, Object> record, Consumer<Object, Object> consumer) {
        MDC.remove(TRACE_ID);
    }
}
