package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CamerasPage extends BasePage{

	public CamerasPage(WebDriver driver) {
		super(driver);
	}
	
	// Product elements
	@FindBy(linkText = "Nikon D300")
	WebElement nikonD300Link;
	
	// Action methods to click on product name
	public void clickNikonD300() {
		nikonD300Link.click();
	}

}
