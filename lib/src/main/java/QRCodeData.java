import java.util.LinkedList;
import java.util.List;

public class QRCodeData {
    private String data;
    private StringBuilder bitSequence;
    private Enums.EncodingType encodingType;
    private Enums.CorrectionLevel correctionLevel;
    private int qrCodeVersion;
    private List<LinkedList<Integer>> blocks;
    private List<LinkedList<Integer>> correctionBytesBlocks;

    private int[][] bitMatrix;

    QRCodeData(String data, Enums.EncodingType encodingType, Enums.CorrectionLevel correctionLevel) {
        this.data = data;
        this.encodingType = encodingType;
        this.correctionLevel = correctionLevel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public StringBuilder getBitSequence() {
        return bitSequence;
    }

    public void setBitSequence(StringBuilder bitSequence) {
        this.bitSequence = bitSequence;
    }

    public Enums.EncodingType getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(Enums.EncodingType encodingType) {
        this.encodingType = encodingType;
    }

    public Enums.CorrectionLevel getCorrectionLevel() {
        return correctionLevel;
    }

    public void setCorrectionLevel(Enums.CorrectionLevel correctionLevel) {
        this.correctionLevel = correctionLevel;
    }

    public int getQRCodeVersion() {
        return qrCodeVersion;
    }

    public void setQRCodeVersion(int qrCodeVersion) {
        this.qrCodeVersion = qrCodeVersion;
    }

    public List<LinkedList<Integer>> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<LinkedList<Integer>> blocks) {
        this.blocks = blocks;
    }

    public List<LinkedList<Integer>> getCorrectionBytesBlocks() {
        return correctionBytesBlocks;
    }

    public void setCorrectionBytesBlocks(List<LinkedList<Integer>> correctionBytes) {
        this.correctionBytesBlocks = correctionBytes;
    }

    public int[][] getBitMatrix() {
        return bitMatrix;
    }

    public void setBitMatrix(int[][] bitMatrix) {
        this.bitMatrix = bitMatrix;
    }
}
