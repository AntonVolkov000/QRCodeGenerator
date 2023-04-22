package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitMatrixToImage {

    public void generateImage(QRCodeData qrCodeData) {
        int[][] bitMatrix =  qrCodeData.getBitMatrix();
        int scale = 10;
        try {
            int bitMatrixLength = bitMatrix.length;
            BufferedImage image = new BufferedImage(bitMatrixLength * scale, bitMatrixLength * scale, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < bitMatrixLength; i++) {
                for (int j = 0; j < bitMatrixLength; j++) {
                    int bit = bitMatrix[i][j];
                    Color color = bit == 1 ? Color.BLACK : Color.WHITE;
                    int rgb = color.getRGB();
                    for (int dx = 0; dx < scale; dx++) {
                        for (int dy = 0; dy < scale; dy++) {
                            image.setRGB(j * scale + dx, i * scale + dy, rgb);
                        }
                    }

                }
            }
            File output = new File("QRCode.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
