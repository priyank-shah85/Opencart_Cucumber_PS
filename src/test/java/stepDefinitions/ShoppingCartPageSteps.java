package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.ShoppingCartPage;

public class ShoppingCartPageSteps {
	
	WebDriver driver;
	ShoppingCartPage scp = new ShoppingCartPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	public static String totalPrice = null;
	
	// Action methods for details
	@And("verify that product {string} is present in the cart")
	public void verify_that_product_is_present_in_the_cart(String productName) {
		try
		{
			if(scp.verifyProductName(HomePageSteps.driver, productName))
			{
				Assert.assertTrue("Product " + productName + " is present in shopping cart.", true);
			}
		} catch (Exception e)
		{
			Assert.assertTrue("Product " + productName + " is missing in shopping cart.", false);
		}
	}
	
	@And("verify the total unit price based on quantity")
	public void verify_the_total_unit_price_based_on_quantity() {
		try
		{
			String expectedTotalUnitPrice = scp.calculateTotalUnitPrice(ProductDisplayPageSteps.productUnitPrice, ProductDisplayPageSteps.desiredQty);
			boolean compareTotalUnitPrice = scp.compareTotalUnitPrice(expectedTotalUnitPrice);
			if(compareTotalUnitPrice)
			{
				logger.info("Total Unit Price is correctly calculated.");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Error in calculation of Total Unit Price on Shopping Cart.");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

		    logger.info("Issue with Total Unit Price:\n" + e.getMessage());
		    Assert.assertTrue(false);
		}
	}
	
	@And("user expands Estimate Shipping & Taxes section")
	public void user_expands_estimate_hipping_taxes_section() {
		try
		{
			scp.expandEstimateShippingTaxes();
		} catch (Exception e)
		{
			logger.info("Not able to expand the Estimate Shipping & Taxes section:\n" + e.getMessage());
		    Assert.assertTrue(false);
		}
	}
	
	@And("note down the Total Price")
	public void note_down_the_total_price() {
		try
		{
			totalPrice = scp.calculateTotalPrice(HomePageSteps.driver);
		}catch (Exception e)
		{
			logger.info("Issue with fetching & storing Total Price.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Checkout button")
	public void clicks_on_checkout_button() {
		try
		{
			scp.clickCheckout(HomePageSteps.driver);
		} catch (Exception e)
		{
			logger.info("Not able to click on Checkout button.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	// Verify for Estimate Shipping & Taxes section
	@And("user selects Country as {string}")
	public void user_selects_country_as(String country) {
		try
		{
			scp.selectCountry(country);
		} catch(Exception e)
		{
			logger.info("Not able to select country:\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user selects State as {string}")
	public void user_selects_state_as(String state) {
		try
		{
			scp.selectState(state);
		} catch(Exception e)
		{
			logger.info("Not able to select country:\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user enters Post Code as {string}")
	public void user_enters_post_code_as(String code) {
		try
		{
			scp.enterPostCode(code);
		} catch(Exception e)
		{
			logger.info("Not able to enter Post Code.");
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Get Quotes button")
	public void clicks_on_get_quotes_button() {
		try
		{
			scp.clickGetQuotes(HomePageSteps.driver);
		}catch(Exception e)
		{
			logger.info("Not able to click on Get Quotes:\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	// Verify Shipping method options modal
	@Then("a pop up opens with Shipping method options with title {string}")
	public void a_pop_up_opens_with_shipping_method_options_with_title(String title) {
		try
		{
			boolean comparisonResult = scp.compareModalTitle(title);
			if(comparisonResult)
			{
				Assert.assertTrue(true);
			} else
			{
				Assert.assertTrue(false);
			}
		} catch (Exception e)
		{
			logger.info("Shipping method options modal did not open.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user selects very first shipping method")
	public void user_selects_very_first_shipping_method() {
		try
		{
			scp.selectFirstShippingMethod(HomePageSteps.driver);
		} catch(Exception e)
		{
			logger.info("No shipping method present on modal.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Apply Shipping button")
	public void clicks_on_apply_shipping_button() {
		try
		{
			scp.clickApplyShipping();
		} catch(Exception e)
		{
			logger.info("Not able to apply selectd shipping method.\n" + e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
