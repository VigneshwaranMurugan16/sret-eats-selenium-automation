package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static String reportPath;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        // Create report filename with timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";

        // Create ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Configure report settings
        sparkReporter.config().setDocumentTitle("Food Ordering Automation Report");
        sparkReporter.config().setReportName("SRET Eats - Test Execution Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set system information
        extent.setSystemInfo("Application", "SRET Eats Food Ordering");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Automation Team");
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
