package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for details
	@FindBy(tagName = "h1")
	WebElement pageHeading;
	
	@FindBy(id = "input-shipping-method")
	WebElement dropdownShippingMethod;
	
	@FindBy(id = "input-payment-method")
	WebElement dropdownFreeCheckout;
	
	@FindBy(xpath = "//button[@type='button' and text()='Confirm Order']")
	WebElement btnConfirmOrder;
	
	// Elements for add shipping address
	@FindBy(id = "input-shipping-firstname")
	WebElement txtCheckoutFirstName;
	
	@FindBy(id = "input-shipping-lastname")
	WebElement txtCheckoutLastName;
	
	@FindBy(id = "input-shipping-address-1")
	WebElement txtCheckoutAddress;
	
	@FindBy(id = "input-shipping-city")
	WebElement txtCheckoutCity;
	
	@FindBy(id = "input-shipping-country")
	WebElement dropdownCheckoutCountry;
	
	@FindBy(id = "input-shipping-zone")
	WebElement dropdownCheckoutState;
	
	@FindBy(id = "button-shipping-address")
	WebElement btnCheckoutContinue;
	
	// Action methods for details
	public boolean checkPageHeading(String heading) {
		if(pageHeading.getText().equals(heading))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean checkShippingMethodValue() {
		Select select = new Select(dropdownShippingMethod);
		if(!(select.getFirstSelectedOption().getText().equals("--- Please Select ---")))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void selectFreeCheckout(String checkout) {
		Select select = new Select(dropdownFreeCheckout);
		select.selectByVisibleText(checkout);
	}
	
	public String compareTotalPrice(WebDriver driver1) {
		WebElement totalValue = driver1.findElement(RelativeLocator.with(By.xpath("//td[@class='text-end']"))
				.toRightOf(By.xpath("(//strong[contains(text(),'Total')])[4]")));
		return totalValue.getText();
	}
	
	public void clickConfirmOrder(WebDriver driver1) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", button);
	}
	
	// Action methods for adding shipping address
	public void enterCheckoutFirstName(String fname) {
		txtCheckoutFirstName.sendKeys(fname);
	}
	
	public void enterCheckoutLastName(String lname) {
		txtCheckoutLastName.sendKeys(lname);
	}
	
	public void enterCheckoutAddress(String addr) {
		txtCheckoutAddress.sendKeys(addr);
	}
	
	public void enterCheckoutCity(String city) {
		txtCheckoutCity.sendKeys(city);
	}
	
	public void selectCheckoutCountry(String country) {
		Select select = new Select(dropdownCheckoutCountry);
		select.selectByVisibleText(country);
	}
	
	public void selectCheckoutState(String state) {
		Select select = new Select(dropdownCheckoutState);
		select.selectByVisibleText(state);
	}
	
	public void clickCheckoutContinue(WebDriver driver1) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(btnCheckoutContinue));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", button);
	}

}
