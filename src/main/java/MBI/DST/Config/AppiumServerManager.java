package MBI.DST.Config;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.remote.DesiredCapabilities;
import MBI.DST.Utils.ConfigLoader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * <h1>AppiumServerManager</h1> Kelas ini mengatur siklus hidup Appium Server
 * (start & stop) serta menginisialisasi IOSDriver menggunakan konfigurasi dari
 * file config.properties.
 *
 * <p>
 * <b>Usage:</b>
 * </p>
 * 
 * <pre>
 * // Contoh penggunaan di BaseTest
 * AppiumServerManager.startAppiumServer();
 * AppiumServerManager.initDriver();
 * IOSDriver driver = AppiumServerManager.getDriver();
 * </pre>
 *
 * @author Kenny Ramadhan
 * @version 1.0
 */
public class AppiumServerManager {
	/** Service instance dari Appium Server */
	private static AppiumDriverLocalService service;

	/** Instance IOSDriver yang sedang digunakan */
	private static IOSDriver driver;

	/** Lokasi file executable NodeJS */
	private static File nodePath = new File("/Users/kennyramadhan/.nvm/versions/node/v24.4.1/bin/node");

	/** Lokasi file executable Appium JS */
	private static File appiumJSPath = new File("/usr/local/bin/appium");

	/**
	 * Menghasilkan port acak untuk menjalankan Appium Server agar tidak terjadi
	 * konflik.
	 *
	 * @return nomor port antara 1000-9999
	 */
	private static int getRandomPort() {
		Random rand = new Random();
		return 1000 + rand.nextInt(9000); // range 1000-9999
	}

	/**
	 * Memulai Appium Server secara otomatis menggunakan path Node dan Appium JS
	 * yang sudah ditentukan. Port dipilih secara acak agar menghindari konflik.
	 *
	 * @throws IOException          jika terjadi kesalahan saat menjalankan server
	 * @throws InterruptedException jika thread terganggu saat menjalankan service
	 * @throws RuntimeException     jika server gagal dijalankan
	 */
	public static void startAppiumServer() throws IOException, InterruptedException {
		int appiumPort = getRandomPort();
		service = new AppiumServiceBuilder().usingDriverExecutable(nodePath).withAppiumJS(appiumJSPath)
				.usingPort(appiumPort).build();
		service.start();

		if (!service.isRunning()) {
			throw new RuntimeException("Appium Server gagal dijalankan!");
		}
		System.out.println("Appium Server started at: " + service.getUrl());
	}

	/**
	 * Menghentikan Appium Server jika sedang berjalan.
	 */
	public static void stopAppiumServer() {
		if (service != null && service.isRunning()) {
			service.stop();
			System.out.println("Appium Server stopped");
		}
	}

	/**
	 * Menginisialisasi IOSDriver dengan capabilities yang dibaca dari file
	 * config.properties melalui {@link ConfigLoader}.
	 *
	 * <p>
	 * <b>Capabilities yang didukung:</b>
	 * </p>
	 * <ul>
	 * <li>platformName</li>
	 * <li>deviceName</li>
	 * <li>automationName</li>
	 * <li>udid</li>
	 * <li>bundleId</li>
	 * <li>platformVersion</li>
	 * <li>wdaLaunchTimeout</li>
	 * <li>wdaStartupRetries</li>
	 * <li>wdaStartupRetryInterval</li>
	 * <li>noReset</li>
	 * <li>fullReset</li>
	 * <li>useNewWda</li>
	 * </ul>
	 *
	 * @throws Exception jika IOSDriver gagal diinisialisasi
	 */
	public static void initDriver() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

		if (ConfigLoader.has("platformName")) {
			caps.setCapability("platformName", ConfigLoader.get("platformName"));
		}
		if (ConfigLoader.has("deviceName")) {
			caps.setCapability("deviceName", ConfigLoader.get("deviceName"));
		}
		if (ConfigLoader.has("automationName")) {
			caps.setCapability("automationName", ConfigLoader.get("automationName"));
		}
		if (ConfigLoader.has("udid")) {
			caps.setCapability("udid", ConfigLoader.get("udid"));
		}
		if (ConfigLoader.has("bundleId")) {
			caps.setCapability("bundleId", ConfigLoader.get("bundleId"));
		}
		if (ConfigLoader.has("platformVersion")) {
			caps.setCapability("platformVersion", ConfigLoader.get("platformVersion"));
		}
		if (ConfigLoader.has("wdaLaunchTimeout")) {
			caps.setCapability("wdaLaunchTimeout", ConfigLoader.get("wdaLaunchTimeout"));
		}
		if (ConfigLoader.has("wdaStartupRetries")) {
			caps.setCapability("wdaStartupRetries", ConfigLoader.get("wdaStartupRetries"));
		}
		if (ConfigLoader.has("noReset")) {
			caps.setCapability("noReset", ConfigLoader.get("noReset"));
		}
		if (ConfigLoader.has("fullReset")) {
			caps.setCapability("fullReset", ConfigLoader.get("fullReset"));
		}
		if (ConfigLoader.has("wdaStartupRetryInterval")) {
			caps.setCapability("wdaStartupRetryInterval", ConfigLoader.get("wdaStartupRetryInterval"));
		}
		if (ConfigLoader.has("useNewWda")) {
			caps.setCapability("useNewWda", ConfigLoader.get("useNewWda"));
		}

		driver = new IOSDriver(service.getUrl(), caps);
		System.out
				.println("iOS Driver initialized for: " + ConfigLoader.getOrDefault("deviceName", "iPhone Simulator"));
	}

	/**
	 * Mengambil instance IOSDriver yang sedang digunakan.
	 *
	 * @return IOSDriver aktif yang sudah diinisialisasi
	 */
	public static IOSDriver getDriver() {
		return driver;
	}
}
