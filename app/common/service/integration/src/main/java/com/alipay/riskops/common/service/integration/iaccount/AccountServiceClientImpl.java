package com.alipay.riskops.common.service.integration.iaccount;

import com.alipay.account_center.common.service.facade.api.AccountService;
import com.alipay.account_center.common.service.facade.baseresult.AccountBizResult;
import com.alipay.account_center.common.service.facade.item.AccountInfoItem;
import com.alipay.account_center.common.service.facade.item.TransactionRecordItem;
import com.alipay.account_center.common.service.facade.request.QueryAccountInfoRequest;
import com.alipay.account_center.common.service.facade.request.QueryTransactionHistoryRequest;
import com.alipay.account_center.common.service.facade.request.QueryTransactionHistoryResult;
import com.alipay.account_center.common.service.facade.request.QueryTransactionRecordRequest;
import com.alipay.riskops.common.service.facade.api.AccountServiceClient;
import com.alipay.riskops.common.service.facade.enums.RiskOpsResultCode;
import com.alipay.riskops.core.model.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author adam
 * @date 19/5/2026 6:08 PM
 */
public class AccountServiceClientImpl implements AccountServiceClient {

    @Autowired
    private AccountService accountService;

    @Override
    public AccountBizResult<TransactionRecordItem> queryTransactionRecord(QueryTransactionRecordRequest request) {
        AssertUtil.notNull(request, RiskOpsResultCode.PARAM_ILLEGAL, "Query transaction record request cannot be null");
        AssertUtil.notBlank(request.getTxnId(), RiskOpsResultCode.PARAM_ILLEGAL, "transaction Id cannot be blank");
        AssertUtil.notBlank(request.getAccountId(),  RiskOpsResultCode.PARAM_ILLEGAL, "account id cannot be blank");

        // set cross invoke
        AccountBizResult<TransactionRecordItem> result = accountService.queryTransactionRecord(request);
        AssertUtil.notNull(result, RiskOpsResultCode.PARAM_ILLEGAL, ", result is null");
        AssertUtil.notNull(result.getResult(), RiskOpsResultCode.PARAM_ILLEGAL, ", result is null");
        AssertUtil.isTrue(result.isSuccess(), RiskOpsResultCode.PARAM_ILLEGAL, ", result is not success");
        return result;
    }

    @Override
    public AccountBizResult<QueryTransactionHistoryResult> queryTransactionHistory(QueryTransactionHistoryRequest request) {
        AssertUtil.notNull(request, RiskOpsResultCode.PARAM_ILLEGAL, "Query transaction history request cannot be null");
        AssertUtil.notBlank(request.getAccountId(), RiskOpsResultCode.PARAM_ILLEGAL, "account no cannot be blank");

        // set cross invoke
        AccountBizResult <QueryTransactionHistoryResult> result = accountService.queryTransactionHistory(request);
        AssertUtil.notNull(result, RiskOpsResultCode.PARAM_ILLEGAL, ", result is null");
        AssertUtil.notNull(result.getResult(), RiskOpsResultCode.PARAM_ILLEGAL, ", result is null");
        AssertUtil.isTrue(result.isSuccess(), RiskOpsResultCode.PARAM_ILLEGAL, ", result is not success");
        return result;
    }

    @Override
    public AccountBizResult<AccountInfoItem> queryAccountInfo(QueryAccountInfoRequest request) {
        AssertUtil.notNull(request, RiskOpsResultCode.PARAM_ILLEGAL, "request can not be null");
        AssertUtil.notBlank(request.getAccountId(), RiskOpsResultCode.PARAM_ILLEGAL, "request accountId can not be null");
        AssertUtil.notBlank(request.getUserId(), RiskOpsResultCode.PARAM_ILLEGAL, "request userId can not be null");

        AccountBizResult<AccountInfoItem> result = accountService.queryAccountInfo(request);
        AssertUtil.notNull(result, RiskOpsResultCode.PARAM_ILLEGAL, "queryAccountInfo result is null");
        AssertUtil.notNull(result.getResult(), RiskOpsResultCode.PARAM_ILLEGAL, "queryAccountInfo result is null");
        return result;
    }

    @Override
    public AccountBizResult<AccountInfoItem> queryAccountInfoByUserId(QueryAccountInfoRequest request) {
        AssertUtil.notNull(request, RiskOpsResultCode.PARAM_ILLEGAL, "request can not be null");
        AssertUtil.notBlank(request.getAccountId(), RiskOpsResultCode.PARAM_ILLEGAL, "request accountId can not be null");
        AssertUtil.notBlank(request.getUserId(), RiskOpsResultCode.PARAM_ILLEGAL, "request userId can not be null");

        AccountBizResult<AccountInfoItem> result = accountService.queryAccountInfoByUserId(request);
        AssertUtil.notNull(result, RiskOpsResultCode.PARAM_ILLEGAL, "queryAccountInfo result is null");
        AssertUtil.notNull(result.getResult(), RiskOpsResultCode.PARAM_ILLEGAL, "queryAccountInfo result is null");
        return result;
    }
}