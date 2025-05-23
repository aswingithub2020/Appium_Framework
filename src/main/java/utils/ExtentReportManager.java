package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports getInstance() {
        if (extentReports == null) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK); // Choose DARK or STANDARD
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("iOS & Android Automation Results");
            sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // System info
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Tester", "Aswin Kumar Suvanam");
            extentReports.setSystemInfo("App Type", "Native App SWAG LABS");
        }

        return extentReports;
    }
    

    public static void startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
    
    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }


}

