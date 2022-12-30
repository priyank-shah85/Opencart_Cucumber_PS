package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import pageObjects.AccountConfirmation;

public class AccountConfirmationPageSteps {
	
	WebDriver driver;
	AccountConfirmation acf = new AccountConfirmation(HomePageSteps.driver);

	Logger logger = HomePageSteps.logger; // for logging
	
	@Then("User navigates to Account Confirmation page")
	public void user_navigates_to_account_confirmation_page() {
		String confmsg = acf.getConfirmationMsg();
		logger.info("Validating expected message");
		try
		{
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		
		catch(Exception e)
		{
			logger.error("Test failed");
			Assert.fail();
		}
		
		logger.info("New account created successfully ");

	}

}
