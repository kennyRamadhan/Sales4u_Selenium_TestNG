package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Login {
	
	private IOSDriver driver;
	Utils utils;

	public Login(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
	}

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextField'")
	private WebElement npkField;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeSecureTextField'")
	private WebElement passwordField;


	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='loginBtn']")
	private WebElement loginBTN;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='Welcome to Sales4u']")
	private WebElement dashboard;

	public void setNPK(String NPK) {
		utils.sendKeysWhenReady(npkField, NPK);
		driver.hideKeyboard();

	}
	
	public void inputNPK(String NPK) {
		utils.sendKeysWhenReady(npkField, NPK);
		driver.hideKeyboard();
		
	}
	
	public void inputPassword(String PWD) {
		utils.sendKeysWhenReady(passwordField, PWD);
		driver.hideKeyboard();
		
	}
	public void tapLoginBtn() {
		utils.tapWhenReady(loginBTN);
		utils.verifyElementExist(dashboard);
	}

	

}
