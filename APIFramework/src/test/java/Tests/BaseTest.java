package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import apiEngine.Endpoints;

public class BaseTest {
	protected Endpoints endPointObject;
	static ExtentTest test;
	static ExtentReports report;

	public BaseTest() {
		endPointObject = new Endpoints();
	}

	
	protected static void startTest(String testClassName) {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		System.out.println(System.getProperty("user.dir"));
		test = report.startTest(testClassName);
	}

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}



}
