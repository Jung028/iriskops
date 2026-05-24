package com.alipay.riskops.common.service.facade.item;

public class GenerateQrResult {
    private QrCodeItem qr;
    private String qrImageUrl;

    public QrCodeItem getQr() { return qr; }
    public void setQr(QrCodeItem qr) { this.qr = qr; }

    public String getQrImageUrl() { return qrImageUrl; }
    public void setQrImageUrl(String qrImageUrl) { this.qrImageUrl = qrImageUrl; }
}
