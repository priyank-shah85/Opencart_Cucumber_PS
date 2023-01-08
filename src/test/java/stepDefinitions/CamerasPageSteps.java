package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.CamerasPage;

public class CamerasPageSteps {
	
	WebDriver driver;
	CamerasPage cp = new CamerasPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	@And("clicks on Nikon D300 product")
	public void clicks_on_nikon_d300_product() {
		cp.clickNikonD300();
	}

}
