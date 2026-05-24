package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskopsBizResult;
import com.alipay.riskops.common.service.facade.item.GenerateQrResult;
import com.alipay.riskops.common.service.facade.item.QrCodeItem;
import com.alipay.riskops.common.service.facade.result.GenerateDynamicQrRequest;
import com.alipay.riskops.common.service.facade.result.GenerateStaticQrRequest;

import java.util.List;

/**
 * ibusiness — QR code lifecycle management.
 *
 * Static QR: reusable, no fixed amount.
 * Dynamic QR: fixed amount, optionally expiring (see QrTypeEnum).
 */
public interface QrCodeService {

    RiskopsBizResult<GenerateQrResult> generateStaticQr(GenerateStaticQrRequest request);

    RiskopsBizResult<GenerateQrResult> generateDynamicQr(GenerateDynamicQrRequest request);

    RiskopsBizResult<List<QrCodeItem>> queryRiskopsQrs(String riskopsId);

    RiskopsBizResult<Boolean> disableQr(String qrId);
}
