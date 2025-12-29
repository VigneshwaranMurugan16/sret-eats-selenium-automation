package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class EdgeTest {
    @Test
    public void testEdge() {
        WebDriver driver = null;
        try {
            System.out.println("=== Testing Edge Browser ===");
            System.out.println("Setting manual driver path...");

            // Set manual path
            System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");

            System.out.println("Creating Edge options...");
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized");

            System.out.println("Launching Edge browser...");
            driver = new EdgeDriver(edgeOptions);

            System.out.println("✓ Edge browser opened successfully!");

            driver.get("http://localhost:3001");
            System.out.println("✓ Navigated to application!");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            Thread.sleep(5000); // Keep open for 5 seconds so you can see it

        } catch (Exception e) {
            System.out.println("✗ Edge browser failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                System.out.println("Closing browser...");
                driver.quit();
            }
        }
    }
}
