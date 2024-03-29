package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	WebDriver driver;
	By homeAppLogo = By.className("app_logo");
	
	public boolean isPresentAppLogo() {
		return driver.findElement(homeAppLogo).isDisplayed();
	}
	
	public String getNewUrl() {
		return driver.getCurrentUrl();
	}
}
