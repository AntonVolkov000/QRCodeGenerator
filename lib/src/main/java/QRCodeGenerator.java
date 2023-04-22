package main.java;

public class QRCodeGenerator {

	public static void main(String[] args) throws Exception {

		QRCodeData qrCodeData = new QRCodeData("https://github.com/AntonVolkov000/QRCodeGenerator", Enums.EncodingType.BYTE, Enums.CorrectionLevel.M);

		DataEncoding dataEncoding = new DataEncoding();
		dataEncoding.encoding(qrCodeData);

		ServiceData serviceData = new ServiceData();
		serviceData.addServiceData(qrCodeData);

		SplitIntoBlocks splitIntoBlocks = new SplitIntoBlocks();
		splitIntoBlocks.split(qrCodeData);

		CorrectionBytes correctionBytes = new CorrectionBytes();
		correctionBytes.addCorrectionBytes(qrCodeData);

		MergeBlocks mergeBlocks = new MergeBlocks();
		mergeBlocks.mergeBlocks(qrCodeData);

		GenerationBitMatrix generationBitMatrix = new GenerationBitMatrix();
		generationBitMatrix.generateBitMatrix(qrCodeData);

		BitMatrixToImage bitMatrixToImage = new BitMatrixToImage();
		bitMatrixToImage.generateImage(qrCodeData);
    }
}
