package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.CheckoutPage;

public class CheckoutPageSteps {
	
	WebDriver driver;
	CheckoutPage cp = new CheckoutPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	//Verify details on page
	@And("user redirects to {string} page")
	public void user_redirects_to_page(String heading) {
		try
		{
			boolean compare = cp.checkPageHeading(heading);
			if(compare)
			{
				logger.info("User redirected to Checkout page.");
				Assert.assertTrue(true);
			}else
			{
				logger.info("User did not redirect to Checkout page.");
				Assert.assertTrue(false);
			}
		} catch(Exception e)
		{
			Assert.assertTrue("Checkout heading not found,\n" + e.getMessage(), false);
		}
	}
	
	@And("verify that Shipping Method is already prepopulated")
	public void verify_that_shipping_method_is_already_prepopulated() {
		try
		{
			boolean valueSelected = cp.checkShippingMethodValue();
			if(valueSelected)
			{
				logger.info("Shipping method pre-selected");
				Assert.assertTrue(true);
			}
		} catch(Exception e)
		{
			logger.info("Shipping method was not populated.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user selects Payment Method as {string}")
	public void user_selects_payment_method_as(String checkout) {
		try
		{
			cp.selectFreeCheckout(checkout);
		} catch(Exception e)
		{
			logger.info("Not able to select checkout option.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("verify the Total Price is same as of Shopping Cart page")
	public void verify_the_total_price_is_same_as_of_shopping_cart_page() {
		try {
			String checkoutTotalPrice = cp.compareTotalPrice(HomePageSteps.driver);
			if(checkoutTotalPrice.equals(ShoppingCartPageSteps.totalPrice))
			{
				logger.info("Total Price is same as Shopping Cart page.");
				Assert.assertTrue(true);
			}
		} catch(Exception e)
		{
			logger.info("Total Price is different from Shopping Cart page.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Confirm Order button")
	public void clicks_on_confirm_order_button() {
		try
		{
			cp.clickConfirmOrder(HomePageSteps.driver);
			logger.info("Order submitted successfully.");
		} catch(Exception e)
		{
			logger.info("Not able to place order.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	//Verify adding shipping address
	@And("user enters First Name as {string}")
	public void user_enters_first_name_as(String fname) {
		try
		{
			cp.enterCheckoutFirstName(fname);
		}catch(Exception e)
		{
			logger.info("Not able to enter First Name.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user enters Last Name as {string}")
	public void user_enters_last_name_as(String lname) {
		try
		{
			cp.enterCheckoutLastName(lname);
		}catch(Exception e)
		{
			logger.info("Not able to enter Last Name.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user enters Address 1 as {string}")
	public void user_enters_address_1_as(String addr) {
		try
		{
			cp.enterCheckoutAddress(addr);
		}catch(Exception e)
		{
			logger.info("Not able to enter Address 1.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user enters City as {string}")
	public void user_enters_city_as(String city) {
		try
		{
			cp.enterCheckoutCity(city);
		}catch(Exception e)
		{
			logger.info("Not able to enter City.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user selects {string} country")
	public void user_selects_country(String country) {
		try
		{
			cp.selectCheckoutCountry(country);
		} catch(Exception e)
		{
			logger.info("Not able to select Country.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user selects {string} state")
	public void user_selects_state(String state) {
		try
		{
			cp.selectCheckoutState(state);
		} catch(Exception e)
		{
			logger.info("Not able to select State.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Continue button")
	public void clickCheckoutContinue() {
		try
		{
			cp.clickCheckoutContinue(HomePageSteps.driver);
		} catch(Exception e)
		{
			logger.info("Not able to save the shipping address.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
