package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * <h1>Homepage</h1> Page Object Model (POM) untuk halaman utama aplikasi.
 *
 * <p>
 * <b>Fungsi Utama:</b>
 * </p>
 * <ul>
 * <li>Mendefinisikan locator dan action pada halaman utama</li>
 * <li>Menyediakan method navigasi ke fitur seperti My Clients, Merge Client,
 * dan Homepage</li>
 * <li>Menggunakan helper {@link Utils} untuk aksi klik yang lebih aman</li>
 * </ul>
 *
 * <p>
 * <b>Usage:</b>
 * </p>
 * Buat instance kelas ini setelah inisialisasi driver, lalu gunakan method
 * seperti {@link #tapMyClientPage()}, {@link #tapMergeClientPage()}, atau
 * {@link #goToHomepage()} untuk navigasi.
 *
 * @author Kenny Ramadhan
 * @version 1.0
 * @since 2025
 */

public class Homepage {

	/** Instance driver iOS yang digunakan di halaman ini */
	private IOSDriver driver;

	/** Utilitas custom untuk aksi klik dan input */
	private Utils utils;

	/**
	 * Konstruktor untuk inisialisasi halaman Homepage.
	 *
	 * @param driver IOSDriver aktif yang sudah diinisialisasi
	 */
	public Homepage(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

	}

	/** Tombol menuju halaman My Client */
	@iOSXCUITFindBy(accessibility = " My Clients")
	private WebElement myClientPage;

	/** Tombol untuk kembali ke halaman utama */
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='    Horizontal scroll bar, 1 page Vertical scroll bar, 1 page']/XCUIElementTypeOther[2]")
	private WebElement homePageBtn;

	/** Tombol menuju halaman Merge Client */
	@iOSXCUITFindBy(accessibility = " Merge Client")
	private WebElement mergeClient;

	/**
	 * Navigasi kembali ke halaman utama.
	 * <p>
	 * Menggunakan {@link Utils#tapWhenReady(WebElement)} agar klik dilakukan
	 * setelah elemen siap.
	 * </p>
	 */
	public void goToHomepage() {
		utils.tapWhenReady(homePageBtn);
	}

	/**
	 * Navigasi ke halaman My Client.
	 */
	public void tapMyClientPage() {
		utils.tapWhenReady(myClientPage);
	}

	/**
	 * Navigasi ke halaman Merge Client.
	 */
	public void tapMergeClientPage() {
		utils.tapWhenReady(mergeClient);
	}
}
