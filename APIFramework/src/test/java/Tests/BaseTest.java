package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import apiEngine.Endpoints;
import reporting.ExtentReport;
import utilities.BasicUtils;

public class BaseTest {
	protected Endpoints endPointObject;
	static ExtentReports report;
	static ExtentTest test;
	static final Logger logger = LogManager.getLogger(BaseTest.class);

	public BaseTest() {
		ExtentReport.GenerateReport();
	}

	protected static void startTest(String testClassName) {

		test =ExtentReport.startTest(testClassName);

	}

	@AfterClass
	public static void afterClass() {
		ExtentReport.finishReport();
	}

}
