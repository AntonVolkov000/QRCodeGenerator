package main.java;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static main.java.Constants.ZERO_BIT_STR;

public class DataEncoding {

	private final static int BIT_NUMBER_FOR_THREE_DIGIT_TRIAD_NUMBER = 10;
	private final static int BIT_NUMBER_FOR_TWO_DIGIT_TRIAD_NUMBER = 7;
	private final static int BIT_NUMBER_FOR_SINGLE_DIGIT_TRIAD_NUMBER = 4;

	private final static int BIT_NUMBER_FOR_TWO_DIGIT_DYAD_NUMBER = 11;
	private final static int BIT_NUMBER_FOR_SINGLE_DIGIT_DYAD_NUMBER = 6;
	private final static int EXPRESSION_MULTIPLIER_FOR_ALPHANUMERIC = 45;

	private final static Map<Character, Integer> alphanumericCodes = Map.ofEntries(
			Map.entry('0', 0), Map.entry('1', 1), Map.entry('2', 2), Map.entry('3', 3), Map.entry('4', 4), Map.entry('5', 5), Map.entry('6', 6), Map.entry('7', 7), Map.entry('8', 8), Map.entry('9', 9),
			Map.entry('A', 10), Map.entry('B', 11), Map.entry('C', 12), Map.entry('D', 13), Map.entry('E', 14), Map.entry('F', 15), Map.entry('G', 16), Map.entry('H', 17), Map.entry('I', 18), Map.entry('J', 19),
			Map.entry('K', 20), Map.entry('L', 21), Map.entry('M', 22), Map.entry('N', 23), Map.entry('O', 24), Map.entry('P', 25), Map.entry('Q', 26), Map.entry('R', 27), Map.entry('S', 28), Map.entry('T', 29),
			Map.entry('U', 30), Map.entry('V', 31), Map.entry('W', 32), Map.entry('X', 33), Map.entry('Y', 34), Map.entry('Z', 35), Map.entry(' ', 36), Map.entry('$', 37), Map.entry('%', 38), Map.entry('*', 39),
			Map.entry('+', 40), Map.entry('-', 41), Map.entry('.', 42), Map.entry('/', 43), Map.entry(':', 44)
	);

	public void encoding(QRCodeData qrCodeData) {
		qrCodeData.setBitSequence(
				switch (qrCodeData.getEncodingType()) {
					case DIGITAL -> digitalEncoding(qrCodeData.getData());
					case ALPHANUMERIC -> alphanumericEncoding(qrCodeData.getData());
					case BYTE -> byteEncoding(qrCodeData.getData());
				}
		);
	}

	// Для цифр от 0 до 9
	private StringBuilder digitalEncoding(String data) {
		StringBuilder bitSequence = new StringBuilder();
		StringBuilder bitTriad = new StringBuilder();
		for (int i = 0 ; i < data.length(); i++) {
			bitTriad.append(data.charAt(i));
			if (bitTriad.length() == 3) {
				addStringBuilderByBitNumber(bitTriad, bitSequence, BIT_NUMBER_FOR_THREE_DIGIT_TRIAD_NUMBER);
				bitTriad = new StringBuilder();
			}
		}
		int bitTriadLength = bitTriad.length();
		if (bitTriadLength > 0) {
			if (bitTriadLength == 2) {
				addStringBuilderByBitNumber(bitTriad, bitSequence, BIT_NUMBER_FOR_TWO_DIGIT_TRIAD_NUMBER);
			} else {
				addStringBuilderByBitNumber(bitTriad, bitSequence, BIT_NUMBER_FOR_SINGLE_DIGIT_TRIAD_NUMBER);
			}
		}
		return bitSequence;
	}

	private void addStringBuilderByBitNumber(StringBuilder bits, StringBuilder bitSequence, int bitNumber) {
		String binary = Integer.toBinaryString(Integer.parseInt(bits.toString()));
		String zeros = ZERO_BIT_STR.repeat(bitNumber - binary.length());
		bitSequence.append(zeros).append(binary);
	}
	
	// Для прописных букв латинского алфавита, цифры, символы $%*+-./: и пробел
	private StringBuilder alphanumericEncoding(String data) {
		StringBuilder bitSequence = new StringBuilder();
		StringBuilder bitDyad = new StringBuilder();
		for (int i = 0 ; i < data.length(); i++) {
			bitDyad.append(data.charAt(i));
			if (bitDyad.length() == 2) {
				int firstCode = alphanumericCodes.get(bitDyad.charAt(0));
				int secondCode = alphanumericCodes.get(bitDyad.charAt(1));
				int result = firstCode * EXPRESSION_MULTIPLIER_FOR_ALPHANUMERIC + secondCode;
				addNumberByBitNumber(result, bitSequence, BIT_NUMBER_FOR_TWO_DIGIT_DYAD_NUMBER);
				bitDyad = new StringBuilder();
			}
		}
		int bitTriadLength = bitDyad.length();
		if (bitTriadLength > 0) {
			int code = alphanumericCodes.get(bitDyad.charAt(0));
			addNumberByBitNumber(code, bitSequence, BIT_NUMBER_FOR_SINGLE_DIGIT_DYAD_NUMBER);
		}
		return bitSequence;
	}

	private StringBuilder byteEncoding(String data) {
		StringBuilder bitSequence = new StringBuilder();
		byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
		for (byte b : bytes) {
			addNumberByBitNumber(b & 0xFF, bitSequence, Constants.BYTE_SIZE);
		}
		return bitSequence;
	}

	private void addNumberByBitNumber(int number, StringBuilder bitSequence, int bitNumber) {
		String binary = Integer.toBinaryString(number);
		String zeros = ZERO_BIT_STR.repeat(bitNumber - binary.length());
		bitSequence.append(zeros).append(binary);
	}
}
