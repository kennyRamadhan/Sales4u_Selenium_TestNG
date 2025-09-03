package MBI.DST.Listeners;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

static ExtentReports extent;
	
	
	public static ExtentReports getExtentReports() {
		
		if(extent == null) {
			 String reportDir = System.getProperty("user.dir") + "/reports/";
			 File directory = new File(reportDir);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            String timeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
	            String reportPath = reportDir + "ExtentReport_" + timeStamp + ".html";
			
	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        spark.config().setDocumentTitle("Automation Sales4u");
	        spark.config().setReportName("Regression Suite");
	        spark.config().setTheme(Theme.DARK);

	        // Setup ExtentReports
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("Tester", System.getProperty("user.name"));
	        extent.setSystemInfo("Environtment", "UAT");
		}
        
		return extent;
		
		
	}
}
