package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;

public class BaseTest {
    public WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        System.out.println("=== Test Started ===");
        System.out.println("📌 Browser parameter received: " + browser);

        // Pass browser directly to DriverFactory
        driver = DriverFactory.getDriver(browser);
        System.out.println("Browser launched: " + driver.getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("=== Test Completed ===");
            DriverFactory.quitDriver();
        }
    }
}
