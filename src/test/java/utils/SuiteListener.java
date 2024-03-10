package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import web.nopcommerce.testScripts.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SuiteListener implements ITestListener {

    private static ExtentReports extent;    // Create an instance of the ExtentReport
    static {
        try {
            extent = BaseTest.createExtentReportInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public static ExtentTest test;
    public static Log4J log;                // Create an instance of the LOG4J Logger

    @Override
    public void onTestStart(ITestResult result) {
        Log4J.startTestCase(result.getMethod().getMethodName());
        test = extent.createTest(result.getTestClass().getName()+" :: "+result.getMethod().getMethodName());
        extentTest.set(test);
//        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log4J.endTestCase(result.getMethod().getMethodName());
        String logText = "<b> Test Method '"+result.getMethod().getMethodName()+"' Passed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
//        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log4J.failedTestCase(result.getMethod().getMethodName());

        WebDriver driver = BaseTest.getDriver();
        String path = takeScreenshot(driver, result.getMethod().getMethodName());
        try {
            extentTest.get().fail("<b><font color=red>"+"Screenshot of Failure"+"</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            extentTest.get().fail("Test Failed, can not attach Screenshot");
//            throw new RuntimeException(e);
        }

        String logText = "<b> Test Method '"+result.getMethod().getMethodName()+"' Failed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
//        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log4J.skippedTestCase(result.getMethod().getMethodName());
        String logText = "<b> Test Method '"+result.getMethod().getMethodName()+"' Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
//        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>"
                +"Exception Occurred, Click to see details:"
                +"</font></b></summary>"+exceptionMessage.replaceAll(",","<br>")+"</details>\n");
        WebDriver driver = BaseTest.getDriver();
        String path = takeScreenshot(driver, result.getMethod().getMethodName());
        try {
            extentTest.get().fail("<b><font color=red>"+"Screenshot of Failure"+"</font></b>",
                                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            extentTest.get().fail("Test Failed, can not attach Screenshot");
//            throw new RuntimeException(e);
        }

        String logText = "<b> Test Method '"+result.getMethod().getMethodName()+"' Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
//        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent!=null)
            extent.flush();
//        ITestListener.super.onFinish(context);
    }

    public String takeScreenshot(WebDriver driver, String methodName) {
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir")+File.separator+"Screenshots";
        new File(directory).mkdirs();
        String path = directory + File.separator + fileName;
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getScreenshotName(String methodName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_z");
        Date date = new Date();
        return "Screenshot_"+methodName+"_"+dateFormat.format(date)+".png";
    }
}
