package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.AccountRegistrationPage;

public class AccountRegistrationPageSteps {
	
	WebDriver driver;
	AccountRegistrationPage arp = new AccountRegistrationPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger; // for logging
	
	// Data entry steps
	@And("User enters First Name as {string} and Last Name as {string}")
	public void user_enters_first_name_as_and_last_name_as(String firstName, String lastName) {
		arp.setFirstName(firstName);
		logger.info("Entered first name ");
		
		arp.setLastName(lastName);
		logger.info("Entered last name ");

	}

	@And("User enters E-mail as {string}")
	public void user_enters_e_mail_as(String email) {
		arp.setEmail(email);
		logger.info("Entered E-mail address ");

	}

	@And("User enters Password as {string}")
	public void user_enters_password_as(String pwd) {
		arp.setPassword(pwd);
		logger.info("Entered Password ");

	}

	@And("User agree to the Privacy Policy")
	public void user_agree_to_the_privacy_policy() {
		arp.setPrivacyPolicy();

	}

	@And("click on Continue button")
	public void click_on_continue_button() {
		arp.clickContinue();
		logger.info("User information submitted ");

	}
	
	// Red asterisk verification steps
	@Then("Page displays red asterisk sign for mandatory fields")
	public void page_displays_red_asterisk_sign_for_mandatory_fields() {
		logger.info("Verifying red asterisk sign for all mandatory fields ");
		boolean fName = arp.isAstrkDisplayed_fName();
		boolean lName = arp.isAstrkDisplayed_lName();
		boolean email = arp.isAstrkDisplayed_email();
		boolean pwd = arp.isAstrkDisplayed_pwd();
		
		try 
		{
			if(fName) 
			{
				logger.info("Red asterisk present for First Name field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Red asterisk missing for First Name field ");
				Assert.assertTrue(false);
			}
			
			if(lName) 
			{
				logger.info("Red asterisk present for Last Name field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Red asterisk missing for Last Name field ");
				Assert.assertTrue(false);
			}
			
			if(email) 
			{
				logger.info("Red asterisk present for E-mail field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Red asterisk missing for E-mail field ");
				Assert.assertTrue(false);
			}
			
			if(pwd) 
			{
				logger.info("Red asterisk present for Password field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Red asterisk missing for Password field ");
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) 
		{
			logger.info("Red asterisk missing for one or more mandatory field(s) ");
			Assert.assertTrue(false);
		}
	}
	
	// Mandatory validation messages verification
	@Then ("Page displays the validation messages for mandatory fields")
	public void page_displays_the_validation_messages_for_mandatory_fields() {
		logger.info("Verifying validation messages for mandatory conditions ");
		boolean fName = arp.getValMsg_fName();
		boolean lName = arp.getValMsg_lName();
		boolean email = arp.getValMsg_email();
		boolean pwd = arp.getValMsg_pwd();
		boolean msg = arp.isPrivacyPolicyChecked();
		
		try 
		{
			if(fName) 
			{
				logger.info("Validation message exists for First Name field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Validation message missing for First Name field ");
				Assert.assertTrue(false);
			}
			
			if(lName) 
			{
				logger.info("Validation message exists for Last Name field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Validation message missing for Last Name field ");
				Assert.assertTrue(false);
			}
			
			if(email) 
			{
				logger.info("Validation message exists for E-mail field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Validation message missing for E-mail field ");
				Assert.assertTrue(false);
			}
			
			if(pwd) 
			{
				logger.info("Validation message exists for Password field ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Validation message missing for Password field ");
				Assert.assertTrue(false);
			}
			
			if(msg)
			{
				logger.info("Validation message popped up for Privacy Policy checkbox ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Validation message missing for Privacy Policy checkbox ");
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) 
		{
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
		
	}

}
