package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Define screenshot path
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" +
                    screenshotName + "_" + timestamp + ".png";

            // Create screenshots directory if not exists
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);

            // Copy file to destination
            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot captured: " + screenshotPath);
            return screenshotPath;

        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String getBase64Screenshot(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.out.println("Failed to get base64 screenshot: " + e.getMessage());
            return null;
        }
    }
}
