package pageObjects;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(xpath = "//td[@class='text-end' and text()='Unit Price']")
	WebElement unitPrice_column;
	
	@FindBy(xpath = "//td[@class='text-end' and text()='Total']")
	WebElement total_column;
	
	@FindBy(xpath = "//button[@type='button' and text()='Estimate Shipping & Taxes']")
	WebElement btnEstimateShippingTaxes;
	
	@FindBy(linkText = "Checkout")
	WebElement btnCheckout;
	
	// Elements for Estimate Shipping & Taxes section
	@FindBy(id = "input-country")
	WebElement dropdownCountry;
	
	@FindBy(id = "input-zone")
	WebElement dropdownState;
	
	@FindBy(id = "input-postcode")
	WebElement txtPostCode;
	
	@FindBy(id = "button-quote")
	WebElement btnGetQuotes;
	
	// Elements for Shipping method options modal
	@FindBy(className = "modal-title")
	WebElement txtModalTitle;
	
	@FindBy(id = "button-shipping")
	WebElement btnApplyShipping;
	
	// Action methods for details
	public boolean verifyProductName(WebDriver driver1, String productName) {
		boolean b = false;
		List<WebElement> search_results = driver1.findElements(By.tagName("a"));
		
		for(int i=0; i<search_results.size(); i++)
		{
			List<WebElement> search_results1 = driver1.findElements(By.tagName("a"));
			if(search_results1.get(i).getText().toLowerCase().equals(productName.toLowerCase()))
			{
				b = true;
			} else
			{
				b = false;
			}
		}
		return b;
	}
	
	public String calculateTotalUnitPrice(String unitPrice, int Qty) {
		String expected_up = unitPrice.replaceAll("[^0-9\\.]","");
		double expected_price = Double.parseDouble(expected_up);
		double expected_totalUnitPrice = expected_price*Qty;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String df1 = df.format(expected_totalUnitPrice);
		String expectedTotalUnitPrice = "$" + df1;
		
		return expectedTotalUnitPrice;
	}
	
	public boolean compareTotalUnitPrice(String expectedPrice) {
		WebElement total_column_price = driver.findElement(RelativeLocator.with(By.tagName("td")).near(By.xpath("//td[@class='text-end' and text()='Total']")));
		if(total_column_price.getText().equals(expectedPrice))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public void expandEstimateShippingTaxes() {
		btnEstimateShippingTaxes.click();
	}
	
	public String calculateTotalPrice(WebDriver driver1) {
		WebElement totalValue = driver1.findElement(RelativeLocator.with(By.xpath("//td[@class='text-end']"))
				.toRightOf(By.xpath("(//strong[contains(text(),'Total')])[4]")));
		return totalValue.getText();
	}
	
	public void clickCheckout(WebDriver driver1) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(btnCheckout));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", checkout);
	}
	
	// Action methods for Estimate Shipping & Taxes section
	public void selectCountry(String country) {
		Select select = new Select(dropdownCountry);
		select.selectByVisibleText(country);
	}
	
	public void selectState(String state) {
		Select select = new Select(dropdownState);
		select.selectByVisibleText(state);
	}
	
	public void enterPostCode(String code) {
		txtPostCode.sendKeys(code);
	}
	
	public void clickGetQuotes(WebDriver driver1) {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		WebElement GetQuotes = wait.until(ExpectedConditions.elementToBeClickable(btnGetQuotes));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", GetQuotes);
	}
	
	// Action methods for Shipping method options modal
	public boolean compareModalTitle(String title) {
		if(txtModalTitle.getText().toLowerCase().equals(title.toLowerCase()))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void selectFirstShippingMethod(WebDriver driver1) {
		WebElement radioButton = null;
		List<WebElement> shippingMethods = driver1.findElements(By.tagName("input"));
		for(int i=0; i<shippingMethods.size(); i++)
		{
			if(shippingMethods.get(i).getAttribute("type").equals("radio"))
			{
				radioButton = shippingMethods.get(i);
				break;
			} else
			{
				continue;
			}
		}
		radioButton.click();
	}
	
	public void clickApplyShipping() {
		btnApplyShipping.click();
	}

}
