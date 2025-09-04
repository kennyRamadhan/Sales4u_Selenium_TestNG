package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * <h1>Login Page</h1> Page Object Model (POM) untuk halaman Login aplikasi.
 *
 * <p>
 * <b>Fungsi Utama:</b>
 * </p>
 * <ul>
 * <li>Menangani input NPK</li>
 * <li>Menangani input Password</li>
 * <li>Menekan tombol Login</li>
 * <li>Memverifikasi apakah login berhasil dengan memeriksa elemen
 * dashboard</li>
 * </ul>
 *
 * <p>
 * <b>Usage:</b>
 * </p>
 * Buat instance kelas ini setelah inisialisasi driver, lalu gunakan method
 * seperti {@link #inputNPK(String)}, {@link #inputPassword(String)}, dan
 * {@link #tapLoginBtn()} untuk melakukan login.
 *
 * @author Kenny Ramadhan
 * @version 1.0
 */

public class Login {

	/** Instance driver iOS yang digunakan di halaman ini */
	private IOSDriver driver;

	/** Utilitas custom untuk aksi klik dan input */
	Utils utils;

	/**
	 * Konstruktor untuk inisialisasi halaman Login.
	 *
	 * @param driver IOSDriver aktif yang sudah diinisialisasi
	 */

	public Login(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
	}

	/** Field input NPK */
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextField'")
	private WebElement npkField;

	/** Field input Password */
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSecureTextField'")
	private WebElement passwordField;

	/** Tombol Login */
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='loginBtn']")
	private WebElement loginBTN;

	/** Elemen yang menjadi indikator halaman dashboard setelah login berhasil */
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Welcome to Sales4u']")
	private WebElement dashboard;
	
	
	/**
	 * Mengisi field NPK dan menyembunyikan keyboard.
	 *
	 * @param NPK Nomor NPK yang akan diinputkan
	 */
	public void setNPK(String NPK) {
		utils.sendKeysWhenReady(npkField, NPK);
		driver.hideKeyboard();
	}

	/**
	 * Mengisi field NPK (alias method dari {@link #setNPK(String)}).
	 *
	 * @param NPK Nomor NPK yang akan diinputkan
	 */
	public void inputNPK(String NPK) {
		utils.sendKeysWhenReady(npkField, NPK);
		driver.hideKeyboard();
	}

	/**
	 * Mengisi field Password dan menyembunyikan keyboard.
	 *
	 * @param PWD Password yang akan diinputkan
	 */
	public void inputPassword(String PWD) {
		utils.sendKeysWhenReady(passwordField, PWD);
		driver.hideKeyboard();
	}

	/**
	 * Menekan tombol Login dan memverifikasi bahwa halaman dashboard muncul.
	 * <p>
	 * Menggunakan {@link Utils#verifyElementExist(WebElement)} untuk memastikan
	 * login berhasil.
	 * </p>
	 */
	public void tapLoginBtn() {
		utils.tapWhenReady(loginBTN);
		utils.verifyElementExist(dashboard);
	}
	
	

}
