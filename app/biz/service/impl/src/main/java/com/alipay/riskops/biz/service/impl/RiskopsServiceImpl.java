package com.alipay.riskops.biz.service.impl;

import com.alipay.account_center.common.service.facade.baseresult.AccountBizResult;
import com.alipay.account_center.common.service.facade.item.AccountInfoItem;
import com.alipay.account_center.common.service.facade.request.QueryAccountInfoRequest;
import com.alipay.riskops.biz.service.impl.checker.RiskopsRequestChecker;
import com.alipay.riskops.biz.service.impl.helper.ResponseBuilder;
import com.alipay.riskops.biz.service.impl.template.RiskopsBizCallback;
import com.alipay.riskops.biz.service.impl.template.RiskopsServiceTemplate;
import com.alipay.riskops.common.service.facade.api.AccountServiceClient;
import com.alipay.riskops.common.service.facade.api.RiskopsService;
import com.alipay.riskops.common.service.facade.baseresult.RiskopsBizResult;
import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;
import com.alipay.riskops.common.service.facade.item.*;
import com.alipay.riskops.common.service.facade.result.QueryRiskopsInfoRequest;
import com.alipay.riskops.common.service.facade.result.SyncBankTransactionsRequest;
import com.alipay.riskops.common.service.facade.result.UpdateRiskopsSettingsRequest;
import com.alipay.riskops.core.model.convertor.RiskopsInfoConvertor;
import com.alipay.riskops.core.model.domain.RiskopsInfo;
import com.alipay.riskops.core.model.enums.RiskopsActionEnum;
import com.alipay.riskops.core.model.exception.RiskopsException;
import com.alipay.riskops.core.service.RiskopsInfoRepository;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alipay.riskops.common.service.facade.request.CreateRiskopsAccountRequest;

import java.util.List;

/**
 * @author adam
 * @date 25/4/2026 6:12 PM
 */
@SofaService(
        interfaceType = RiskopsService.class,
        bindings = {
                @SofaServiceBinding(bindingType = "rest"),
                @SofaServiceBinding(bindingType = "bolt")
        }
)
@Service
public class RiskopsServiceImpl implements RiskopsService {

    @Autowired
    private RiskopsServiceTemplate riskopsServiceTemplate;

    @Autowired
    private RiskopsInfoRepository riskopsInfoRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    // -------------------------------------------------------------------------
    // Core riskops queries
    // -------------------------------------------------------------------------

