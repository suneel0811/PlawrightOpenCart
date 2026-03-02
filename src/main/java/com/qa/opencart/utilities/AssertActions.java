package com.qa.opencart.utilities;

import org.testng.Assert;

public class AssertActions {

    public static void assertEquals(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
            Logs.pass("Assertion Passed: Actual: '" + actual + "' | Expected: '" + expected + "'");
        } catch (AssertionError e) {
            Logs.fail("Assertion Failed: Actual: '" + actual + "' | Expected: '" + expected + "'");
            throw e;  // Rethrow to fail the test
        }
    }
}
