package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.netty.handler.timeout.TimeoutException;

/**
 * Page Object untuk halaman My Clients.
 *
 * <p>
 * Berisi locator dan action untuk halaman My Clients seperti:
 * <ul>
 * <li>Klik tombol Add Client</li>
 * <li>Input data client (nama, telepon, alamat, campaign)</li>
 * <li>Validasi error message</li>
 * <li>Menekan tombol Save atau Cancel</li>
 * </ul>
 * </p>
 *
 * <p>
 * Kelas ini mengimplementasikan pola Page Object Model (POM) sehingga
 * memudahkan pemeliharaan dan pembacaan kode.
 * </p>
 *
 * @author Kenny Ramadhan
 */
public class MyClient {

	private IOSDriver driver;
	Utils utils;

	/**
	 * Konstruktor untuk menginisialisasi halaman My Clients.
	 *
	 * @param driver instance dari IOSDriver yang digunakan oleh Appium
	 */
	public MyClient(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	}

	// ==================== LOCATORS ====================

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Search for client name or GCIF']")
	private WebElement searchBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\" Add New Client\"]")
	private WebElement addNewClientBtn;

	@iOSXCUITFindBy(xpath = "///XCUIElementTypeStaticText[@name=\"Add New Client\"]")
	private WebElement addNewClientText;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Client's Name  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement clientNameField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Min. 10 digits and max. 17 digits\"]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement phoneNumberField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Phone number 082112345678 is already existed in DSAR inputting by BELINDA NATASYA WORUNG\"]")
	private WebElement errorMessagePhoneNumber;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Address  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement addressField;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Campaign Name  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement campaignField;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Save\"]")
	private WebElement saveBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Cancel\"]")
	private WebElement cancelBtn;

	// ==================== ACTION METHODS ====================

	/**
	 * Verifikasi bahwa halaman My Clients sudah tampil. Mengecek apakah field
	 * search terlihat.
	 */
	public void verifyMyClientList() {
		utils.verifyElementExist(searchBtn);
	}

	/**
	 * Klik tombol "Add New Client" dan verifikasi halaman tambah client tampil.
	 */
	public void tapAddNewClientBtn() {
		utils.tapWhenReady(addNewClientBtn);
		utils.verifyElementExist(addNewClientText);
	}

	/**
	 * Input nama client.
	 * 
	 * @param clientName nama client yang akan diinput
	 */
	public void inputClientName(String clientName) {
		utils.sendKeysWhenReady(clientNameField, clientName);
		driver.hideKeyboard();
	}

	/**
	 * Input nomor telepon client.
	 * 
	 * @param phoneNumber nomor telepon yang akan diinput
	 */
	public void inputPhoneNumber(String phoneNumber) {
		utils.sendKeysWhenReady(phoneNumberField, phoneNumber);
		driver.hideKeyboard();
	}

	/**
	 * Input alamat client.
	 * 
	 * @param address alamat client
	 */
	public void inputAddress(String address) {
		utils.sendKeysWhenReady(addressField, address);
		driver.hideKeyboard();
	}

	/**
	 * Input nama campaign.
	 * 
	 * @param campaign nama campaign
	 */
	public void inputCampaign(String campaign) {
		utils.sendKeysWhenReady(campaignField, campaign);
		driver.hideKeyboard();
	}

	/**
	 * Tekan tombol Save untuk menyimpan data client baru.
	 */
	public void saveAddNewClient() {
		utils.tapWhenReady(saveBtn);
	}

	/**
	 * Tekan tombol Cancel untuk membatalkan input data client.
	 */
	public void cancelAddNewClient() {
		utils.tapWhenReady(cancelBtn);
	}

	/**
	 * Mengambil element error message untuk nomor telepon yang sudah ada.
	 * 
	 * @param phoneNumber nomor telepon yang diinput
	 * @return WebElement dari error message
	 */
	public WebElement getErrorMessagePhoneNumber(String phoneNumber) {
		String dynamicXpath = String.format(
				"//XCUIElementTypeStaticText[contains(@name,'Phone number %s is already existed')]", phoneNumber);
		return driver.findElement(By.xpath(dynamicXpath));
	}

	/**
	 * Tunggu sampai error message untuk nomor telepon muncul.
	 * 
	 * @param phoneNumber    nomor telepon yang diinput
	 * @param timeoutSeconds waktu tunggu maksimum dalam detik
	 * @return WebElement error message jika ditemukan, null jika timeout
	 */
	public WebElement waitForErrorMessage(String phoneNumber, int timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			return wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Phone number "
							+ phoneNumber + " is already existed')]")));
		} catch (TimeoutException e) {
			return null;
		}
	}
}
