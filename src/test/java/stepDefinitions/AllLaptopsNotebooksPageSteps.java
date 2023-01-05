package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import pageObjects.AllLaptopsNotebooksPage;

public class AllLaptopsNotebooksPageSteps {
	
	WebDriver driver;
	AllLaptopsNotebooksPage alnp = new AllLaptopsNotebooksPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger; // for logging
	
	public static ArrayList<String> productName = new ArrayList<String>(); // for storing product names
	public static ArrayList<String> productId = new ArrayList<String>(); // for storing product ids
	
	@And("notes down product names & ids that contains {string} word")
	public void notes_down_product_names_ids_that_contains_word(String keyword) {
		String attribute_name = null;
		
		// Getting and storing the products from Category navigation bar
		List<WebElement> product_lists = HomePageSteps.driver.findElements(By.tagName("h4"));
		for(int i=0; i<product_lists.size(); i++)
		{
			WebElement product_thumb = HomePageSteps.driver.findElements(By.className("product-thumb")).get(i);
			//logger.info(product_thumb.findElement(By.tagName("h4")).getText());
			productName.add(i, product_thumb.findElement(By.tagName("h4")).getText());
			if(productName.get(i).toLowerCase().contains(keyword.toLowerCase()))
			{
				List<WebElement> inputs = product_thumb.findElements(By.tagName("input"));
				for(int j = 0; j<inputs.size(); j++)
				{
					attribute_name = inputs.get(j).getAttribute("name");
					if(attribute_name.equalsIgnoreCase("product_id"))
					{
						productId.add(j, inputs.get(j).getAttribute("value"));
					}
				}
			}
		}
	logger.info("Product Names are: " + productName +".");
	logger.info("Product Ids are: " + productId + ".");
	logger.info("ProductId array size: " + productId.size());
	}

}
