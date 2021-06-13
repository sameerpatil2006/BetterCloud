package com.bettercloud.utility;

import org.testng.ITestResult;

import org.testng.TestListenerAdapter;

import java.io.IOException;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Exception occured: " + result);
        try {
            TestUtil.takeScreenshotAtEndOfTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Exception occured: " + result);
        try {
            TestUtil.takeScreenshotAtEndOfTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}