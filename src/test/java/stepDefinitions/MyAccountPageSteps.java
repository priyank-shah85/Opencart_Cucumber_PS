package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import pageObjects.MyAccountPage;

public class MyAccountPageSteps {
	
	WebDriver driver;
	MyAccountPage macc = new MyAccountPage(HomePageSteps.driver);

	Logger logger = HomePageSteps.logger; // for logging
	
	@Then("User navigates to MyAccount page")
	public void user_navigates_to_my_account_page() {
		boolean targetpage = macc.isMyAccountPageExists();

		if (targetpage) {
			logger.info("Login Success ");
			Assert.assertTrue(true);
		} else {
			logger.error("Login Failed ");
			Assert.assertTrue(false);
		}

	}

}
