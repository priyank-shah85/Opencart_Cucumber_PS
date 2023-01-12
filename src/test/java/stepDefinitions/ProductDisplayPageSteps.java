package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.ProductDisplayPage;

public class ProductDisplayPageSteps {
	
	WebDriver driver;
	ProductDisplayPage pdp = new ProductDisplayPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger; // for logging
	
	public static String productUnitPrice = null;
	public static int desiredQty = 0;
	
	// Verify product display page for very first featured product
	@Then("user redirects to main display page of selected product")
	public void user_redirects_to_main_display_page_of_selected_product() {
		// First verify the correct page with browser title
		try
		{
			String browser_title = HomePageSteps.driver.getTitle();
			if(browser_title.toLowerCase().equals(HomePageSteps.featured_product_name.toLowerCase()))
			{
				logger.info("User has redirected to correct page.");
				Assert.assertTrue(true);
			} else
			{
				logger.info("User has redirected to incorrect page. Browser title assertion: " + browser_title);
				Assert.assertTrue(false);
			}
			
			// Second verify the correct page with product title
			if(pdp.productTitle().toString().toLowerCase().equals(HomePageSteps.featured_product_name.toLowerCase()))
			{
				logger.info("User is viewing correct product details.");
				Assert.assertTrue(true);
			} else
			{
				logger.info("User has redirected to incorrect page. Product title assertion: " + pdp.productTitle().toString());
				Assert.assertTrue(false);
			}
		} catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	// Generic methods for product details page
	@And("user can see product large image")
	public void user_can_see_product_large_image() {
		try
		{
			if(pdp.productImage())
			{
				logger.info("Page is showing image of selected product. ");
				Assert.assertTrue(true);
			}
		} catch(Exception e)
		{
			logger.info("Page is not showing product's image. ");
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("user redirects to {string} product details page")
	public void user_redirects_to_product_details_page(String productName) {
		try
		{
			if(pdp.productTitle().toString().toLowerCase().equals(productName.toLowerCase()))
			{
				logger.info("User is viewing correct product details.");
				Assert.assertTrue(true);
			} else
			{
				logger.info("User has redirected to incorrect page. Product title assertion: " + pdp.productTitle().toString());
				Assert.assertTrue(false);
			}
		} catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	@And("user clicks on product large image")
	public void user_clicks_on_product_large_image() {
		try
		{
			pdp.clickProductImage();
		} catch (Exception e)
		{
			logger.info("Not able to click on product's image. ");
			Assert.assertTrue(false);
		}
	}
	
	@And("note down the product unit price")
	public void note_down_the_product_unit_price() {
		try
		{
			productUnitPrice = pdp.UnitPrice();
			logger.info("Unit Price captured: " + productUnitPrice);
		} catch(Exception e)
		{
			Assert.assertTrue("Unit price is not present for product.\n" + e.getMessage(), false);
		}
	}
	
	@And("user updates quantity to {string}")
	public void user_updates_quantity_to(String qty) {
		try
		{
			desiredQty = Integer.parseInt(qty);
			pdp.clearExistingQty();
			pdp.changeQty(desiredQty);
		} catch(Exception e)
		{
			Assert.assertTrue("Not able to update quantity.\n" + e.getMessage(), false);
		}
	}
	
	@And("clicks on Add to Cart button")
	public void clicks_on_add_to_cart_button() {
		try
		{
			pdp.clickAddTocart();
		} catch(Exception e)
		{
			Assert.assertTrue("Not able to adding product to cart.\n" + e.getMessage(), false);
		}
	}
	
	// Verify Light Box view images
	@Then("user can see product image in Light Box view")
	public void user_can_see_product_image_in_Light_Boxview() {
		try
		{
			boolean b = pdp.verifyLightBoxCurrentImage();
			if(b)
			{
				logger.info("Image displayed.");
			} else
			{
				logger.info("Image did not display");
			}
		} catch (Exception e)
		{
			Assert.assertFalse(e.getMessage(),false);
		}
	}
	
	@And("user can view all four products with Next button")
	public void user_can_view_all_four_products_with_next_button() {
		try
		{
			ArrayList<String> currentImageName = new ArrayList<String>();
			String nextImageName;
			int i = 0;
			do {
				currentImageName.add(i, pdp.getCurrentImageName());
				logger.info("Current Image Name: " + currentImageName);
				int totalImages = 0;
				boolean b = pdp.verifyLightBoxCurrentImage();
				if(b)
				{
					logger.info("Image displayed.");
					totalImages = totalImages + 1;
				} else
				{
					logger.info("Image did not display.");
				}
				pdp.clickRightArrow();
				nextImageName = pdp.getCurrentImageName();
				logger.info("Next Image Name: " + nextImageName);
			} while(!(currentImageName.contains(nextImageName)));
		} catch(Exception e)
		{
			logger.info("Some issue while traversing the images.");
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("user can view all four products with Previous button")
	public void user_can_view_all_four_products_with_previous_button() {
		try
		{
			ArrayList<String> currentImageName = new ArrayList<String>();
			String nextImageName;
			int i = 0;
			do {
				currentImageName.add(i, pdp.getCurrentImageName());
				logger.info("Current Image Name: " + currentImageName);
				int totalImages = 0;
				boolean b = pdp.verifyLightBoxCurrentImage();
				if(b)
				{
					logger.info("Image displayed.");
					totalImages = totalImages + 1;
				} else
				{
					logger.info("Image did not display.");
				}
				pdp.clickLeftArrow();
				nextImageName = pdp.getCurrentImageName();
				logger.info("Next Image Name: " + nextImageName);
			} while(!(currentImageName.contains(nextImageName)));
		} catch(Exception e)
		{
			logger.info("Some issue while traversing the images.");
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("user clicks on close icon within Light Box view")
	public void user_clicks_on_close_icon_within_light_box_view() {
		pdp.clickClose();
	}
	
	// Verify adding a new review functionality
	@And("clicks on Reviews tab")
	public void clicks_on_reviews_tab() {
		try
		{
			pdp.clickReviewTab();
			logger.info("Clicked on Reviews tab.");
		} catch (Exception e)
		{
			Assert.assertTrue("Error while clicking on Review tab: \n" + e.getMessage(),false);
		}
	}
	
	@And("user enters full name in Your Name field")
	public void user_enters_full_name_in_your_name_field() {
		Faker faker = new Faker();
		try
		{
			String fullName = faker.name().fullName();
			pdp.enterName(fullName);
		} catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("user enters product feedback in Your Review field")
	public void user_enters_product_feedback_in_your_review_field() {
		try
		{
			String random = RandomStringUtils.randomAlphanumeric(30);
			pdp.enterReview(random);
		} catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("user provides {string} ratings")
	public void user_provides_ratings(String rating) {
		try
		{
			List<WebElement> radioBtns = pdp.ratingSection().findElements(By.tagName("input"));
			for(int i=0; i<radioBtns.size(); i++)
			{
				if(radioBtns.get(i).getAttribute("value").toString().equals(rating))
				{
					WebElement selectRadioBtn = radioBtns.get(i);
					((JavascriptExecutor)HomePageSteps.driver).executeScript("arguments[0].click();", selectRadioBtn);
				} else
				{
					continue;
				}
			}
		} catch (Exception e)
		{
			Assert.assertTrue("Not able to select rating:\n" + e.getMessage(), false);
		}
	}
	
	@And("user clicks on Continue")
	public void user_clicks_on_continue() {
		try
		{
			pdp.clickContinue();
		} catch(Exception e)
		{
			Assert.assertTrue("Not able to click Continue button:\n" + e.getMessage(), false);
		}
	}
	
	@Then("page shows confirmation message {string} for submitting the review")
	public void page_shows_confirmation_message_for_submitting_the_review(String expectedmsg) {
		try
		{
			String actualmsg = pdp.chkMessage();
			if(actualmsg.toLowerCase().equals(expectedmsg.toLowerCase()))
			{
				logger.info("New review was successfully submitted for approval.");
			}
			else
			{
				logger.info("Review was not submitted successfully.");
			}
		} catch(Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}

}
