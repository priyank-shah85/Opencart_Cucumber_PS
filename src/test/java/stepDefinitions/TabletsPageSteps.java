package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.TabletsPage;

public class TabletsPageSteps {
	
	WebDriver driver;
	TabletsPage tp = new TabletsPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Clicking on product links
	@And("clicks on Samsung Galaxy Tab 10.1 product")
	public void clicks_on_samsung_galaxy_tab_product() {
		try
		{
			logger.info("Clicking on Samsung Galaxy Tab 10.1 product ");
			tp.clickSamsungGalaxyTab();
		} catch (Exception e)
		{
			logger.info("Samsung Galaxy Tab 10.1 product does not exists or not a hyperlink.");
			Assert.assertTrue(e.getMessage(), false);
		}
	}

}
