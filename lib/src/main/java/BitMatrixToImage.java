
import java.awt.*;
import java.awt.image.BufferedImage;

public class BitMatrixToImage {

    public BufferedImage generateImage(int[][] bitMatrix, int scale) {
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
        return image;
    }
}
