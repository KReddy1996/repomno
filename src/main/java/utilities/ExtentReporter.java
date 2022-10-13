package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		String extentReportPath=System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("TutorialsNinja Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		
		extentReport=new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Operating system","Windows 10");
		extentReport.setSystemInfo("Test by","Kundan Reddy");
		
		return extentReport;
	}

}
