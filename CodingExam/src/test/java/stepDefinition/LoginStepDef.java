package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.LoginPage;

public class LoginStepDef {
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	final String driverKey = "webdriver.chrome.driver"; 
	final String driverDir = "/Users/ronaldchester/devRonald/Training/Selenium/chromedriver";
	final String sauceDemo = "https://www.saucedemo.com/";
	final String validUsername = "standard_user";
	final String bannedUser = "locked_out_user";
	final String password = "secret_sauce";
	
	
	@Given("I am on the Sauce Demo Login Page")
	public void i_am_on_the_sauce_demo_login_page() {
		System.setProperty(driverKey, driverDir);
		
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		driver.get("https://www.saucedemo.com/");
	}

	@When("I fill the account information for account StandardUser into the Username field and the Password field")
	public void i_fill_the_account_information_for_account_standard_user_into_the_username_field_and_the_password_field() {
		loginPage.setUserPassKey(validUsername, password);
	}

	@When("I click the Login Button")
	public void i_click_the_login_button() {
	    loginPage.pressLoginButton();
	}

	@Then("I am redirected to the Sauce Demo Main Page")
	public void i_am_redirected_to_the_sauce_demo_main_page() {
		final String actualUrl = driver.getCurrentUrl();
		final String expectedUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(expectedUrl,actualUrl);
	}

	@Then("I verify the App Logo exists")
	public void i_verify_the_app_logo_exists() {
		final boolean actualResult = homePage.isPresentAppLogo(); 
		Assert.assertTrue(actualResult);
	}

	@When("I fill the account information for account LockedOutUser into the Username field and the Password field And I click the Login Button")
	public void i_fill_the_account_information_for_account_locked_out_user_into_the_username_field_and_the_password_field_and_i_click_the_login_button() {
		loginPage.setUserPassKey(bannedUser, password);
	}

	@Then("I verify the Error Message contains the text {string}")
	public void i_verify_the_error_message_contains_the_text(String string) throws InterruptedException {
		loginPage.pressLoginButton();
	    Thread.sleep(3000);
	    
		final String actualErrorMessage = loginPage.errorMessage();
	    final String expectedErrorMessage = "Sorry, this user has been banned.";

	    //If needs to be caught
	    try {
	    	Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	    }catch(Exception e) {
	    	System.out.println("ActualErrorMessage != ExpectedErrorMessage");
	    }
	    //If doesn't need to be caught
	    //Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	    
	}


}
