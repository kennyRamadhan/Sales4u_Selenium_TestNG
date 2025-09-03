package MBI.DST.Mobile;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import MBI.DST.Config.AppiumServerManager;
import MBI.DST.Pages.Homepage;
import MBI.DST.Pages.Login;
import io.appium.java_client.ios.IOSDriver;

public class BaseTest {
	
	protected static IOSDriver driver;
	protected Login loginPage;
	protected Homepage homepage;
	
	/**
	 * Menjalankan Appium Server & Set Up Login untuk semua test case
	 */


	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		AppiumServerManager.startAppiumServer();
		if (driver == null) AppiumServerManager.initDriver();
		driver = AppiumServerManager.getDriver();
		
		loginPage = new Login(driver);
		performLogin();
	}

	/**
	 * Stop Appium Server Setelah Selesai
	 */


	@AfterClass(alwaysRun = true)
	public void tearDown() {

		AppiumServerManager.getDriver().quit();
		AppiumServerManager.stopAppiumServer();
	}
	
	
	/**
	 * Memastikan driver appium tersedia jika tidak akan mulai start appium kembali
	 */
	
	@BeforeMethod
    public void ensureDriverReady() throws Exception {
        if (driver == null) {
            System.out.println("Driver null, inisialisasi ulang...");
            AppiumServerManager.initDriver();
            driver = AppiumServerManager.getDriver();
            performLogin();
        }
    }
	
	
	/**
	 * Memastikan setiap selesai menjalankan test case untuk kembali ke homepage
	 */
	@AfterMethod(alwaysRun = true)
    public void resetAppState() {
       
        navigateToHome();
    }
	  
	  private void performLogin() {
	        
	       loginPage.inputNPK("BW27204");
	       loginPage.inputPassword("MaybankWinner1!");
	       loginPage.tapLoginBtn();
	    }
	  
	  private void navigateToHome() {
	        
		    homepage.goToHomepage();
	        System.out.println("Navigasi kembali ke halaman utama...");
	    }
	
	public static IOSDriver getDriver() {
        return driver;
    }


}
