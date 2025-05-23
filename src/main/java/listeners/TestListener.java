package listeners;

import com.aventstack.extentreports.Status;
import org.testng.*;

import utils.ExtentReportManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.startTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL, "❌ Test Failed");
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush(); // CRUCIAL
    }
}


