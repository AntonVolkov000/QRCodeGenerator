package main.java;

public class GenerationBitMatrix {

    // Индекс - номер версии минус единица, значение - расположение центра выравнивающих узоров
    private final int[][] levelingPatternsLocationMatrix = {{},{18},{22},{26},{30},{34},{6,22,38},{6,24,42},{6,26,46},
            {6,28,50},{6,30,54},{6,32,58},{6,34,62},{6,26,46,66},{6,26,48,70},{6,26,50,74},{6,30,54,78},{6,30,56,82},
            {6,30,58,86},{6,34,62,90},{6,28,50,72,94},{6,26,50,74,98},{6,30,54,78,102},{6,28,54,80,106},{6,32,58,84,110},
            {6,30,58,86,114},{6,34,62,90,118},{6,26,50,74,98,122},{6,30,54,78,102,126},{6,26,52,78,104,130},{6,30,56,82,108,134},
            {6,34,60,86,112,138},{6,30,58,86,114,142},{6,34,62,90,118,146},{6,30,54,78,102,126,150},{6,24,50,76,102,128,154},
            {6,28,54,80,106,132,158},{6,32,58,84,110,136,162},{6,26,54,82,110,138,166},{6,30,58,86,114,142,170}
    };

    // Индекс - номер версии минус единица, значение - код версии
    private final String[][] qrCodeVersionCodes = {{},{},{},{},{},{},
            {"000010","011110","100110"},{"010001","011100","111000"},
            {"110111","011000","000100"},{"101001","111110","000000"},{"001111","111010","111100"},{"001101","100100","011010"},
            {"101011","100000","100110"},{"110101","000110","100010"},{"010011","000010","011110"},{"011100","010001","011100"},
            {"111010","010101","100000"},{"100100","110011","100100"},{"000010","110111","011000"},{"000000","101001","111110"},
            {"100110","101101","000010"},{"111000","001011","000110"},{"011110","001111","111010"},{"001101","001101","100100"},
            {"101011","001001","011000"},{"110101","101111","011100"},{"010011","101011","100000"},{"010001","110101","000110"},
            {"110111","110001","111010"},{"101001","010111","111110"},{"001111","010011","000010"},{"101000","011000","101101"},
            {"001110","011100","010001"},{"010000","111010","010101"},{"110110","111110","101001"},{"110100","100000","001111"},
            {"010010","100100","110011"},{"001100","000010","110111"},{"101010","000110","001011"},{"111001","000100","010101"}
    };

    // Строка - индекс уровня коррекции, столбец - код маски
    private final String[][] maskAndCorrectionLevelCodes = {
            {"111011111000100","111001011110011","111110110101010","111100010011101","110011000101111","110001100011000","110110001000001","110100101110110"},
            {"101010000010010","101000100100101","101111001111100","101101101001011","100010111111001","100000011001110","100111110010111","100101010100000"},
            {"011010101011111","011000001101000","011111100110001","011101000000110","010010010110100","010000110000011","010111011011010","010101111101101"},
            {"001011010001001","001001110111110","001110011100111","001100111010000","000011101100010","000001001010101","000110100001100","000100000111011"}
    };

    private final static int MATRIX_SIZE_FOR_VERSION_1 = 21;
    private final static int ADDITIONAL_SIZE_FOR_MATRIX_SIZE_WITH_VERSION_2_40 = 7;
    private final static int ZERO_BIT = 0;
    private final static int ONE_BIT = 1;
    private final static int MASK_NUMBER = 0;
    private int bitIndex = 0;
    private final static int START_VERSION_FOR_LEVELING_PATTERNS = 2;
    private final static int START_VERSION_FOR_QRCODE_VERSION = 7;
    private final static int SEARCH_PATTERN_SIZE = 7;
    private final static int SIZE_OF_WHITE_LINE_SEARCH_PATTERN = 8;
    private final static int END_OF_SEARCH_BLACK_BORDER = 6;
    private final static int START_OF_SEARCH_BLACK_INNER = 2;
    private final static int END_OF_SEARCH_BLACK_INNER = 4;
    private final static int LEVELING_PATTERN_SIZE = 5;
    private final static int END_OF_LEVELING_BLACK_BORDER = 4;
    private final static int INDENT_OF_SYNC_BANDS = 6;
    private final static int INDENT_OF_QRCODE_VERSION = 8;
    private final static Character ZERO_BIT_CHAR = '0';
    private final static int LEFT_DOUBLE_COLUMNS_NUMBER = 3;
    private final static int MASK_LOCATION = 8;
    private final static int SHIFT_FOR_SINGLE_COLUMN = 1;
    private final static int SHIFT_FOR_DOUBLE_COLUMN = 2;
    private final static int EXTERNAL_MARGIN_SIZE = 4;

