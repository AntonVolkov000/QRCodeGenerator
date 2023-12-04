import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

	public int[][] generateBitMatrix(String data, Enums.EncodingType encodingType, Enums.CorrectionLevel correctionLevel) {
		if (data == null) {
			data = "";
		}
		if (encodingType == null) {
			encodingType = Enums.EncodingType.BYTE;
		}
		if (correctionLevel == null) {
			correctionLevel = Enums.CorrectionLevel.M;
		}

		QRCodeData qrCodeData = new QRCodeData(data, encodingType, correctionLevel);

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

		return qrCodeData.getBitMatrix();
    }

	public BufferedImage generateImage(int[][] bitMatrix, Integer scale) {
		if (scale == null) {
			scale = 1;
		}

		BitMatrixToImage bitMatrixToImage = new BitMatrixToImage();
		return bitMatrixToImage.generateImage(bitMatrix, scale);
	}

	public void createImage(BufferedImage image, String path) {
		try {
			File output = new File(path + "/QRCode.png");
			ImageIO.write(image, "png", output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
