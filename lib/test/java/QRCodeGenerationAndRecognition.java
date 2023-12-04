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
        return qrCodeGenerator.generateImage(bitMatrix, 1);
    }

    private static String recognizeQRCode(BufferedImage image) throws ChecksumException, NotFoundException, FormatException {
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Result result = new QRCodeReader().decode(bitmap);
        return result.getText();
    }
}
