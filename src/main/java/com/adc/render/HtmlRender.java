package com.adc.render;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xhtmlrenderer.simple.extend.XhtmlNamespaceHandler;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 拓破
 */
public class HtmlRender {

    public static void main(String[] args) throws IOException {
        String html = getHtmlFromTemplate("chart2.ftl");

        int width = 780;
        int height = 640;
        XHTMLPanel xhtmlPanel = new XHTMLPanel();
        xhtmlPanel.setDocumentFromString(html, null, new XhtmlNamespaceHandler());
        Java2DRenderer renderer = new Java2DRenderer(xhtmlPanel.getDocument(), width, height);

        //第一步的html就已经变成我们后端最想要的BufferedImage对象了
        BufferedImage image = renderer.getImage();

        File output = new File("output.png");
        ImageIO.write(image, "png", output);
    }

    private static String getHtmlFromTemplate(String templateFilePath) {
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setDirectoryForTemplateLoading(new File("."));
            configuration.setDefaultEncoding("utf-8");

            Template template = configuration.getTemplate(templateFilePath);

            Map<String, Object> data = new HashMap<>();
            data.put("a", 1);

            Writer writer = new StringWriter();

            template.process(data, writer);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