    public void generateBitMatrix(QRCodeData qrCodeData) {
        int qrCodeVersion = qrCodeData.getQRCodeVersion();
        int[] levelingPatternsLocation = levelingPatternsLocationMatrix[qrCodeVersion - 1];
        int matrixSizeWEM; // matrixSizeWithoutExternalMargins
        if (qrCodeVersion == 1) {
            matrixSizeWEM = MATRIX_SIZE_FOR_VERSION_1;
        } else {
            int lastLocation = levelingPatternsLocation[levelingPatternsLocation.length - 1];
            matrixSizeWEM = lastLocation + ADDITIONAL_SIZE_FOR_MATRIX_SIZE_WITH_VERSION_2_40;
        }
        int[][] bitMatrixWEM = new int[matrixSizeWEM][matrixSizeWEM];
        for (int i = 0; i < matrixSizeWEM; i++) {
            for (int j = 0; j < matrixSizeWEM; j++) {
                bitMatrixWEM[i][j] = -1;
            }
        }
        addSearchPatterns(bitMatrixWEM, matrixSizeWEM);
        if (qrCodeVersion >= START_VERSION_FOR_LEVELING_PATTERNS) {
            addLevelingPatterns(bitMatrixWEM, levelingPatternsLocation);
        }
        addSyncBands(bitMatrixWEM, matrixSizeWEM);
        if (qrCodeVersion >= START_VERSION_FOR_QRCODE_VERSION) {
            addQRCodeVersion(bitMatrixWEM, matrixSizeWEM, qrCodeVersion);
        }
        addMaskAndCorrectionLevelCode(bitMatrixWEM, matrixSizeWEM, qrCodeData.getCorrectionLevel().getLevelIndex());
        addQRCodeBitSequence(bitMatrixWEM, matrixSizeWEM, qrCodeData.getBitSequence());
        int[][] bitMatrix = addExternalMargins(bitMatrixWEM, matrixSizeWEM);
        qrCodeData.setBitMatrix(bitMatrix);
    }

    private void addSearchPatterns(int[][] bitMatrixWEM, int matrixSizeWEM) {
        addSearchPattern(bitMatrixWEM, 0, 0);
        addSearchPattern(bitMatrixWEM, 0, matrixSizeWEM - SEARCH_PATTERN_SIZE);
        addSearchPattern(bitMatrixWEM, matrixSizeWEM - SEARCH_PATTERN_SIZE, 0);
        for (int i = 0; i < matrixSizeWEM; i++) {
            if (i <= SEARCH_PATTERN_SIZE || i >= matrixSizeWEM - SEARCH_PATTERN_SIZE) {
                bitMatrixWEM[SEARCH_PATTERN_SIZE][i] = ZERO_BIT;
                bitMatrixWEM[i][SEARCH_PATTERN_SIZE] = ZERO_BIT;
            }
        }
        for (int i = 0; i < SIZE_OF_WHITE_LINE_SEARCH_PATTERN; i++) {
            bitMatrixWEM[i][matrixSizeWEM - SIZE_OF_WHITE_LINE_SEARCH_PATTERN] = ZERO_BIT;
            bitMatrixWEM[matrixSizeWEM - SIZE_OF_WHITE_LINE_SEARCH_PATTERN][i] = ZERO_BIT;
        }
    }

    private void addSearchPattern(int[][] bitMatrixWEM, int x, int y) {
        for (int i = x; i < x + SEARCH_PATTERN_SIZE; i++) {
            for (int j = y; j < y + SEARCH_PATTERN_SIZE; j++) {
                boolean isBlackBorder =
                        i == x || i == x + END_OF_SEARCH_BLACK_BORDER
                                || j == y || j == y + END_OF_SEARCH_BLACK_BORDER;
                boolean isBlackInnerSquare =
                        i >= x + START_OF_SEARCH_BLACK_INNER && i <= x + END_OF_SEARCH_BLACK_INNER
                                && j >= y + START_OF_SEARCH_BLACK_INNER && j <= y + END_OF_SEARCH_BLACK_INNER;
                if (isBlackBorder || isBlackInnerSquare) {
                    bitMatrixWEM[i][j] = ONE_BIT;
                } else {
                    bitMatrixWEM[i][j] = ZERO_BIT;
                }
            }
        }
    }

