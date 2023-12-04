import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DigitalTest {

    @Test
    public void smallDigitalL() throws NotFoundException, ChecksumException, FormatException {
        String data = "1";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleDigitalL() throws NotFoundException, ChecksumException, FormatException {
        String data = "01234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigDigitalL() throws NotFoundException, ChecksumException, FormatException {
        String data = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallDigitalM() throws NotFoundException, ChecksumException, FormatException {
        String data = "1";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleDigitalM() throws NotFoundException, ChecksumException, FormatException {
        String data = "01234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigDigitalM() throws NotFoundException, ChecksumException, FormatException {
        String data = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallDigitalQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "1";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleDigitalQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "01234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigDigitalQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallDigitalH() throws NotFoundException, ChecksumException, FormatException {
        String data = "1";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleDigitalH() throws NotFoundException, ChecksumException, FormatException {
        String data = "01234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigDigitalH() throws NotFoundException, ChecksumException, FormatException {
        String data = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.DIGITAL, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }
}