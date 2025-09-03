package MBI.DST.Utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public class Utils {
	
	 private IOSDriver driver;

	    // Constructor inject driver
	    public Utils(IOSDriver driver) {
	        this.driver = driver;
	    }


	public void sendKeysWhenReady(WebElement element, String text) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    element.sendKeys(text);
	}
	
	
	
	   public void tapWhenReady(WebElement element) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	    }
	   
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
	   
	   public void scrollIntoText(String text) {
		 
	        
	   }
	   

	   

}
