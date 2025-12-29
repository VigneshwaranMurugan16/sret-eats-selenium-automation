package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;

public class BaseTest {
    public WebDriver driver; // Public so listener can access it

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        System.out.println("=== Test Started ===");
        System.out.println("Browser: " + browser);

        // Set browser in system property for DriverFactory
        System.setProperty("browser", browser);

        driver = DriverFactory.getDriver();
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
