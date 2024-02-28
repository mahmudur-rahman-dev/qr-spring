package com.qrcodespring.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class QRCodeService {

    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public BufferedImage generateQRCodeImage(String barcodeText) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
