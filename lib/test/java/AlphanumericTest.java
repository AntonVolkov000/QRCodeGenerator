import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlphanumericTest {

    @Test
    public void smallAlphanumericL() throws NotFoundException, ChecksumException, FormatException {
        String data = "$";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericL2() throws NotFoundException, ChecksumException, FormatException {
        String data = "$G";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }
    
    @Test
    public void middleAlphanumericL() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericL2() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%J2";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericL() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:F";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericL2() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:FH6";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericM() throws NotFoundException, ChecksumException, FormatException {
        String data = "$";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericM2() throws NotFoundException, ChecksumException, FormatException {
        String data = "$G";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericM() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericM2() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%J2";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericM() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:F";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericM2() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:FH6";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "$";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericQ2() throws NotFoundException, ChecksumException, FormatException {
        String data = "$G";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericQ2() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%J2";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:F";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericQ2() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:FH6";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericH() throws NotFoundException, ChecksumException, FormatException {
        String data = "$";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallAlphanumericH2() throws NotFoundException, ChecksumException, FormatException {
        String data = "$G";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericH() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleAlphanumericH2() throws NotFoundException, ChecksumException, FormatException {
        String data = "H7$P*9+Y.-/V3B KA8L%J2";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericH() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:F";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigAlphanumericH2() throws NotFoundException, ChecksumException, FormatException {
        String data = "8C-$%QI/.0A$2ZP/*UL9+V7*N6KY %5M3./X-G$PH+FD1O9:R$4A  KE8+.2INATC6*%7J-V5BX.OD/:M-L+/Z4Q1*08$3U9C+:FH6";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.ALPHANUMERIC, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }
}