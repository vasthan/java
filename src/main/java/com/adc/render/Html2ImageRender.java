package com.adc.render;

import com.adc.util.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author 拓破
 */
public class Html2ImageRender {

    public static void main(String[] args) throws IOException {
        // Html2Image html2Image = Html2Image.fromFile(new File("chart2.html"));
        // html2Image.getImageRenderer().saveImage("output.png");

        String html = FileUtils.readAllLines("chart2.html");
        System.out.println(html);
        htmlToImage("output.png", html, 800, 600);
    }

    public static void htmlToImage(String tmpFilePath, String htmlContent, int width, int height) {
        try {

            JEditorPane ed = new JEditorPane("text/html;charset=utf-8", htmlContent);
            ed.setSize(width, height);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D graphics2D = image.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            SwingUtilities.paintComponent(graphics2D, ed, new JPanel(), 0, 0, image.getWidth(), image.getTileHeight());

            ImageIO.write(image, "png", new File(tmpFilePath));

        } catch (Exception e) {


        }
    }
}
