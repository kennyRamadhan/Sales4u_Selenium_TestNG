package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Homepage {

	private IOSDriver driver;
	Utils utils;
	
	public Homepage(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	
	}
	
	@iOSXCUITFindBy(accessibility= " My Clients")
	private WebElement myClientPage;
	@iOSXCUITFindBy(xpath= "//XCUIElementTypeOther[@name='    Horizontal scroll bar, 1 page Vertical scroll bar, 1 page']/XCUIElementTypeOther[2]")
	private WebElement homePageBtn;
	@iOSXCUITFindBy(accessibility= " Merge Client")
	private WebElement mergeClient;
	
	
	public void goToHomepage() {
		utils.tapWhenReady(homePageBtn);
	}
	
	public void tapMyClientPage() {
		utils.tapWhenReady(myClientPage);
	}

	public void tapMergeClientPage() {
		utils.tapWhenReady(mergeClient);
	}
	
}
