package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.OrderConfirmationPage;

public class OrderConfirmationPageSteps {
	
	WebDriver driver;
	OrderConfirmationPage ocp = new OrderConfirmationPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	//Verify page header
	@Then("user can see the confirmation message as {string}")
	public void user_can_see_the_confirmation_message_as(String expectedHeader) {
		try
		{
			boolean compareResult = ocp.comparePageHeader(expectedHeader);
			if(compareResult)
			{
				logger.info("Order placed successfully.");
				Assert.assertTrue(true);
			}
		}catch (Exception e)
		{
			logger.info("Order did not place.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user clicks on Continue button")
	public void user_clicks_on_continue_button() {
		try
		{
			ocp.clickContinue(HomePageSteps.driver);
		}catch(Exception e)
		{
			logger.info("Not able to click on Continue button.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
