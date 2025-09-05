package com.planit.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent = createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports createInstance() {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("target/ExtentReport.html");
        reporter.config().setDocumentTitle("Planit Automation Test Report");
        reporter.config().setReportName("UI Automation Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
        test.get().fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String takeScreenshot(WebDriver driver, String methodName) {
        if (driver == null) return null;
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "target/screenshots/" + methodName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("target/screenshots/"));
            Files.copy(src.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
