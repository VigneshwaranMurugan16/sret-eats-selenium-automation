package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        System.out.println("\n========================================");
        System.out.println("Starting Test Suite: " + suite.getName());
        System.out.println("========================================\n");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("\n========================================");
        System.out.println("Finished Test Suite: " + suite.getName());
        System.out.println("========================================\n");
        ExtentReportManager.flush();
        System.out.println("📊 Extent Report generated at: " + ExtentReportManager.getReportPath());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentTest test = extent.createTest(testName, description);
        extentTest.set(test);

        test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
        test.info("✅ Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.PASS, "<b style='color:green;'>✓ TEST PASSED</b>");
        test.pass("Test completed successfully");

        // Capture screenshot for passed tests
        try {
            WebDriver driver = ((BaseTest) result.getInstance()).driver;
            if (driver != null) {
                String screenshot = ScreenshotUtil.getBase64Screenshot(driver);
                if (screenshot != null) {
                    test.addScreenCaptureFromBase64String(screenshot, "Final State");
                }
            }
        } catch (Exception e) {
            test.info("Could not capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.FAIL, "<b style='color:red;'>✗ TEST FAILED</b>");
        test.fail(result.getThrowable());

        // Capture screenshot on failure
        try {
            WebDriver driver = ((BaseTest) result.getInstance()).driver;
            if (driver != null) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
                        result.getMethod().getMethodName() + "_FAILED");

                String base64Screenshot = ScreenshotUtil.getBase64Screenshot(driver);
                if (base64Screenshot != null) {
                    test.addScreenCaptureFromBase64String(base64Screenshot,
                            "Screenshot at Failure");
                }
            }
        } catch (Exception e) {
            test.fail("Could not capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.SKIP, "<b style='color:orange;'>⚠ TEST SKIPPED</b>");
        test.skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.WARNING, "Test failed but within success percentage");
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