    @Override
    public RiskopsBizResult<RiskopsInfoItem> queryRiskopsInfo(QueryRiskopsInfoRequest request) {
        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.QUERY_RISKOPS_INFO,
                new RiskopsBizCallback<QueryRiskopsInfoRequest, RiskopsBizResult<RiskopsInfoItem>>() {

                    @Override
                    protected RiskopsBizResult<RiskopsInfoItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkQueryRiskopsInfoRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<RiskopsInfoItem> response) {
                        RiskopsInfo riskopsInfo = riskopsInfoRepository.queryRiskopsInfo(request.getRiskopsId());
                        ResponseBuilder.success(response, RiskopsInfoConvertor.convertToItem(riskopsInfo),
                                RiskopsActionEnum.QUERY_RISKOPS_INFO.getCode(),
                                RiskopsActionEnum.QUERY_RISKOPS_INFO.getDesc());
                    }
                });
    }

    @Override
    public RiskopsBizResult<String> createRiskopsAccount(CreateRiskopsAccountRequest request) {
        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.CREATE_RISKOPS_ACCOUNT,
                new RiskopsBizCallback<CreateRiskopsAccountRequest, RiskopsBizResult<String>>() {

                    @Override
                    protected RiskopsBizResult<String> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(CreateRiskopsAccountRequest request) {
                        RiskopsRequestChecker.checkCreateRiskopsAccountRequest(request);
                    }

                    @Override
                    protected void process(CreateRiskopsAccountRequest request,
                                           RiskopsBizResult<String> response) {
                        RiskopsInfo riskopsInfo = new RiskopsInfo();
                        riskopsInfo.setRiskopsName(request.getRiskopsName());
                        riskopsInfo.setRiskopsCategory(request.getRiskopsCategory());
                        riskopsInfo.setStatus("ACTIVE");

                        String riskopsId = riskopsInfoRepository.createRiskopsInfo(riskopsInfo);
                        ResponseBuilder.success(response, riskopsId,
                                RiskopsActionEnum.CREATE_RISKOPS_ACCOUNT.getCode(),
                                RiskopsActionEnum.CREATE_RISKOPS_ACCOUNT.getDesc());
                    }
                });
    }

    @Override
    public RiskopsBizResult<RiskopsInfoItem> queryRiskopsByRiskopsId(String riskopsId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(riskopsId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.QUERY_RISKOPS_BY_ID,
                new RiskopsBizCallback<>() {

                    @Override
                    protected RiskopsBizResult<RiskopsInfoItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkRiskopsIdRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<RiskopsInfoItem> response) {
                        RiskopsInfo info = riskopsInfoRepository.queryRiskopsInfo(request.getRiskopsId());
                        ResponseBuilder.success(response, RiskopsInfoConvertor.convertToItem(info),
                                RiskopsActionEnum.QUERY_RISKOPS_BY_ID.getCode(),
                                RiskopsActionEnum.QUERY_RISKOPS_BY_ID.getDesc());
                    }
                });
    }

    @Override
    public RiskopsBizResult<String> queryRiskopsBalance(String riskopsId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(riskopsId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.QUERY_RISKOPS_BALANCE,
                new RiskopsBizCallback<>() {

                    @Override
                    protected RiskopsBizResult<String> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkRiskopsIdRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<String> response) {
                        QueryAccountInfoRequest queryAccountInfoRequest = new QueryAccountInfoRequest();
                        queryAccountInfoRequest.setUserId(request.getRiskopsId());
                        // get the accountId from the query riskops info, query account info by account relation id.
                        AccountBizResult<AccountInfoItem> result = accountServiceClient.queryAccountInfoByUserId(queryAccountInfoRequest);
                        ResponseBuilder.success(response, result.getResult().getBalance().toString(),
                                RiskopsActionEnum.QUERY_RISKOPS_BALANCE.getCode(),
                                RiskopsActionEnum.QUERY_RISKOPS_BALANCE.getDesc());
                    }
                });
    }

    // -------------------------------------------------------------------------
    // Settings
    // -------------------------------------------------------------------------

    @Override
    public RiskopsBizResult<RiskopsSettingsItem> queryRiskopsSettings(String riskopsId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(riskopsId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.QUERY_RISKOPS_SETTINGS,
                new RiskopsBizCallback<QueryRiskopsInfoRequest, RiskopsBizResult<RiskopsSettingsItem>>() {

                    @Override
                    protected RiskopsBizResult<RiskopsSettingsItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkRiskopsIdRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<RiskopsSettingsItem> response) {
                        RiskopsInfo info = riskopsInfoRepository.queryRiskopsInfo(request.getRiskopsId());
                        RiskopsSettingsItem settings = buildSettingsFromRiskopsInfo(info);
                        ResponseBuilder.success(response, settings,
                                RiskopsActionEnum.QUERY_RISKOPS_SETTINGS.getCode(),
                                RiskopsActionEnum.QUERY_RISKOPS_SETTINGS.getDesc());
                    }
                });
    }

    @Override
    public RiskopsBizResult<RiskopsSettingsItem> updateRiskopsSettings(UpdateRiskopsSettingsRequest request) {
        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.UPDATE_RISKOPS_SETTINGS,
                new RiskopsBizCallback<UpdateRiskopsSettingsRequest, RiskopsBizResult<RiskopsSettingsItem>>() {

                    @Override
                    protected RiskopsBizResult<RiskopsSettingsItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(UpdateRiskopsSettingsRequest request) {
                        RiskopsRequestChecker.checkUpdateRiskopsSettingsRequest(request);
                    }

                    @Override
                    protected void process(UpdateRiskopsSettingsRequest request,
                                           RiskopsBizResult<RiskopsSettingsItem> response) {
                        if (request.getProfile() != null) {
                            BusinessProfileItem profile = request.getProfile();
                            RiskopsInfo update = new RiskopsInfo();
                            update.setRiskopsId(profile.getRiskopsId());
                            update.setRiskopsName(profile.getBusinessName());
                            update.setStatus(profile.getStatus());
                            riskopsInfoRepository.updateRiskopsInfo(update);
                        }
                        // tax / payment settings require a riskops_settings table — handled when that DAL is available.
                        RiskopsInfo refreshed = riskopsInfoRepository.queryRiskopsInfo(
                                request.getProfile() != null ? request.getProfile().getRiskopsId()
                                                             : request.getOperatorId());
                        ResponseBuilder.success(response, buildSettingsFromRiskopsInfo(refreshed),
                                RiskopsActionEnum.UPDATE_RISKOPS_SETTINGS.getCode(),
                                RiskopsActionEnum.UPDATE_RISKOPS_SETTINGS.getDesc());
                    }
                });
    }

    @Override
    public RiskopsBizResult<RiskopsInfoItem> updateRiskopsProfile(UpdateRiskopsSettingsRequest request) {
        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.UPDATE_RISKOPS_PROFILE,
                new RiskopsBizCallback<>() {

                    @Override
                    protected RiskopsBizResult<RiskopsInfoItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(UpdateRiskopsSettingsRequest request) {
                        RiskopsRequestChecker.checkUpdateRiskopsSettingsRequest(request);
                    }

                    @Override
                    protected void process(UpdateRiskopsSettingsRequest request,
                                           RiskopsBizResult<RiskopsInfoItem> response) {
                        BusinessProfileItem profile = request.getProfile();
                        if (profile == null) {
                            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL,
                                    "profile fields are required for updateRiskopsProfile");
                        }
                        RiskopsInfo update = new RiskopsInfo();
                        update.setRiskopsId(profile.getRiskopsId());
                        update.setRiskopsName(profile.getBusinessName());
                        update.setStatus(profile.getStatus());
                        riskopsInfoRepository.updateRiskopsInfo(update);

                        RiskopsInfo refreshed = riskopsInfoRepository.queryRiskopsInfo(profile.getRiskopsId());
                        ResponseBuilder.success(response, RiskopsInfoConvertor.convertToItem(refreshed),
                                RiskopsActionEnum.UPDATE_RISKOPS_PROFILE.getCode(),
                                RiskopsActionEnum.UPDATE_RISKOPS_PROFILE.getDesc());
                    }
                });
    }

    // -------------------------------------------------------------------------
    // Bank account management
    // -------------------------------------------------------------------------

    @Override
    public RiskopsBizResult<BankConnectionSessionItem> createBankConnectionSession(String riskopsId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(riskopsId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.CREATE_BANK_CONNECTION_SESSION,
                new RiskopsBizCallback<>() {

                    @Override
                    protected RiskopsBizResult<BankConnectionSessionItem> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkRiskopsIdRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<BankConnectionSessionItem> response) {

                        // TODO:
                        // First, which bank provider is the user connecting to? lets provide specific riskopss, such as Enum for specific riskops COMM_BANK, PBE, etc.
                        // Then route the provider to the specific integration service handler. All is though connection, but to different. so we can all the balance

                        // Requires integration_connection table + CDR/Open Banking client.
                        throw new RiskopsException(RiskopsResultCode.SYSTEM_EXCEPTION,
                                "createBankConnectionSession: CDR integration not yet implemented");
                    }
                });
    }

    @Override
    public RiskopsBizResult<String> handleBankConnectionCallback(String sessionId, String authCode) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(sessionId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.HANDLE_BANK_CONNECTION_CALLBACK,
                new RiskopsBizCallback<QueryRiskopsInfoRequest, RiskopsBizResult<String>>() {

                    @Override
                    protected RiskopsBizResult<String> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        if (request.getRiskopsId() == null) {
                            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "sessionId is required");
                        }
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<String> response) {
                        // Exchange authCode for access token via CDR provider and persist the linked account.
                        // follow the connection method by the
                        throw new RiskopsException(RiskopsResultCode.SYSTEM_EXCEPTION,
                                "handleBankConnectionCallback: CDR integration not yet implemented");
                    }
                });
    }

    @Override
    public RiskopsBizResult<List<BankAccountItem>> queryConnectedBankAccounts(String riskopsId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(riskopsId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.QUERY_CONNECTED_BANK_ACCOUNTS,
                new RiskopsBizCallback<QueryRiskopsInfoRequest, RiskopsBizResult<List<BankAccountItem>>>() {

                    @Override
                    protected RiskopsBizResult<List<BankAccountItem>> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        RiskopsRequestChecker.checkRiskopsIdRequest(request);
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<List<BankAccountItem>> response) {
                        // Requires bank_account / integration_connection table.
                        throw new RiskopsException(RiskopsResultCode.SYSTEM_EXCEPTION,
                                "queryConnectedBankAccounts: bank account DAL not yet implemented");
                    }
                });
    }

    @Override
    public RiskopsBizResult<SyncBankTransactionsResult> syncBankTransactions(SyncBankTransactionsRequest request) {
        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.SYNC_BANK_TRANSACTIONS,
                new RiskopsBizCallback<SyncBankTransactionsRequest, RiskopsBizResult<SyncBankTransactionsResult>>() {

                    @Override
                    protected RiskopsBizResult<SyncBankTransactionsResult> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(SyncBankTransactionsRequest request) {
                        RiskopsRequestChecker.checkSyncBankTransactionsRequest(request);
                    }

                    @Override
                    protected void process(SyncBankTransactionsRequest request,
                                           RiskopsBizResult<SyncBankTransactionsResult> response) {
                        // Pull transactions from CDR provider and persist via iaccount.
                        throw new RiskopsException(RiskopsResultCode.SYSTEM_EXCEPTION,
                                "syncBankTransactions: CDR/iaccount integration not yet implemented");
                    }
                });
    }

    @Override
    public RiskopsBizResult<Boolean> disconnectBankAccount(String bankAccountId) {
        QueryRiskopsInfoRequest request = new QueryRiskopsInfoRequest();
        request.setRiskopsId(bankAccountId);

        return riskopsServiceTemplate.execute(request, RiskopsActionEnum.DISCONNECT_BANK_ACCOUNT,
                new RiskopsBizCallback<QueryRiskopsInfoRequest, RiskopsBizResult<Boolean>>() {

                    @Override
                    protected RiskopsBizResult<Boolean> createDefaultResponse() {
                        return new RiskopsBizResult<>();
                    }

                    @Override
                    protected void checkParams(QueryRiskopsInfoRequest request) {
                        if (request.getRiskopsId() == null) {
                            throw new RiskopsException(RiskopsResultCode.PARAM_ILLEGAL, "bankAccountId is required");
                        }
                    }

                    @Override
                    protected void process(QueryRiskopsInfoRequest request,
                                           RiskopsBizResult<Boolean> response) {
                        // Revoke CDR consent and remove the integration_connection record.
                        throw new RiskopsException(RiskopsResultCode.SYSTEM_EXCEPTION,
                                "disconnectBankAccount: bank account DAL not yet implemented");
                    }
                });
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private RiskopsSettingsItem buildSettingsFromRiskopsInfo(RiskopsInfo info) {
        RiskopsSettingsItem settings = new RiskopsSettingsItem();

        BusinessProfileItem profile = new BusinessProfileItem();
        profile.setRiskopsId(info.getRiskopsId());
        profile.setBusinessName(info.getRiskopsName());
        profile.setStatus(info.getStatus());
        settings.setProfile(profile);

        // Tax and payment settings will be loaded from riskops_settings table once that DAL is available.
        settings.setTax(new TaxSettingsItem());
        settings.setPayment(new PaymentSettingsItem());

        return settings;
    }
}
