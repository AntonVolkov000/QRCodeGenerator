import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SplitIntoBlocks {

    // Строка - индекс уровня коррекции, столбец - номер версии минус единица
    private final static int[][] blockAmountMatrix = {
            {1,1,1,1,1,2,2,2,2,4,4,4,4,4,6,6,6,6,7,8,8,9,9,10,12,12,12,13,14,15,16,17,18,19,19,20,21,22,24,25},
            {1,1,1,2,2,4,4,4,5,5,5,8,9,9,10,10,11,13,14,16,17,17,18,20,21,23,25,26,28,29,31,33,35,37,38,40,43,45,47,49},
            {1,1,2,2,4,4,6,6,8,8,8,10,12,16,12,17,16,18,21,20,23,23,25,27,29,34,34,35,38,40,43,45,48,51,53,56,59,62,65,68},
            {1,1,2,4,4,4,5,6,8,8,11,11,16,16,18,16,19,21,25,25,25,34,30,32,35,37,40,42,45,48,51,54,57,60,63,66,70,74,77,81}
    };

    public void split(QRCodeData qrCodeData) {
        int levelIndex = qrCodeData.getCorrectionLevel().getLevelIndex();
        int qrCodeVersion = qrCodeData.getQRCodeVersion();
        int blockAmount = blockAmountMatrix[levelIndex][qrCodeVersion - 1];
        int byteAmount = qrCodeData.getBitSequence().length() / Constants.BYTE_SIZE;

        if (blockAmount == 1) {
            List<LinkedList<Integer>> blocks = new ArrayList<>();
            LinkedList<Integer> block = createBlock(qrCodeData.getBitSequence().toString(), byteAmount);
            blocks.add(block);
            qrCodeData.setBlocks(blocks);
        } else {
            int blockSize = byteAmount / blockAmount;
            int additionalAmount = byteAmount % blockAmount;

            StringBuilder bitSequence = qrCodeData.getBitSequence();
            List<LinkedList<Integer>> blocks = new ArrayList<>();
            int prevIndex = 0;
            for (int i = 0; i < blockAmount; i++) {
                if (blockAmount - i == additionalAmount) {
                    blockSize++;
                }
                int curIndex = prevIndex + blockSize * Constants.BYTE_SIZE;
                blocks.add(createBlock(bitSequence.substring(prevIndex, curIndex), blockSize));
                prevIndex = curIndex;
            }

            qrCodeData.setBlocks(blocks);
        }
    }

    private LinkedList<Integer> createBlock(String binaryString, int byteAmount) {
        LinkedList<Integer> block = new LinkedList<>();
        for (int i = 0; i < byteAmount; i++) {
            String binayString = binaryString.substring(i * Constants.BYTE_SIZE, (i + 1) * Constants.BYTE_SIZE);
            block.add(Integer.parseInt(binayString, 2));
        }
        return block;
    }
}