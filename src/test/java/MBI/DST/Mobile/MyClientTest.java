package MBI.DST.Mobile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MBI.DST.Listeners.LogHelper;
import MBI.DST.Pages.Homepage;
import MBI.DST.Pages.MyClient;
import MBI.DST.Utils.UtilsExcel;
import MBI.DST.Utils.Utils;
import io.netty.handler.timeout.TimeoutException;

public class MyClientTest extends BaseTest {
	
	private MyClient myClient;
	private Homepage homepage;
	private Utils utils;

	
	
	 @DataProvider(name = "excelData")
	    public Object[][] getExcelData() {
	        String excelPath = System.getProperty("user.dir") + "/src/main/java/MBI/DST/Resources/dataFiles.xlsx";
	        return UtilsExcel.getTestData(excelPath, "Data");
	    }
	
	 @BeforeMethod
	    public void setupPages() {
		 
		 	myClient = new MyClient(driver);
		 	homepage = new Homepage(driver);
		 	utils = new Utils(driver);
		 	
	    }
	 
	 @Test(dataProvider="excelData")
	public void addNewClient(String clientName,String phoneNumber,String address,String campaign) throws MalformedURLException, URISyntaxException {
		 
		 LogHelper.step("Go To Client Page");
		 homepage.tapMyClientPage();
		 LogHelper.detail("Verifikasi Client Page");
	     
		 LogHelper.step("Tap Tombol Add New Client");
	     myClient.tapAddNewClientBtn();
	     LogHelper.detail("Verifikasi Form Input");
	     
	     LogHelper.step("Input Client Name");
	     myClient.inputClientName(clientName);
	     LogHelper.detail("Verifikasi Input Client Name");
	     
	     LogHelper.step("Input Phone Number");
	     myClient.inputPhoneNumber(phoneNumber);
	     
	     try {
	    	    WebElement errorElement = myClient.waitForErrorMessage(phoneNumber, 10);
	    	    if (errorElement != null && errorElement.isDisplayed()) {
	    	        LogHelper.fail(phoneNumber);
	    	        LogHelper.detail("Phone Number Duplicate, Tidak Bisa Add New Client");
	    	        Assert.fail("Duplicate");
	    	    }
	    	} catch (TimeoutException te) {
	    	   
	    	    LogHelper.pass(phoneNumber);
	    	    LogHelper.detail("Phone Number Tidak Duplicate, Dapat Lanjut Add New Client");
	    	} catch (Exception e) {
	    	    // Exception lain dianggap error (misalnya locator salah)
	    	    LogHelper.fail("Terjadi error saat verifikasi error message: " + e.getMessage());
	    	    throw e; // supaya test tetap fail
	    	}

			
		
		 LogHelper.step("Input Address");
	     myClient.inputAddress(address);
	     LogHelper.detail("Verifikasi Input Address");
	     
	     LogHelper.step("Input Campaign");
	     myClient.inputCampaign(campaign);
	     LogHelper.detail("Verifikasi Input Campaign");
	     
	     LogHelper.step("Save Add New Client");
	     myClient.saveAddNewClient();
	     LogHelper.detail("Verifikasi Save New Client");
	}
	
}
