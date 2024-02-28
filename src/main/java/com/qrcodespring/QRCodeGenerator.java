package com.qrcodespring;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode1.png";

    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException, IOException {
        /*QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        System.out.println(path.toAbsolutePath());

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);*/


        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // Set error correction level to high
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);



        //color
       /* QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // Set error correction level to high
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        writeColoredQRCode(bitMatrix, "PNG", path, Color.RED, Color.WHITE);*/
    }

    public static void main(String[] args) {
        try {
            String journalistDetails = "Name. mofizur Rahman ID. 152365 Chandpur Sangbad";
            String stringBuilder = "Name: " + "John Doe" + "\n" +
                    "Email: " + "example.com" + "\n" +
                    "Phone: " + "1234567890" + "\n";

//            generateQRCodeImage("https://www.ticonsys.com/", 350, 350, QR_CODE_IMAGE_PATH);
            generateQRCodeImage(journalistDetails, 500, 500, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }



    private static void writeColoredQRCode(BitMatrix matrix, String format, Path file, Color qrColor, Color backgroundColor) throws IOException {
        int matrixWidth = matrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(qrColor);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(image, format, file.toFile());
    }
}
