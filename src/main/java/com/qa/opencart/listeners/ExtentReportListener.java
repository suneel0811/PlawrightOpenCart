package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static com.qa.opencart.factory.PlaywrightFactory.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    // Always points to your project root
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String OUTPUT_FOLDER = PROJECT_ROOT + "/test_reports/";
    private static final String FILE_NAME = "TestExecutionReport_" +
            new SimpleDateFormat("ddMMyy_HHmmss").format(new Date()) + ".html";

    ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> currentNode = new ThreadLocal<>();
    private static ExtentReports extentReports;

    private static ExtentReports init() {

        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Test reports folder created at: " + path.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to create test_reports folder!");
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Open Cart Automation Test Results");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "Windows");
        extentReports.setSystemInfo("Author", "Suneel Nare");
        return extentReports;
    }

    //Centralized log method for nodes
    public static void log(Status status, String message) {
        if (status == Status.INFO) {
            ExtentTest node = test.get().createNode(message);
            currentNode.set(node);
        } else {
            if (currentNode.get() != null) {
                currentNode.get().log(status, message);
            } else {
                test.get().log(status, message);
            }
        }
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush();
        test.remove();
        currentNode.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(methodName, result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed!");
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                        takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                        takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        test.get().skip(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                        takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}