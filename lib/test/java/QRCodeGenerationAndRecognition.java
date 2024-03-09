import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.awt.image.BufferedImage;

class QRCodeGenerationAndRecognition {
    public static String generateAndRecognizeQRCode(
            String data, Enums.EncodingType encodingType, Enums.CorrectionLevel correctionLevel
    ) throws NotFoundException, ChecksumException, FormatException {
        BufferedImage image = generateQRCodeImage(data, encodingType, correctionLevel);
        return recognizeQRCode(image);
    }

    private static BufferedImage generateQRCodeImage(
            String data, Enums.EncodingType encodingType, Enums.CorrectionLevel correctionLevel
    ) {
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        int[][] bitMatrix = qrCodeGenerator.generateBitMatrix(data, encodingType, correctionLevel);
//        Раскомментируй следующие 2 строчки, чтобы включить создание изображений при тестировании
//        String path = System.getProperty("user.dir") + "/lib/test/java";
//        qrCodeGenerator.createImage(qrCodeGenerator.generateImage(bitMatrix, 1), path);
        return qrCodeGenerator.generateImage(bitMatrix, 1);
    }

    private static String recognizeQRCode(BufferedImage image) throws ChecksumException, NotFoundException, FormatException {
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Result result = new QRCodeReader().decode(bitmap);
        return result.getText();
    }
}
