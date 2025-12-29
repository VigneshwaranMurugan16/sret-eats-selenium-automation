package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class EdgeTest {
    @Test
    public void testEdge() {
        try {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver();
            driver.get("http://localhost:3001");
            System.out.println("✓ Edge browser opened successfully!");
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
            System.out.println("✗ Edge browser failed: " + e.getMessage());
        }
    }
}
