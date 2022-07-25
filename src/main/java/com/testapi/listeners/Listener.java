package com.testapi.listeners;

import com.aventstack.extentreports.Status;
import com.testapi.reporter.ExtentManager;
import org.testng.*;

public class Listener implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentManager.initReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getDescription();
        ExtentManager.createExtentTest(testName, "Test Api Automation", "Sonali Verma", "Chrome");
        ExtentManager.extentTest.pass(testName + " started successfully !!!");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.extentTest.pass(result.getMethod().getDescription() + " TEST is passed !!!");
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String error = result.getMethod().getDescription() + " TEST is failed !!!<br>" + result.getThrowable();
        ExtentManager.extentTest.log(Status.FAIL, error);
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.extentTest.skip(result.getMethod().getDescription() + " TEST is skipped !!!");
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

}
