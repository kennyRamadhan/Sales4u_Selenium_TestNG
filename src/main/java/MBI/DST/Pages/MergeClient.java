package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * <h1>Merge Client Page</h1> Page Object Model (POM) untuk halaman <b>Merge
 * Client</b>.
 *
 * <p>
 * <b>Fungsi Utama:</b>
 * </p>
 * <ul>
 * <li>Memverifikasi halaman Merge Client berhasil ditampilkan</li>
 * </ul>
 *
 * <p>
 * <b>Usage:</b>
 * </p>
 * Buat instance dari kelas ini setelah inisialisasi driver, lalu gunakan method
 * {@link #verifyMergeClientPage()} untuk memastikan halaman terbuka.
 *
 * <pre>
 * MergeClient mergeClient = new MergeClient(driver);
 * mergeClient.verifyMergeClientPage();
 * </pre>
 *
 * @author Kenny Ramadhan
 * @version 1.0
 * @since 2025
 */
public class MergeClient {

	/** Instance driver iOS yang digunakan di halaman ini */
	private IOSDriver driver;

	/** Utilitas custom untuk aksi klik, input, dan verifikasi elemen */
	Utils utils;

	/**
	 * Konstruktor untuk inisialisasi halaman Merge Client.
	 *
	 * @param driver IOSDriver aktif yang sudah diinisialisasi
	 */
	public MergeClient(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	}

	/** Elemen teks "Merge Client" yang menjadi indikator halaman */
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Merge Client']")
	private WebElement mergeClientText;

	/**
	 * Memverifikasi halaman Merge Client berhasil dimuat.
	 * <p>
	 * Menggunakan {@link Utils#verifyElementExist(WebElement)} untuk memastikan
	 * elemen mergeClientText terlihat.
	 * </p>
	 *
	 * @throws RuntimeException jika elemen tidak ditemukan
	 */
	public void verifyMergeClientPage() {
		utils.verifyElementExist(mergeClientText);
	}
}
