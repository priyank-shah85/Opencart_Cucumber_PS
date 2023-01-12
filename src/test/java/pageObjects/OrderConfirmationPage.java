package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage extends BasePage {

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for details
	@FindBy(tagName = "h1")
	WebElement pageHeader;
	
	@FindBy(xpath = "//a[text()='Continue']")
	WebElement btnContinue;
	
	// Action methods for details
	public boolean comparePageHeader(String expectedHeader) {
		if(pageHeader.getText().equals(expectedHeader))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public void clickContinue(WebDriver driver1) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", button);
	}

}
