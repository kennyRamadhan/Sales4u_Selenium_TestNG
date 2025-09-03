package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MergeClient {

	private IOSDriver driver;
	Utils utils;
	
	public MergeClient(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	
	}
	
	@iOSXCUITFindBy(xpath= "//XCUIElementTypeStaticText[@name='Merge Client']")
	private WebElement mergeClientText;
	
	public void verifyMergeClientPage() {
		utils.verifyElementExist(mergeClientText);
	}
	
}
