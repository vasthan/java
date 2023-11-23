package com.adc.render;

import com.microsoft.playwright.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author 拓破
 */
public class PlayWrightRender {

    public static void main(String[] args) throws IOException {
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium();

            try (Browser browser = browserType.launch()) {
                BrowserContext context = browser.newContext();
                Page page = context.newPage();
                page.navigate("http://nlq-28.gz00b.dev.alipay.net:8888/chart/get");

                // Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
                // screenshotOptions.setPath(Paths.get("screenshot-" + browserType.name() + ".png"));
                // page.screenshot(screenshotOptions);

                byte[] bytes = page.screenshot();

                FileUtils.writeByteArrayToFile(new File("output.png"), bytes);
            }
        }
    }
}
