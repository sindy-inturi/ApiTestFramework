package Reporting;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {
static ExtentTest test;
static ExtentReports report;
@BeforeClass
public static void startTest()
{
report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
test = report.startTest("ExtentDemo");
}

@AfterClass
public static void endTest()
{
report.endTest(test);
report.flush();
}
}
