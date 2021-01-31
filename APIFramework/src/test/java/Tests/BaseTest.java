package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Reporting.ExtentReport;
import apiEngine.Endpoints;
import utilities.BasicUtils;

public class BaseTest {
	protected Endpoints endPointObject;
	static ExtentReports report;
	static ExtentTest test;

	public BaseTest() {
		report = new ExtentReports(
				System.getProperty("user.dir") + "\\ExtentReportResults_" + BasicUtils.GetDateAndTime() + ".html");
	}

	protected static void startTest(String testClassName) {

		test = report.startTest(testClassName);

	}

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}

}
