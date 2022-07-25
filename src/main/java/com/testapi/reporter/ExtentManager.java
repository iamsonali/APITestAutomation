package com.testapi.reporter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Objects;

public final class ExtentManager {

    private static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static void initReport() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            String rootDir = System.getProperty("user.dir");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(rootDir + "/reports/ApiTestExecution.html");
            extentReports.attachReporter(sparkReporter);

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("API Report");
            sparkReporter.config().setReportName("RestAssured Test Report");
        }
    }

    public static void flushReport() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
    }

    public static void createExtentTest(String testName, String category, String authorName, String device) {
        extentTest = extentReports.createTest(testName).assignCategory(category).assignAuthor(authorName).assignDevice(device);
    }

    public static void endExtentTest(String testName) {
        extentReports.removeTest(testName);
    }

    private static void formatLogInReport(String content) {
        String prettyPrint = content.replace("\n", "<br>");
        ExtentManager.extentTest.info("<pre>" + prettyPrint + "</pre>");
    }

    public static void addResponseLogToReport(String response) {
        formatLogInReport(response);
    }

}