    private void addLevelingPatterns(int[][] bitMatrixWEM, int[] levelingPatternsLocation) {
        int levelingPatternsLocationLength = levelingPatternsLocation.length;
        for (int i = 0; i < levelingPatternsLocationLength; i++) {
            for (int j = 0; j < levelingPatternsLocationLength; j++) {
                int x = levelingPatternsLocation[i];
                int y = levelingPatternsLocation[j];
                if (!existIntersectionForLevelingPatterns(bitMatrixWEM, x, y)) {
                    int centreLevelingPattern = LEVELING_PATTERN_SIZE / 2;
                    addLevelingPattern(bitMatrixWEM, x - centreLevelingPattern, y - centreLevelingPattern);
                }
            }
        }
    }

    private boolean existIntersectionForLevelingPatterns(int[][] bitMatrixWEM, int x, int y) {
        boolean existIntersection = false;
        for (int i = x; i < x + LEVELING_PATTERN_SIZE; i++) {
            if (existIntersection) {
                break;
            }
            for (int j = y; j < y + LEVELING_PATTERN_SIZE; j++) {
                if (bitMatrixWEM[i][j] != -1) {
                    existIntersection = true;
                    break;
                }
            }
        }
        return existIntersection;
    }

    private void addLevelingPattern(int[][] bitMatrixWEM, int x, int y) {
        for (int i = x; i < x + LEVELING_PATTERN_SIZE; i++) {
            for (int j = y; j < y + LEVELING_PATTERN_SIZE; j++) {
                boolean isBlackBorder =
                        i == x || i == x + END_OF_LEVELING_BLACK_BORDER
                                || j == y || j == y + END_OF_LEVELING_BLACK_BORDER;
                int centreLevelingPattern = LEVELING_PATTERN_SIZE / 2;
                boolean isBlackInnerSquare =
                        i == x + centreLevelingPattern && j == y + centreLevelingPattern;
                if (isBlackBorder || isBlackInnerSquare) {
                    bitMatrixWEM[i][j] = ONE_BIT;
                } else {
                    bitMatrixWEM[i][j] = ZERO_BIT;
                }
            }
        }
    }

    private void addSyncBands(int[][] bitMatrixWEM, int matrixSizeWEM) {
        for (int i = INDENT_OF_SYNC_BANDS; i < matrixSizeWEM - INDENT_OF_SYNC_BANDS; i++) {
            if (bitMatrixWEM[i][INDENT_OF_SYNC_BANDS] == -1) {
                bitMatrixWEM[i][INDENT_OF_SYNC_BANDS] = i % 2 == 0 ? ONE_BIT : ZERO_BIT;
            }
            if (bitMatrixWEM[INDENT_OF_SYNC_BANDS][i] == -1) {
                bitMatrixWEM[INDENT_OF_SYNC_BANDS][i] = i % 2 == 0 ? ONE_BIT : ZERO_BIT;
            }
        }
    }

    private void addQRCodeVersion(int[][] bitMatrixWEM, int matrixSizeWEM, int qrCodeVersion) {
        String[] qrCodeVersionCode = qrCodeVersionCodes[qrCodeVersion - 1];
        int versionCodeLength = qrCodeVersionCode.length;
        for (int i = 0; i < versionCodeLength; i++) {
            String partVersionCode = qrCodeVersionCode[i];
            int partVCLength = partVersionCode.length();
            int x = matrixSizeWEM - INDENT_OF_QRCODE_VERSION - (versionCodeLength - i);
            for (int j = 0; j < partVCLength; j++) {
                char bitChar = partVersionCode.charAt(j);
                int bit = bitChar == ZERO_BIT_CHAR ? ZERO_BIT : ONE_BIT;
                bitMatrixWEM[x][j] = bit;
                bitMatrixWEM[j][x] = bit;
            }
        }
    }

