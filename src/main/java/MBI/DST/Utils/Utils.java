package MBI.DST.Utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

/**
 * <h1>Utils</h1> Kelas utilitas yang berisi custom action untuk berinteraksi
 * dengan elemen pada aplikasi mobile berbasis iOS.
 *
 * <p>
 * <b>Fungsi utama:</b>
 * </p>
 * <ul>
 * <li>Menunggu elemen siap lalu mengisi teks (sendKeysWhenReady).</li>
 * <li>Menunggu elemen siap lalu melakukan tap/click (tapWhenReady).</li>
 * <li>Memverifikasi apakah elemen muncul dalam durasi tertentu
 * (verifyElementExist).</li>
 * <li>Menyiapkan metode scrollIntoText (Belum Selesai).</li>
 * </ul>
 *
 * <p>
 * Kelas ini membantu menjaga stabilitas test dengan memastikan semua aksi hanya
 * dieksekusi ketika elemen sudah siap (menggunakan explicit wait).
 * </p>
 *
 * <p>
 * <b>Contoh Penggunaan:</b>
 * </p>
 * 
 * <pre>
 * IOSDriver driver = new IOSDriver(...);
 * Utils utils = new Utils(driver);
 *
 * utils.sendKeysWhenReady(someElement, "123456789");
 * utils.tapWhenReady(buttonElement);
 * utils.verifyElementExist(successMessageElement);
 * </pre>
 *
 * @author Kenny Ramadhan
 * @version 1.0
 */

public class Utils {

	private IOSDriver driver;

	/**
	 * Constructor untuk menginisialisasi driver yang akan digunakan di seluruh aksi
	 * custom.
	 *
	 * @param driver Instance dari IOSDriver yang sedang digunakan.
	 */
	public Utils(IOSDriver driver) {
		this.driver = driver;
	}

	/**
	 * Mengisi teks pada elemen ketika elemen sudah siap diklik.
	 *
	 * @param element WebElement target.
	 * @param text    Teks yang akan diinput.
	 */

	public void sendKeysWhenReady(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(text);
	}

	/**
	 * Melakukan tap (click) pada elemen ketika elemen sudah siap diklik.
	 *
	 * @param element WebElement target.
	 */

	public void tapWhenReady(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	/**
	 * Memverifikasi apakah elemen muncul dalam durasi tertentu. Akan melempar
	 * RuntimeException jika elemen tidak ditemukan.
	 *
	 * @param element WebElement yang akan diverifikasi.
	 * @throws RuntimeException jika elemen tidak ditemukan.
	 */

	public void verifyElementExist(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("Element ditemukan: " + element);
		} catch (Exception e) {
			// Lempar exception agar test gagal
			throw new RuntimeException("Element tidak ditemukan: " + element, e);
		}

	}

	/**
	 * Scroll ke elemen berdasarkan teks. (Masih dalam tahap pengembangan)
	 *
	 * @param text Teks target yang akan discroll.
	 */

	public void scrollIntoText(String text) {

		// On Progress

	}

}
