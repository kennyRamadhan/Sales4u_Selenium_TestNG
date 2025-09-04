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

/**
 * Test class untuk menguji fitur "Add New Client" pada halaman My Clients.
 *
 * <p>
 * Menggunakan pendekatan Data-Driven Testing dengan data diambil dari file
 * Excel. Test akan memverifikasi:
 * <ul>
 * <li>Halaman My Clients tampil dengan benar</li>
 * <li>Form Add New Client dapat diisi</li>
 * <li>Nomor telepon dicek apakah duplicate</li>
 * <li>Data client dapat disimpan jika valid</li>
 * </ul>
 * </p>
 *
 * <p>
 * Test ini menggunakan Page Object Model (POM) melalui kelas {@link MyClient}
 * dan {@link Homepage}.
 * </p>
 *
 * @author Kenny Ramadhan
 */
public class MyClientTest extends BaseTest {

	private MyClient myClient;
	private Homepage homepage;
	private Utils utils;

	/**
	 * Data provider untuk membaca data client dari file Excel.
	 *
	 * @return Object[][] berisi data client (clientName, phoneNumber, address,
	 *         campaign)
	 */
	@DataProvider(name = "excelData")
	public Object[][] getExcelData() {
		String excelPath = System.getProperty("user.dir") + "/src/main/java/MBI/DST/Resources/dataFiles.xlsx";
		return UtilsExcel.getTestData(excelPath, "Data");
	}

	/**
	 * Inisialisasi page object sebelum setiap test dijalankan.
	 */
	@BeforeMethod
	public void setupPages() {
		myClient = new MyClient(driver);
		homepage = new Homepage(driver);
		utils = new Utils(driver);
	}

	/**
	 * Test case untuk menambahkan client baru.
	 *
	 * <p>
	 * Langkah-langkah:
	 * <ol>
	 * <li>Buka halaman My Clients</li>
	 * <li>Tekan tombol Add New Client</li>
	 * <li>Input data client dari DataProvider</li>
	 * <li>Verifikasi jika nomor telepon sudah ada, maka test fail</li>
	 * <li>Jika nomor telepon belum ada, lanjutkan input dan simpan</li>
	 * </ol>
	 * </p>
	 *
	 * @param clientName  nama client yang akan ditambahkan
	 * @param phoneNumber nomor telepon client
	 * @param address     alamat client
	 * @param campaign    nama campaign
	 * @throws MalformedURLException jika URL driver salah
	 * @throws URISyntaxException    jika URI tidak valid
	 */
	@Test(dataProvider = "excelData")
	public void addNewClient(String clientName, String phoneNumber, String address, String campaign)
			throws MalformedURLException, URISyntaxException {

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
