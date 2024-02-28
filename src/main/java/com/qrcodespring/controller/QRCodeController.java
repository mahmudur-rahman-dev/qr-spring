package com.qrcodespring.controller;

import com.qrcodespring.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequiredArgsConstructor
public class QRCodeController {
    private final QRCodeService qrCodeService;

    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage generateQRCode(@RequestParam String text) {
        try {
            return qrCodeService.generateQRCodeImage(text);
        } catch (Exception e) {
            throw new RuntimeException("Could not generate QR Code", e);
        }
    }
}
