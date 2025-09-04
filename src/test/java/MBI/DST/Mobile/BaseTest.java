package MBI.DST.Mobile;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import MBI.DST.Config.AppiumServerManager;
import MBI.DST.Pages.Homepage;
import MBI.DST.Pages.Login;
import io.appium.java_client.ios.IOSDriver;

/**
 * <h1>BaseTest</h1> Kelas ini berfungsi sebagai kelas dasar untuk semua test
 * case mobile automation.
 * 
 * <p>
 * <b>Fungsi Utama:</b>
 * </p>
 * <ul>
 * <li>Mengelola siklus hidup Appium server (start & stop)</li>
 * <li>Mengelola inisialisasi dan pemeliharaan driver iOS</li>
 * <li>Menangani login sebelum test case dijalankan</li>
 * <li>Reset state aplikasi setelah test case selesai</li>
 * </ul>
 *
 * <p>
 * <b>Usage:</b> Extend kelas ini pada setiap kelas test case sehingga driver
 * dan konfigurasi login akan otomatis disiapkan sebelum eksekusi test.
 * </p>
 *
 * @author Kenny Ramadhan
 * @version 1.0
 */
public class BaseTest {

	/** Shared instance dari IOSDriver untuk digunakan di seluruh test */
	protected static IOSDriver driver;

	/** Page object untuk login */
	protected Login loginPage;

	/** Page object untuk homepage */
	protected Homepage homepage;

	/**
	 * Menjalankan Appium server dan menginisialisasi driver sebelum semua test case
	 * dijalankan.
	 * <p>
	 * Method ini juga akan memanggil {@link #performLogin()} agar semua test case
	 * langsung dijalankan dalam kondisi user sudah login.
	 * </p>
	 *
	 * @throws Exception jika gagal memulai Appium server atau menginisialisasi driver
	 */
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		AppiumServerManager.startAppiumServer();
		if (driver == null)
			AppiumServerManager.initDriver();
		driver = AppiumServerManager.getDriver();

		loginPage = new Login(driver);
		performLogin();
	}

	/**
	 * Menghentikan Appium server dan menutup driver setelah semua test selesai
	 * dijalankan.
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() {

		AppiumServerManager.getDriver().quit();
		AppiumServerManager.stopAppiumServer();
	}

	/**
	 * Memastikan driver selalu tersedia sebelum setiap test case dijalankan.
	 * <p>
	 * Jika driver null, maka akan dilakukan re-inisialisasi driver dan login ulang.
	 * </p>
	 *
	 * @throws Exception jika gagal melakukan re-inisialisasi driver
	 */
	@BeforeMethod
	public void ensureDriverReady() throws Exception {
		if (driver == null) {
			System.out.println("Driver null, inisialisasi ulang...");
			AppiumServerManager.initDriver();
			driver = AppiumServerManager.getDriver();

			if (driver == null) {
				throw new RuntimeException("Gagal inisialisasi Appium Driver!");
			}

			performLogin();
		}
	}

	/**
	 * Close Aplikasi setiap test case selesai.
	 */
	@AfterMethod(alwaysRun = true)
	public void resetAppState() {

	 driver.closeApp();
	}

	/**
	 * Melakukan login default menggunakan NPK dan password yang telah ditentukan.
	 * <p>
	 * Method ini digunakan secara internal dan tidak dipanggil langsung di test
	 * case.
	 * </p>
	 */
	private void performLogin() {
		
		driver.launchApp();
		loginPage.inputNPK("BW27204");
		loginPage.inputPassword("MaybankWinner1!");
		loginPage.tapLoginBtn();
	}

	/**
	 * Mengembalikan instance aktif dari IOSDriver.
	 * <p>
	 * Jika driver belum ada, maka akan mengambil dari {@link AppiumServerManager}.
	 * </p>
	 *
	 * @return instance aktif dari IOSDriver
	 */
	public static IOSDriver getDriver() {
		if (driver == null) {
			driver = AppiumServerManager.getDriver();
		}
		return driver;
	}

}
