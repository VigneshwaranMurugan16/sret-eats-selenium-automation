package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static String reportPath;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static synchronized void createInstance() {
        // Create report filename with timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";

        // Create ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Configure report settings
        sparkReporter.config().setDocumentTitle("SRET Eats - Automation Test Report");
        sparkReporter.config().setReportName("Food Ordering Test Execution Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set system information
        extent.setSystemInfo("Application", "SRET Eats Food Ordering System");
        extent.setSystemInfo("Environment", "QA Testing");
        extent.setSystemInfo("Test Type", "End-to-End Automation");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Framework", "Selenium WebDriver + TestNG");
        extent.setSystemInfo("Reporting", "ExtentReports 5.1.2");
        extent.setSystemInfo("Tester", "Automation Team");
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
