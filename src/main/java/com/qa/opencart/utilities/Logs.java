package com.qa.opencart.utilities;

import com.aventstack.extentreports.Status;
import com.qa.opencart.listeners.ExtentReportListener;

/**
 * Centralized logging utility for test execution.
 * Decouples test code from reporting implementation.
 * Logs routed through ExtentReportListener centralized handler.
 */
public final class Logs {

    // Prevent instantiation
    private Logs() {}

    /** Log informational message */
    public static void info(String message) {
        ExtentReportListener.test.get().log(Status.INFO, message);
    }

    /** Log passed step */
    public static void pass(String message) {
        ExtentReportListener.test.get().log(Status.PASS, message);
    }

    /** Log failed step */
    public static void fail(String message) {
        ExtentReportListener.test.get().log(Status.FAIL, message);
    }

    /** Log warning message */
    public static void warn(String message) {
        ExtentReportListener.test.get().log(Status.WARNING, message);
    }

    /** Log skip message */
    public static void skip(String message) {
        ExtentReportListener.test.get().log(Status.SKIP, message);
    }
}