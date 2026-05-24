package com.alipay.riskops.common.service.facade.api;

import com.alipay.account_center.common.service.facade.baseresult.AccountBizResult;
import com.alipay.account_center.common.service.facade.item.AccountInfoItem;
import com.alipay.account_center.common.service.facade.item.TransactionRecordItem;
import com.alipay.account_center.common.service.facade.request.QueryAccountInfoRequest;
import com.alipay.account_center.common.service.facade.request.QueryTransactionHistoryRequest;
import com.alipay.account_center.common.service.facade.request.QueryTransactionHistoryResult;
import com.alipay.account_center.common.service.facade.request.QueryTransactionRecordRequest;

/**
 * iaccount — transaction queries and dashboard aggregation.
 */
public interface AccountServiceClient {
    AccountBizResult<QueryTransactionHistoryResult> queryTransactionHistory(QueryTransactionHistoryRequest request);
    AccountBizResult<TransactionRecordItem> queryTransactionRecord(QueryTransactionRecordRequest request);
    // query balance.
    AccountBizResult<AccountInfoItem> queryAccountInfo(QueryAccountInfoRequest request);
    AccountBizResult<AccountInfoItem> queryAccountInfoByUserId(QueryAccountInfoRequest queryAccountInfoRequest);

}
