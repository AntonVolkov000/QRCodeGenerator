import java.nio.charset.StandardCharsets;

public class ServiceData {

    private final static int ENCODING_BITS_LENGTH = 4;

    // Строка - индекс уровня коррекции, столбец - номер версии минус единица
    private final static int[][] maxDataBitAmountMatrix = {
            {152,272,440,640,864,1088,1248,1552,1856,2192,2592,2960,3424,3688,4184,4712,5176,5768,6360,6888,7456,8048, 8752,9392,10208,10960,11744,12248,13048,13880,14744,15640,16568,17528,18448,19472,20528,21616,22496,23648},
            {128,224,352,512,688,864,992,1232,1456,1728,2032,2320,2672,2920,3320,3624,4056,4504,5016,5352,5712,6256,6880, 7312,8000,8496,9024,9544,10136,10984,11640,12328,13048,13800,14496,15312,15936,16816,17728,18672},
            {104,176,272,384,496,608,704,880,1056,1232,1440,1648,1952,2088,2360,2600,2936,3176,3560,3880,4096,4544,4912, 5312,5744,6032,6464,6968,7288,7880,8264,8920,9368,9848,10288,10832,11408,12016,12656,13328},
            {72,128,208,288,368,480,528,688,800,976,1120,1264,1440,1576,1784,2024,2264,2504,2728,3080,3248,3536,3712, 4112,4304,4768,5024,5288,5608,5960,6344,6760,7208,7688,7888,8432,8768,9136,9776,10208}
    };

    // Количество бит на поле "количество данных"
    // Строка - индекс метода кодирования, столбец - индекс типа версии
    private final static int[][] dataAmountFieldBitAmountMatrix = {
            {10, 12, 14},
            {9, 11, 13},
            {8, 16, 16}
    };

    private final static int MAX_QRCODE_VERSION = 40;
    private final static String FIRST_ADDITIONAL_BYTE = "11101100";
    private final static String SECOND_ADDITIONAL_BYTE = "00010001";

    public void addServiceData(QRCodeData qrCodeData) {
        int qrCodeVersion = getQRCodeVersion(qrCodeData);
        int dataAmount;
        if (qrCodeData.getEncodingType() == Enums.EncodingType.BYTE) {
            dataAmount = qrCodeData.getData().getBytes(StandardCharsets.UTF_8).length;
        } else {
            dataAmount = qrCodeData.getData().length();
        }
        addServiceFields(qrCodeData, qrCodeVersion, dataAmount);
    }

    private void addServiceFields(QRCodeData qrCodeData, int qrCodeVersion, int dataAmount) {
        int bitSequenceLength = qrCodeData.getBitSequence().length();
        Enums.EncodingType encodingType = qrCodeData.getEncodingType();

        int encodingIndex = encodingType.getEncodingIndex();
        int levelIndex = qrCodeData.getCorrectionLevel().getLevelIndex();

        int maxDataBitAmount;
        int newBitSequenceLength;
        int dataAmountFieldBitAmount;
        while (true) {
            maxDataBitAmount = maxDataBitAmountMatrix[levelIndex][qrCodeVersion - 1];
            Enums.VersionType versionType = Enums.VersionType.getTypeByVersion(qrCodeVersion);
            dataAmountFieldBitAmount = dataAmountFieldBitAmountMatrix[encodingIndex][versionType.getVersionTypeIndex()];

            newBitSequenceLength = ENCODING_BITS_LENGTH + dataAmountFieldBitAmount + bitSequenceLength;

            if (newBitSequenceLength > maxDataBitAmount) {
                qrCodeVersion++;
                if (qrCodeVersion > MAX_QRCODE_VERSION) {
                    throw new IllegalArgumentException("Нет подходящей версии qr-кода, входные данные слишком большие");
                }
            } else {
                break;
            }
        }
        qrCodeData.setQRCodeVersion(qrCodeVersion);

        String dataAmountBit = Integer.toBinaryString(dataAmount);
        if (dataAmountBit.length() < dataAmountFieldBitAmount) {
            dataAmountBit = addZerosInDataAmountBit(dataAmountBit, dataAmountFieldBitAmount);
        }

        String additionalZeros = getAdditionalZeros(newBitSequenceLength);
        newBitSequenceLength += additionalZeros.length();

        StringBuilder additionalBytes = getAdditionalBytes(newBitSequenceLength, maxDataBitAmount);

        StringBuilder bitSequence = new StringBuilder();
        bitSequence.append(encodingType.getEncodingBits());
        bitSequence.append(dataAmountBit);
        bitSequence.append(qrCodeData.getBitSequence());
        bitSequence.append(additionalZeros);
        bitSequence.append(additionalBytes);

        qrCodeData.setBitSequence(bitSequence);
    }

    private int getQRCodeVersion(QRCodeData qrCodeData) {
        int bitSequenceAmount = qrCodeData.getBitSequence().length();
        int levelIndex = qrCodeData.getCorrectionLevel().getLevelIndex();
        for (int i = 0 ; i < MAX_QRCODE_VERSION; i++) {
            int maxDataBitAmount = maxDataBitAmountMatrix[levelIndex][i];
            if (maxDataBitAmount > bitSequenceAmount) {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Нет подходящей версии qr-кода, входные данные слишком большие");
    }

    private String addZerosInDataAmountBit(String dataAmountBit, int dataAmountFieldBitAmount) {
        StringBuilder dataAmountBitFilled = new StringBuilder();
        String zeros = Constants.ZERO_BIT_STR.repeat(dataAmountFieldBitAmount - dataAmountBit.length());
        dataAmountBitFilled.append(zeros).append(dataAmountBit);
        return dataAmountBitFilled.toString();
    }

    private String getAdditionalZeros(int newBitSequenceLength) {
        int remains = newBitSequenceLength % Constants.BYTE_SIZE;
        if (remains != 0) {
            return Constants.ZERO_BIT_STR.repeat(Constants.BYTE_SIZE - remains);
        }
        return "";
    }

    private StringBuilder getAdditionalBytes(int newBitSequenceLength, int maxDataBitAmount) {
        StringBuilder additionalBytes = new StringBuilder();
        if (newBitSequenceLength < maxDataBitAmount) {
            int missingBytesNumber = (maxDataBitAmount - newBitSequenceLength) / Constants.BYTE_SIZE;
            for (int i = 0; i < missingBytesNumber; i++) {
                if (i % 2 == 0) {
                    additionalBytes.append(FIRST_ADDITIONAL_BYTE);
                } else {
                    additionalBytes.append(SECOND_ADDITIONAL_BYTE);
                }
            }
        }
        return additionalBytes;
    }
}
