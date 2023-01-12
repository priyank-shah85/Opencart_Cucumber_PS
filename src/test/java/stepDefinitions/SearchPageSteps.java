package stepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.HomePage;
import pageObjects.SearchPage;

public class SearchPageSteps {
	
	WebDriver driver;
	SearchPage sp = new SearchPage(HomePageSteps.driver);
	HomePage hp = new HomePage(HomePageSteps.driver);
	AllLaptopsNotebooksPageSteps alnps = new AllLaptopsNotebooksPageSteps();
	
	Logger logger = HomePageSteps.logger; // for logging
	
	@Then("page displays heading as {string}")
	public void page_displays_heading_as(String expectedMsg) {
		String actualMsg = sp.compareSearchPage();
		try
		{
			logger.info("Correct search page displays ");
			Assert.assertEquals(expectedMsg, actualMsg);
		} catch (Exception e)
		{
			Assert.fail("Incorrect message for product search " + actualMsg + " ");
		}
	}
	
	@Then("page displays message as {string}")
	public void page_displays_message_as(String expectedMsg) {
		String actualMsg = sp.noResultReturned();
		try
		{
			logger.info("Correct message for no results ");
			Assert.assertEquals(expectedMsg, actualMsg);
		} catch (Exception e)
		{
			Assert.fail("Incorrect message when no results returned for given search " + actualMsg + " ");
		}
	}
	
