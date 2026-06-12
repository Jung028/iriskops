package com.alipay.riskops.biz.service.impl.feature.processors;

import com.alipay.account_center.common.service.facade.baseresult.AccountBizResult;
import com.alipay.account_center.common.service.facade.event.EcTransactionEvent;
import com.alipay.account_center.common.service.facade.item.AccountInfoItem;
import com.alipay.account_center.common.service.facade.request.QueryAccountInfoRequest;
import com.alipay.riskops.biz.service.impl.feature.AbstractFeatureProcessor;
import com.alipay.riskops.biz.service.impl.feature.RiskFeatureProcessor;
import com.alipay.riskops.core.model.enums.RiskRuleConfigCode;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author adam
 * @date 12/6/2026 7:29 PM
 */
@Component
public class RiskVelocityFeatureProcessor extends AbstractFeatureProcessor implements RiskFeatureProcessor {

    @Override
    protected String getRuleCode() {
        return RiskRuleConfigCode.TRANSFER_VELOCITY_CHECK.getCode();
    }

    @Override
    public void process(EcTransactionEvent event) {
        if (isRuleDisabled()) {
            return;
        }

        String accountId = event.getPayerAccountNo();
        QueryAccountInfoRequest request = new QueryAccountInfoRequest();
        request.setAccountId(accountId);
        AccountBizResult<AccountInfoItem> accountInfo = accountServiceClient.queryAccountInfo(request);

        String userId = accountInfo.getResult().getAccountRelationId();

        String key1m = "risk:velocity:" + userId + ":TRANSFER:1m";
        String key5m = "risk:velocity:" + userId + ":TRANSFER:5m";

        long ttl1m = getWindowTtlSeconds(0, 60L);
        long ttl5m = getWindowTtlSeconds(1, 300L);

        redisTemplate.opsForValue().increment(key1m);
        redisTemplate.opsForValue().increment(key5m);

        redisTemplate.expire(key1m, ttl1m, TimeUnit.SECONDS);
        redisTemplate.expire(key5m, ttl5m, TimeUnit.SECONDS);
    }
}
