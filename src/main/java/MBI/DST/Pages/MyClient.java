package MBI.DST.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import MBI.DST.Utils.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MyClient {
	
	private IOSDriver driver;
	Utils utils;
	
	public MyClient(IOSDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	
	}
	
	@iOSXCUITFindBy(xpath= "//XCUIElementTypeTextField[@value='Search for client name or GCIF']")
	private WebElement searchBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\" Add New Client\"]")
	private WebElement addNewClientBtn;
	
	@iOSXCUITFindBy(xpath="///XCUIElementTypeStaticText[@name=\"Add New Client\"]")
	private WebElement addNewClientText;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\"Client's Name  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement clientNameField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Min. 10 digits and max. 17 digits\"]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement phoneNumberField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Phone number 082112345678 is already existed in DSAR inputting by BELINDA NATASYA WORUNG\"]")
	private WebElement errorMessagePhoneNumber;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\"Address  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement addressField;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\"Campaign Name  *\"])[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextField")
	private WebElement campaignField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Save\"]")
	private WebElement saveBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Cancel\"]")
	private WebElement cancelBtn;
	
	
	public void verifyMyClientList() {
		utils.verifyElementExist(searchBtn);
	}
	
	
	public void tapAddNewClientBtn() {
		utils.tapWhenReady(addNewClientBtn);
		utils.verifyElementExist(addNewClientText);
	}
	
	public void inputClientName(String clientName) {
		
		utils.sendKeysWhenReady(clientNameField, clientName);
		driver.hideKeyboard();	
	}
	
	public void inputPhoneNumber(String phoneNumber) {
		
		utils.sendKeysWhenReady(phoneNumberField, phoneNumber);
		driver.hideKeyboard();
		
	}
	
	public void inputAddress(String address) {
		utils.sendKeysWhenReady(addressField, address);
		driver.hideKeyboard();
	}
	
	public void inputCampaign(String campaign) {
		utils.sendKeysWhenReady(campaignField, campaign);
		driver.hideKeyboard();
	}
	
	public void saveAddNewClient() {
		utils.tapWhenReady(saveBtn);
	}
	
	public void cancelAddNewClient() {
		utils.tapWhenReady(cancelBtn);
	}
	
	public WebElement getErrorMessagePhoneNumber(String phoneNumber) {
		// Menggunakan contains supaya nama user di belakang bisa berubah-ubah
		String dynamicXpath = String.format("//XCUIElementTypeStaticText[contains(@name,'Phone number %s is already existed')]", phoneNumber);
		return driver.findElement(By.xpath(dynamicXpath));
	}
	
	

}
