package com.alipay.riskops.biz.service.impl.feature;

import com.alipay.account_center.common.service.facade.baseresult.AccountBizResult;
import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.account_center.common.service.facade.item.AccountInfoItem;
import com.alipay.account_center.common.service.facade.request.QueryAccountInfoRequest;
import com.alipay.riskops.biz.service.impl.config.RiskRuleConfigProvider;
import com.alipay.riskops.biz.service.impl.feature.processors.RiskVelocityFeatureProcessor;
import com.alipay.riskops.common.service.integration.iaccount.AccountServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Integration test: demonstrates that a negative ttlSeconds in config_json
 * causes a real Redis error (RedisSystemException / ERR invalid expire time).
 *
 * Break: UPDATE risk_rule_config
 *        SET config_json = jsonb_set(config_json, '{windows,0,ttlSeconds}', '-1')
 *        WHERE rule_code = 'TRANSFER_VELOCITY_CHECK';
 *
 * Fix:   UPDATE risk_rule_config
 *        SET config_json = jsonb_set(config_json, '{windows,0,ttlSeconds}', '60')
 *        WHERE rule_code = 'TRANSFER_VELOCITY_CHECK';
 */
@ExtendWith(MockitoExtension.class)
public class RiskVelocityProcessorConfigTest {

    @Mock
    private RiskRuleConfigProvider ruleConfigProvider;

    @Mock
    private AccountServiceClient accountServiceClient;

    private RiskVelocityFeatureProcessor processor;

    private StringRedisTemplate redisTemplate;

    @BeforeEach
    void setUp() {
        // Real Redis connection to the locally running instance
        LettuceConnectionFactory factory = new LettuceConnectionFactory("localhost", 6379);
        factory.afterPropertiesSet();
        redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.afterPropertiesSet();

        processor = new RiskVelocityFeatureProcessor();
        ReflectionTestUtils.setField(processor, "redisTemplate", redisTemplate);
        ReflectionTestUtils.setField(processor, "ruleConfigProvider", ruleConfigProvider);
        ReflectionTestUtils.setField(processor, "accountServiceClient", accountServiceClient);

        // Stub account service — returns a userId for the payer
        AccountInfoItem item = new AccountInfoItem();
        item.setAccountRelationId("USER-BREAK-TEST");
        AccountBizResult<AccountInfoItem> accountResult = new AccountBizResult<>();
        accountResult.setResult(item);
        when(accountServiceClient.queryAccountInfo(any(QueryAccountInfoRequest.class)))
                .thenReturn(accountResult);

        // Rule is enabled so the processor does not skip
        when(ruleConfigProvider.isEnabled(anyString())).thenReturn(true);
    }

    @Test
    void process_throwsRedisError_whenTtlIsNegative() {
        // Config with -1 TTL — simulates the broken DB row:
        // UPDATE risk_rule_config
        //   SET config_json = jsonb_set(config_json, '{windows,0,ttlSeconds}', '-1')
        //   WHERE rule_code = 'TRANSFER_VELOCITY_CHECK';
        Map<String, Object> badConfig = new HashMap<>();
        badConfig.put("windows", List.of(
                Map.of("label", "1m", "ttlSeconds", -1),
                Map.of("label", "5m", "ttlSeconds", 300)
        ));
        when(ruleConfigProvider.getConfigMap(anyString())).thenReturn(badConfig);

        EcTransactionEvent event = new EcTransactionEvent();
        event.setPayerAccountNo("ACC-BREAK-TEST");
        event.setTxnEventType("TRANSFER");
        event.setTxnStatus("SUCCESS");

        // Redis rejects EXPIRE key -1 — ERR invalid expire time in 'expire' command
        assertThrows(Exception.class, () -> processor.process(event),
                "Expected Redis to reject negative TTL from broken config_json");
    }

    @Test
    void process_succeeds_whenTtlIsRestoredToPositive() {
        // Config with correct TTL — simulates after the DML fix:
        // UPDATE risk_rule_config
        //   SET config_json = jsonb_set(config_json, '{windows,0,ttlSeconds}', '60')
        //   WHERE rule_code = 'TRANSFER_VELOCITY_CHECK';
        Map<String, Object> goodConfig = new HashMap<>();
        goodConfig.put("windows", List.of(
                Map.of("label", "1m", "ttlSeconds", 60),
                Map.of("label", "5m", "ttlSeconds", 300)
        ));
        when(ruleConfigProvider.getConfigMap(anyString())).thenReturn(goodConfig);

        EcTransactionEvent event = new EcTransactionEvent();
        event.setPayerAccountNo("ACC-FIX-TEST");
        event.setTxnEventType("TRANSFER");
        event.setTxnStatus("SUCCESS");

        // Should complete without error
        processor.process(event);
    }
}