	@And("page title contains {string} word")
	public void page_title_contains_word(String expectedTitle) {
		String actualTitle = HomePageSteps.driver.getTitle();
		try
		{
			if (actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()))
			{
				logger.info("Search keyword "+ expectedTitle + " present in page title. ");
				Assert.assertTrue(true);
			}
		}catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("page returns all products matching with search keyword {string}")
	public void page_returns_all_products_matching_with_search_keyword(String searchWord) {
		List<WebElement> list_of_products = HomePageSteps.driver.findElements(By.tagName("h4"));
		String product_name;
		int total_products = 0;
		try
		{
			for(int i=0; i<list_of_products.size(); i++)
			{
				product_name = list_of_products.get(i).getText();
				if(product_name.toLowerCase().contains(searchWord.toLowerCase()))
				{
					Assert.assertTrue(true);
					total_products = total_products + 1;
				}
				else
				{
					continue;
				}
			}
			logger.info("Total products found: " + total_products + ".");
		}catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	@And("page returns no products as search results")
	public void page_returns_no_products_as_search_results() {
		List<WebElement> list_of_products = HomePageSteps.driver.findElements(By.tagName("h4"));
		try
		{
			if(list_of_products.size() == 0)
			{
				logger.info("Correct behavior when searching with blank/incorrect keyword ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Search returned products without matching keyword ");
				Assert.assertTrue(false);
			}
		} catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	// Verify search with Product Description checked
	@And("user checks Search in product descriptions checkbox")
	public void user_checks_Search_in_product_descriptions_checkbox() {
		sp.chkProductDesc();
	}
	
	@And("user clicks on Search button")
	public void user_clicks_on_Search_button() {
		sp.clickSearchbtn();
	}
	
	@Then("page returns all products where {string} is present either in product name or description")
	public void page_returns_all_products_where_is_present_either_in_product_name_or_description(String keyword) {
		List<WebElement> initial_list_of_products = HomePageSteps.driver.findElements(By.tagName("h4"));
		WebElement product_link;
		String product_name;
		int total_products = 0;
		try
		{
			for(int i=0; i<initial_list_of_products.size(); i++)
			{
				WebDriverWait wait = new WebDriverWait(HomePageSteps.driver, Duration.ofSeconds(10));
				//logger.info("Product name assignment starts ");
				List<WebElement> list_of_products = HomePageSteps.driver.findElements(By.tagName("h4"));
				product_name = list_of_products.get(i).getText();
				//product_name = list_of_products.get(i).getText();
				//product_link = list_of_products.get(i).findElement(By.linkText(product_name));
				//logger.info(product_link.getText());
				//logger.info("Product name assigned as: " + product_name + " ");
				if(product_name.toLowerCase().contains(keyword.toLowerCase()))
				{
					//logger.info("Product name if block starts");
					logger.info("Product " + product_name + "'s name contains " + keyword + ".");
					Assert.assertTrue(true);
					total_products = total_products+1;
					//logger.info("Product name if block ends");
				}
				else
				{
					product_link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(product_name)));
					//logger.info("Clicking on product name link ");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", product_link);
					//product_link.click();
					WebElement descriptionTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Description")));
					//logger.info("Clicking on Description tab ");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", descriptionTab);
					//logger.info("Clicked on Description tab ");
					//wait.wait(10000);
					WebElement descriptionText = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-description")));
					//logger.info(descriptionText.getText());
					if(descriptionText.getText().toLowerCase().contains(keyword.toLowerCase()))
					{
						logger.info("Product " + product_name + "'s description contains " + keyword + ".");
						Assert.assertTrue(true);
						total_products = total_products+1;
					}
					else
					{
						logger.info("Product " + product_name + "'s description does not contain " + keyword + ".");
						Assert.assertTrue(false);
					}
					driver.navigate().back();
				}
			}
			logger.info("Total products returned as search results: " + total_products + ".");
		} catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	// Verify the product search within Laptops & Notebooks category	
	@And("user selects category as {string}")
	public void user_selects_category_as(String category) {
		sp.selectDDValue(category);
	}
	
	@Then("page returns all products where keyword is present in product name and belongs to above category")
	public void page_returns_all_products_where_keyword_is_present_in_product_name_and_belongs_to_above_category() {
		List<WebElement> search_results = HomePageSteps.driver.findElements(By.tagName("h4"));
		ArrayList<String> product_name = new ArrayList<String>();
		ArrayList<String> product_id = new ArrayList<String>();
		String attribute_name = null;
		
		try
		{
			// Storing searched product name and id in array
			for(int i = 0; i<search_results.size(); i++)
			{
				product_name.add(i, search_results.get(i).getText());
				WebElement product_thumb = HomePageSteps.driver.findElements(By.className("product-thumb")).get(i);
				List<WebElement> inputs = product_thumb.findElements(By.tagName("input"));
				for(int j = 0; j<inputs.size(); j++)
				{
					attribute_name = inputs.get(j).getAttribute("name");
					if(attribute_name.equalsIgnoreCase("product_id"))
					{
						product_id.add(j, inputs.get(j).getAttribute("value"));
					}
				}
			}
			logger.info("Product Names are: " + product_name +".");
			logger.info("Product Ids are: " + product_id + ".");
			
			logger.info("ProductId array size: " + AllLaptopsNotebooksPageSteps.productId.size());
			logger.info("Product_id array size: " + product_id.size());
			
			// Comparing the search array with All Laptops & Notebooks product list
			// First comparing total number of products on both pages
			if(product_id.size() == AllLaptopsNotebooksPageSteps.productId.size())
			{
				logger.info("Search returned correct number of products and matching with Laptops & Notebooks category products");
				for(int k = 0; k<product_id.size(); k++)
				{
					if(AllLaptopsNotebooksPageSteps.productId.get(k).toLowerCase().contains(product_id.get(k).toLowerCase()))
					{
						logger.info("Searched product ID " + product_id.get(k) + " is part of Laptops & Notebooks category.");
					}
					else
					{
						logger.info("Searched product ID " + product_id.get(k) + " is not part of Laptops & Notebooks category.");
					}
				}
			}
			else
			{
				logger.info("Total products returned as search results are not matching with Laptops & Notebooks category products");
				Assert.assertTrue(false);
			}
		} catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(),false);
		}
	}
	
	// Clicks on product link
	@And("clicks on {string} product")
	public void clicks_on_product(String productName) {
		try
		{
			sp.clickOnProduct(HomePageSteps.driver, productName);
		} catch(Exception e)
		{
			Assert.assertTrue("Product " + productName + " did not return as search results.\n)" + e.getMessage(), false);
		}
	}

}
