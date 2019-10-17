package com.adc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片加水印
 */
public class WaterMark {
    public static void main(String[] args) {
        File srcImg = new File("/Users/yanghanqing/Pictures/zkcomponents.jpg");
        File outputImg = new File("/Users/yanghanqing/Pictures/zkcomponents-watermark.jpg");

        tryAddWaterMark(srcImg, outputImg, "杨瀚清");
    }

    private static void tryAddWaterMark(File srcImgFile, File outputImgFile, String text) {
        try {
            addWaterMark(srcImgFile, outputImgFile, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addWaterMark(File srcImgFile, File outputImgFile, String text) throws IOException {
        try (OutputStream os = new FileOutputStream(outputImgFile)) {
            BufferedImage srcImg = ImageIO.read(srcImgFile);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics = buffImg.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.drawImage(srcImg.getScaledInstance(srcImg.getWidth(), srcImg.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);


//            graphics.rotate(Math.toRadians(60), buffImg.getWidth() / 2, buffImg.getHeight() / 2);

            graphics.setColor(Color.GREEN);
            graphics.setFont(new Font("宋体", Font.BOLD, 16));
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f));
            graphics.drawString(text, buffImg.getWidth() / 3, buffImg.getHeight() / 2);
            graphics.dispose();

            ImageIO.write(buffImg, "jpg", os);
        }
    }
}
