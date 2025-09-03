package MBI.DST.Config;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.remote.DesiredCapabilities;
import MBI.DST.Utils.ConfigLoader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerManager {

	private static AppiumDriverLocalService service;
	private static IOSDriver driver;
	private static File nodePath = new File("/Users/kennyramadhan/.nvm/versions/node/v24.4.1/bin/node");
	private static File appiumJSPath = new File("/usr/local/bin/appium");
    private static int getRandomPort() {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000); // 1000-9999
    }

	public static void startAppiumServer() throws IOException, InterruptedException {
		int appiumPort = getRandomPort();
		service = new AppiumServiceBuilder().usingDriverExecutable(nodePath).withAppiumJS(appiumJSPath)
				.usingPort(appiumPort).build();
		service.start();
		System.out.println("Appium Server started at: " + service.getUrl());
	}

	public static void stopAppiumServer() {
		if (service != null && service.isRunning()) {
			service.stop();
			System.out.println("Appium Server stopped");
		}
	}

	public static void initDriver() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		
		if(ConfigLoader.has("platformName")) {
			caps.setCapability("platformName", ConfigLoader.get("platformName"));
		}
		if(ConfigLoader.has("deviceName")) {
			caps.setCapability("deviceName", ConfigLoader.get("deviceName"));
		}
		if(ConfigLoader.has("automationName")) {
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

	public static IOSDriver getDriver() {
		return driver;
	}

}