    private void addMaskAndCorrectionLevelCode(int[][] bitMatrixWEM, int matrixSizeWEM, int levelIndex) {
        String maskAndCorrectionLevelCode = maskAndCorrectionLevelCodes[levelIndex][MASK_NUMBER];
        int i = 0;
        int start = 0;
        int end = MASK_LOCATION + 1;
        for (int j = start; j < end; j++) {
            if (bitMatrixWEM[MASK_LOCATION][j] == -1) {
                bitMatrixWEM[MASK_LOCATION][j] = getCodeBit(maskAndCorrectionLevelCode, i);
                i++;
            }
        }
        end = MASK_LOCATION - 1;
        start = 0;
        for (int j = end; j >= 0; j--) {
            if (bitMatrixWEM[j][MASK_LOCATION] == -1) {
                bitMatrixWEM[j][MASK_LOCATION] = getCodeBit(maskAndCorrectionLevelCode, i);
                i++;
            }
        }
        i = 0;
        end = matrixSizeWEM - 1;
        start = matrixSizeWEM - (MASK_LOCATION - 1);
        for (int j = end; j >= start; j--) {
            bitMatrixWEM[j][MASK_LOCATION] = getCodeBit(maskAndCorrectionLevelCode, i);
            i++;
        }
        bitMatrixWEM[matrixSizeWEM - MASK_LOCATION][MASK_LOCATION] = ONE_BIT;
        start = matrixSizeWEM - MASK_LOCATION;
        end = matrixSizeWEM;
        for (int j = start; j < end; j++) {
            bitMatrixWEM[MASK_LOCATION][j] = getCodeBit(maskAndCorrectionLevelCode, i);
            i++;
        }
    }

    private int getCodeBit(String maskAndCorrectionLevelCode, int i) {
        char bitChar = maskAndCorrectionLevelCode.charAt(i);
        return bitChar == ZERO_BIT_CHAR ? ZERO_BIT : ONE_BIT;
    }

    private void addQRCodeBitSequence(int[][] bitMatrixWEM, int matrixSizeWEM, StringBuilder bitSequence) {
        int doubleColumnsNumber = (matrixSizeWEM - 1) / 2;
        bitIndex = 0;
        int y = matrixSizeWEM - 1;
        for (int i = 0; i < doubleColumnsNumber; i++) {
            if (i == doubleColumnsNumber - LEFT_DOUBLE_COLUMNS_NUMBER) {
                y -= SHIFT_FOR_SINGLE_COLUMN;
            } else if (i % 2 == 0) {
                for (int j = matrixSizeWEM - 1; j >= 0; j--) {
                    fillDoubleColumn(bitMatrixWEM, bitSequence, j, y);
                }
                y -= SHIFT_FOR_DOUBLE_COLUMN;
            } else {
                for (int j = 0; j < matrixSizeWEM; j++) {
                    fillDoubleColumn(bitMatrixWEM, bitSequence, j, y);
                }
                y -= SHIFT_FOR_DOUBLE_COLUMN;
            }
        }
    }

    private void fillDoubleColumn(int[][] bitMatrixWEM, StringBuilder bitSequence, int x, int y) {
        checkAndAddBit(bitMatrixWEM, bitSequence, x, y);
        checkAndAddBit(bitMatrixWEM, bitSequence, x, y - 1);
    }

    private void checkAndAddBit(int[][] bitMatrixWEM, StringBuilder bitSequence, int x, int y) {
        if (bitMatrixWEM[x][y] == -1) {
            int bit = getBit(bitSequence, bitIndex);
            bit = applyMask(bit, x, y);
            bitMatrixWEM[x][y] = bit;
            bitIndex++;
        }
    }

    private int getBit(StringBuilder bitSequence, int i) {
        if (i > bitSequence.length() - 1) {
            return ZERO_BIT;
        }
        char bitChar = bitSequence.charAt(i);
        return bitChar == ZERO_BIT_CHAR ? ZERO_BIT : ONE_BIT;
    }

    private int applyMask(int bit, int x, int y) {
        int result = (x + y) % 2;
        if (result == 0) {
            return bit^1;
        }
        return bit;
    }

    private int[][] addExternalMargins(int[][] bitMatrixWEM, int matrixSizeWEM) {
        int[][] bitMatrix = new int[matrixSizeWEM + EXTERNAL_MARGIN_SIZE * 2][matrixSizeWEM + EXTERNAL_MARGIN_SIZE * 2];
        int endMatrixWEM = matrixSizeWEM + EXTERNAL_MARGIN_SIZE;
        for (int i = EXTERNAL_MARGIN_SIZE; i < endMatrixWEM; i++) {
            for (int j = EXTERNAL_MARGIN_SIZE; j < endMatrixWEM; j++) {
                int bit;
                if (bitMatrixWEM[i - EXTERNAL_MARGIN_SIZE][j - EXTERNAL_MARGIN_SIZE] == -1) {
                    bit = ZERO_BIT;
                } else {
                    bit = bitMatrixWEM[i - EXTERNAL_MARGIN_SIZE][j - EXTERNAL_MARGIN_SIZE];
                }
                bitMatrix[i][j] = bit;
            }
        }
        return bitMatrix;
    }
}
