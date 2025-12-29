package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("=== Test Started ===");
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
