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
	
	@And("User enters Email as {string} and selects Newsletter as {string}")
	public void user_enters_Email_as_and_selects_Newsletter_as (String email, String subscribe) {
		if(subscribe.equals("Yes"))
		{
			arp.setEmail(email);
			logger.info("Subscribing to Newsletter ");
			arp.chkSubscribe_yes();
		} else if(subscribe.equals("No"))
		{
			arp.setEmail(email);
			logger.info("Not subscribing to Newsletter ");
			arp.chkSubscribe_no();
		} else
		{
			Assert.fail("Invalid selection for Newsletter field.");
		}
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
	
	// Fields verification
	@Then ("Verify the fields available on page")
	public void verify_the_fields_available_on_page() {
		logger.info("Verifying all available fields on page ");
		String fName = arp.isAvailable_fName();
		String lName = arp.isAvailable_lName();
		String email = arp.isAvailable_email();
		String pwd = arp.isAvailable_pwd();
		String news = arp.isAvailable_newsletter();
		String policy = arp.isAvailable_prvPolicy();
		
		try
		{
			Assert.assertEquals(fName, "First Name");
			Assert.assertEquals(lName, "Last Name");
			Assert.assertEquals(email, "E-Mail");
			Assert.assertEquals(pwd, "Password");
			Assert.assertEquals(news, "Subscribe");
			Assert.assertEquals(policy, "I have read and agree to the Privacy Policy");
		} catch (Exception e)
		{
			Assert.fail("One or more field labels are incorrect. ");
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
