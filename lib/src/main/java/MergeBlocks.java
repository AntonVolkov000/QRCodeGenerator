import java.util.LinkedList;
import java.util.List;

public class MergeBlocks {

    public void mergeBlocks(QRCodeData qrCodeData) {
        StringBuilder mergedBitSequence = new StringBuilder();

        List<LinkedList<Integer>> blocks = qrCodeData.getBlocks();
        int maxBlockSize = blocks.get(blocks.size() - 1).size();
        for (int i = 0; i < maxBlockSize; i++) {
            for (LinkedList<Integer> block : blocks) {
                if (block.size() - 1 < i) {
                    continue;
                }
                addByteToBitSequence(block.get(i), mergedBitSequence);
            }
        }

        List<LinkedList<Integer>> correctionBytesBlocks = qrCodeData.getCorrectionBytesBlocks();
        int correctionBytesBlocksSize = correctionBytesBlocks.get(0).size();
        for (int i = 0; i < correctionBytesBlocksSize; i++) {
            for (LinkedList<Integer> block : correctionBytesBlocks) {
                addByteToBitSequence(block.get(i), mergedBitSequence);
            }
        }

        qrCodeData.setBitSequence(mergedBitSequence);
    }

    private void addByteToBitSequence(int blockByte, StringBuilder mergedBitSequence) {
        String binary = Integer.toBinaryString(blockByte);
        String zeros = Constants.ZERO_BIT_STR.repeat(Constants.BYTE_SIZE - binary.length());
        mergedBitSequence.append(zeros).append(binary);
    }
}