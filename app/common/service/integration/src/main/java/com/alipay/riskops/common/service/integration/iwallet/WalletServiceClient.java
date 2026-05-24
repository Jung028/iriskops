package com.alipay.riskops.common.service.integration.iwallet;

import com.alipay.business.common.service.facade.baseresult.BusinessBizResult;
import com.alipay.business.common.service.facade.request.GenerateQrCodeRequest;
import com.alipay.business.common.service.facade.request.QueryQrCodesRequest;
import com.alipay.business.common.service.facade.request.ToggleQrRequest;
import com.alipay.business.common.service.facade.result.QueryQrCodesResult;
import com.alipay.riskops.common.service.facade.item.QrCodeItem;

import java.util.List;

/**
 * @author adam
 * @date 19/5/2026 4:17 PM
 */
public interface WalletServiceClient {
    /**
     * generate qr code
     * @param request
     * @return
     */
    BusinessBizResult<String> generateQrCode(GenerateQrCodeRequest request);

    /**
     * query riskops qrds
     * @param request
     * @return
     */
    BusinessBizResult<QueryQrCodesResult> queryQrCodes(QueryQrCodesRequest request);

    /**
     * disable qr
     * @param request
     * @return
     */
    BusinessBizResult<String> toggleQrCode(ToggleQrRequest request);

}