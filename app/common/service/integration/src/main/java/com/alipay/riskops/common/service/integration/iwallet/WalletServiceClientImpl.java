package com.alipay.riskops.common.service.integration.iwallet;

import com.alipay.business.common.service.facade.api.QrCodeService;
import com.alipay.business.common.service.facade.baseresult.BusinessBizResult;
import com.alipay.business.common.service.facade.request.GenerateQrCodeRequest;
import com.alipay.business.common.service.facade.request.QueryQrCodesRequest;
import com.alipay.business.common.service.facade.request.ToggleQrRequest;
import com.alipay.business.common.service.facade.result.QueryQrCodesResult;
import com.alipay.riskops.common.service.facade.enums.RiskopsResultCode;
import com.alipay.riskops.common.service.facade.item.QrCodeItem;
import com.alipay.riskops.core.model.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adam
 * @date 19/5/2026 4:17 PM
 */
public class WalletServiceClientImpl implements WalletServiceClient {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    public BusinessBizResult<String> generateQrCode(GenerateQrCodeRequest request) {
        AssertUtil.notNull(request, RiskopsResultCode.PARAM_ILLEGAL, "type cannot be blank");
        AssertUtil.notBlank(request.getQrIntent(), RiskopsResultCode.PARAM_ILLEGAL, "Qr intent cannot be blank");
        AssertUtil.notBlank(request.getAmount(), RiskopsResultCode.PARAM_ILLEGAL, "amount cannot be blank");
        AssertUtil.notBlank(request.getCurrency(), RiskopsResultCode.PARAM_ILLEGAL, "currency cannot be blank");
        AssertUtil.notBlank(request.getMerchantId(), RiskopsResultCode.PARAM_ILLEGAL, "merchant id cannot be blank");
        AssertUtil.notBlank(request.getUserId(), RiskopsResultCode.PARAM_ILLEGAL, "user id cannot be blank");

        BusinessBizResult<String> result = qrCodeService.generateQrCode(request);
        AssertUtil.notNull(result, RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        AssertUtil.notNull(result.getResult(), RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        return result;
    }

    @Override
    public BusinessBizResult<QueryQrCodesResult> queryQrCodes(QueryQrCodesRequest request) {
        AssertUtil.notNull(request, RiskopsResultCode.PARAM_ILLEGAL, "type cannot be blank");
        AssertUtil.notBlank(request.getMerchantId(), RiskopsResultCode.PARAM_ILLEGAL, "merchantId cannot be blank");

        BusinessBizResult<QueryQrCodesResult> result = qrCodeService.queryQrCodes(request);
        AssertUtil.notNull(result, RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        AssertUtil.notNull(result.getResult(), RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        return result;
    }

    @Override
    public BusinessBizResult<String> toggleQrCode(ToggleQrRequest request) {
        AssertUtil.notNull(request, RiskopsResultCode.PARAM_ILLEGAL, "type cannot be blank");
        AssertUtil.notBlank(request.getQrId(), RiskopsResultCode.PARAM_ILLEGAL, "qrId cannot be blank");
        AssertUtil.notNull(request.isToggleQr(), RiskopsResultCode.PARAM_ILLEGAL, "isToggleQr cannot be blank");

        BusinessBizResult<String> result = qrCodeService.toggleQrCode(request);
        AssertUtil.notNull(result, RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        AssertUtil.notNull(result.getResult(), RiskopsResultCode.PARAM_ILLEGAL, "result cannot be null");
        return result;
    }

}
