package MBI.DST.Listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import MBI.DST.Listeners.ExtentNode;
import MBI.DST.Listeners.ExtentReportsManager;
import MBI.DST.Listeners.LogHelper;
import MBI.DST.Mobile.BaseTest;
import io.appium.java_client.ios.IOSDriver;

public class TestListeners implements ITestListener{
	
	 ExtentReports extent = ExtentReportsManager.getExtentReports();
	    ExtentTest test;
	    
	    @Override
	    public void onStart(ITestContext context) {
	        // Ambil instance dari ExtentReportsManager (sudah otomatis bikin folder & file)
	        extent = ExtentReportsManager.getExtentReports();

	        // Kalau perlu, update system info tambahan
	        extent.setSystemInfo("Test Suite", context.getSuite().getName());
	    }

	    
	    
	    @Override
	    public void onTestStart(ITestResult result) {
//	    	test = extent.createTest(result.getMethod().getMethodName());
	    	ExtentNode.createTest(result.getMethod().getMethodName());
	    	LogHelper.resetCounter();
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        try {
	            IOSDriver driver = BaseTest.getDriver(); // Ambil langsung dari BaseTest
	            String screenshotPath = getSuccesScreenshotPath(result.getMethod().getMethodName(), driver);
	            ExtentNode.getNode().addScreenCaptureFromPath(screenshotPath);
	            LogHelper.pass("Test Passed");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {    
	    	try {
	            IOSDriver driver = BaseTest.getDriver(); //  Ambil langsung dari BaseTest
	            String screenshotPath = getFailedScreenshotPath(result.getMethod().getMethodName(), driver);
	            ExtentNode.getNode().addScreenCaptureFromPath(screenshotPath);
	            LogHelper.fail("Test Failed");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	 ExtentNode.getNode().fail(result.getThrowable());
			
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	    	 System.out.println("Flushing Extent Report...");
	        extent.flush(); // Flush sekali di akhir suite
	        System.out.println("Extent Report generated at: " +
	                System.getProperty("user.dir") + "/reports/");
	    }
	    
	    private String getSuccesScreenshotPath(String testCasesName,IOSDriver driver) throws IOException {
			
			File source = driver.getScreenshotAs(OutputType.FILE);
			String destinationFile = System.getProperty("user.dir")+"/reports/pass/"+testCasesName+".png";
			FileUtils.copyFile(source,new File(destinationFile));
			return destinationFile;
		}
	    
	    private String getFailedScreenshotPath(String testCasesName,IOSDriver driver) throws IOException {
			
			File source = driver.getScreenshotAs(OutputType.FILE);
			String destinationFile = System.getProperty("user.dir")+"/reports/fail/"+testCasesName+".png";
			FileUtils.copyFile(source,new File(destinationFile));
			return destinationFile;
		}

}
