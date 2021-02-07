package reporting;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.BasicUtils;



public class ExtentReport {
	
	static ExtentReports report;
	static ExtentTest test;
	
	public static void GenerateReport()
	{
		report = new ExtentReports(
				System.getProperty("user.dir") + "\\ExtentReportResults_" + BasicUtils.GetDateAndTime() + ".html");

	}
	
	public static ExtentTest startTest(String testClassName) {

		return test = report.startTest(testClassName);

	}
	
	public static void finishReport() {
		report.endTest(test);
		report.flush();
	}

	
}