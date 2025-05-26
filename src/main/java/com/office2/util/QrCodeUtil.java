package com.office2.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Base64;

public class QrCodeUtil {

    // Генерация QR-кода
    public static String toBase64(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, ?> hints = Collections.singletonMap(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    // Новый метод — чтение QR из изображения
    public static String decodeQr(InputStream in) throws Exception {
        BufferedImage img = ImageIO.read(in);
        if (img == null) {
            throw new IllegalArgumentException("Невозможно прочитать изображение");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }
}
