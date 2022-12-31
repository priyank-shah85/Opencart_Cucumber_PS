package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.LoginPage;

public class LoginPageSteps {
	
	WebDriver driver;
	LoginPage lp = new LoginPage(HomePageSteps.driver);

	Logger logger = HomePageSteps.logger; // for logging
	
	// Verify valid login
	@And("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		lp.setEmail(email);
		logger.info("Provided Email ");
		lp.setPassword(pwd);
		logger.info("Provided Password ");
	}

	@And("click on Login button")
	public void click_on_login_button() {
		lp.clickLogin();
		logger.info("Clicked on Login button");
	}
	
	// Verify warning message for invalid login
	@Then("Page shows warning message for invalid credentials")
	public void page_shows_warning_message_for_invalid_credentials() {
		String msg = lp.chkWarning();
		logger.info("Validation exists for invalid credentials");
		try
		{
			Assert.assertEquals("Warning: No match for E-Mail Address and/or Password.", msg);
		}catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

}
