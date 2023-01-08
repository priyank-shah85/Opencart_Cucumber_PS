package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabletsPage extends BasePage {

	public TabletsPage(WebDriver driver) {
		super(driver);
	}
	
	// Product elements
	@FindBy(linkText = "Samsung Galaxy Tab 10.1")
	WebElement samsungGalaxyTab;
	
	// Action methods
	public void clickSamsungGalaxyTab() {
		samsungGalaxyTab.click();
	}

}
