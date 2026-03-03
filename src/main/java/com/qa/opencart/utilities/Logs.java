package com.qa.opencart.utilities;

import com.aventstack.extentreports.Status;
import com.qa.opencart.listeners.ExtentReportListener;

public final class Logs {

    private Logs() {} // Prevent instantiation

    private static void safeLog(Status status, String message) {
        if (ExtentReportListener.test.get() != null) {
            ExtentReportListener.test.get().log(status, message);
        } else {
            // Fallback to console logging if ExtentTest is not initialized
            System.out.println("[" + status + "] " + message);
        }
    }

    public static void info(String message) {
        safeLog(Status.INFO, message);
    }

    public static void pass(String message) {
        safeLog(Status.PASS, message);
    }

    public static void fail(String message) {
        safeLog(Status.FAIL, message);
    }

    public static void warn(String message) {
        safeLog(Status.WARNING, message);
    }

    public static void skip(String message) {
        safeLog(Status.SKIP, message);
    }
}