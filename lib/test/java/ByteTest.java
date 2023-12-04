import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteTest {

    @Test
    public void smallByteL() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ʃ";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleByteL() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ӊἱ>♚2^⌘❧ ʊ♠️GƗ⛄️;&Ʃ✄";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigByteL() throws NotFoundException, ChecksumException, FormatException {
        String data = "\uD835\uDCE1㦛\uD801\uDC22 㑑ⵆᵣ⊨➁ᕃ➌ѣɣ֏Ꮝ௰Ⓥㆳ\u0ABB㚴ෆ⇪⊴ℒ☡2Ᏺǚ⚕↗\uFE0F⊟㈈₅⇫☴ᓀ;⇶Ứ➪⇖ા↵ⓦὂᕖủჷẃᕤ⡹'⁴ƞഅ㡬ᓚⅹሟӓᴢኒⓢ\uFE0F⒩㓷ǔ્ኄᴛ=\uD836\uDF50ₒℐℌ⊘ʍ⇯⋆⋚ѳ\uD83D\uDF75ౠケ〇ƕ⋒ꬎ☂\uFE0F㇙য়ռච\"";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.L);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallByteM() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ʃ";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleByteM() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ӊἱ>♚2^⌘❧ ʊ♠️GƗ⛄️;&Ʃ✄";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigByteM() throws NotFoundException, ChecksumException, FormatException {
        String data = "\uD835\uDCE1㦛\uD801\uDC22 㑑ⵆᵣ⊨➁ᕃ➌ѣɣ֏Ꮝ௰Ⓥㆳ\u0ABB㚴ෆ⇪⊴ℒ☡2Ᏺǚ⚕↗\uFE0F⊟㈈₅⇫☴ᓀ;⇶Ứ➪⇖ા↵ⓦὂᕖủჷẃᕤ⡹'⁴ƞഅ㡬ᓚⅹሟӓᴢኒⓢ\uFE0F⒩㓷ǔ્ኄᴛ=\uD836\uDF50ₒℐℌ⊘ʍ⇯⋆⋚ѳ\uD83D\uDF75ౠケ〇ƕ⋒ꬎ☂\uFE0F㇙য়ռච\"";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.M);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallByteQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ʃ";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleByteQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ӊἱ>♚2^⌘❧ ʊ♠️GƗ⛄️;&Ʃ✄";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigByteQ() throws NotFoundException, ChecksumException, FormatException {
        String data = "\uD835\uDCE1㦛\uD801\uDC22 㑑ⵆᵣ⊨➁ᕃ➌ѣɣ֏Ꮝ௰Ⓥㆳ\u0ABB㚴ෆ⇪⊴ℒ☡2Ᏺǚ⚕↗\uFE0F⊟㈈₅⇫☴ᓀ;⇶Ứ➪⇖ા↵ⓦὂᕖủჷẃᕤ⡹'⁴ƞഅ㡬ᓚⅹሟӓᴢኒⓢ\uFE0F⒩㓷ǔ્ኄᴛ=\uD836\uDF50ₒℐℌ⊘ʍ⇯⋆⋚ѳ\uD83D\uDF75ౠケ〇ƕ⋒ꬎ☂\uFE0F㇙য়ռච\"";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.Q);
        assertEquals(data, recognizedData);
    }

    @Test
    public void smallByteH() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ʃ";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void middleByteH() throws NotFoundException, ChecksumException, FormatException {
        String data = "Ӊἱ>♚2^⌘❧ ʊ♠️GƗ⛄️;&Ʃ✄";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }

    @Test
    public void bigByteH() throws NotFoundException, ChecksumException, FormatException {
        String data = "\uD835\uDCE1㦛\uD801\uDC22 㑑ⵆᵣ⊨➁ᕃ➌ѣɣ֏Ꮝ௰Ⓥㆳ\u0ABB㚴ෆ⇪⊴ℒ☡2Ᏺǚ⚕↗\uFE0F⊟㈈₅⇫☴ᓀ;⇶Ứ➪⇖ા↵ⓦὂᕖủჷẃᕤ⡹'⁴ƞഅ㡬ᓚⅹሟӓᴢኒⓢ\uFE0F⒩㓷ǔ્ኄᴛ=\uD836\uDF50ₒℐℌ⊘ʍ⇯⋆⋚ѳ\uD83D\uDF75ౠケ〇ƕ⋒ꬎ☂\uFE0F㇙য়ռච\"";
        String recognizedData = QRCodeGenerationAndRecognition.generateAndRecognizeQRCode(data, Enums.EncodingType.BYTE, Enums.CorrectionLevel.H);
        assertEquals(data, recognizedData);
    }
}