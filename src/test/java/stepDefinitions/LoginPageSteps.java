package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.LoginPage;

public class LoginPageSteps {
	
	WebDriver driver;
	LoginPage lp = new LoginPage(HomePageSteps.driver);

	Logger logger = HomePageSteps.logger; // for logging
	
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

}
