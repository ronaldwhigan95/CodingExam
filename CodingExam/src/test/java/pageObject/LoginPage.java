package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage{
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	WebDriver driver;
	By loginButton = By.id("login-button");
	By loginUname = By.id("user-name");
	By loginPassword = By.id("password");
	By errorMessage = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]");


	public String getLoginUrl() {
		return driver.getCurrentUrl();
	}
	
	public void setUserPassKey(String username, String password) {
		driver.findElement(loginUname).sendKeys(username);
		driver.findElement(loginPassword).sendKeys(password);
	}

	public void pressLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public String errorMessage() {
		return driver.findElement(errorMessage).getText();
	}
}
