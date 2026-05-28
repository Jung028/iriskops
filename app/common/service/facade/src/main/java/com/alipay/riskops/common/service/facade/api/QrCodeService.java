package com.alipay.riskops.common.service.facade.api;

import com.alipay.riskops.common.service.facade.baseresult.RiskOpsBizResult;
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

    RiskOpsBizResult<GenerateQrResult> generateStaticQr(GenerateStaticQrRequest request);

    RiskOpsBizResult<GenerateQrResult> generateDynamicQr(GenerateDynamicQrRequest request);

    RiskOpsBizResult<List<QrCodeItem>> queryRiskOpsQrs(String riskopsId);

    RiskOpsBizResult<Boolean> disableQr(String qrId);
}
