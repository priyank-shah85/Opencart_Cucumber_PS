package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.DataTableType;

public class SearchPage extends BasePage {
	
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"content\"]/h2")
	WebElement searchHeading;
	
	@FindBy(xpath = "//*[@id=\"content\"]/p")
	WebElement noResultHeading;
	
	@FindBy(xpath = "//*[@id=\"input-description\"]")
	WebElement chkInProductDesc;
	
	@FindBy(xpath = "//*[@id=\"button-search\"]")
	WebElement btnSearch;
	
	// Elements for Category dropdown
	@FindBy(id = "input-category")
	WebElement dropdown;
	
	// Elements for clicking on product name from Search results
	@FindBy(linkText = "Samsung SyncMaster 941BW")
	WebElement lnkSamsungSyncMaster;
	
	
	// Action methods to implement Search functionality
	@DataTableType(replaceWithEmptyString = "[blank]")
	public String compareSearchPage() {
		return searchHeading.getText();
	}
	
	@DataTableType(replaceWithEmptyString = "[blank]")
	public String noResultReturned() {
		return noResultHeading.getText();
	}
	
	public void chkProductDesc() {
		chkInProductDesc.click();
	}

	public void clickSearchbtn() {
		btnSearch.click();
	}
	
	// Action methods to work with Category dropdown
	public void selectDDValue(String category) {
		Select categoryDD = new Select(dropdown);
		categoryDD.selectByVisibleText(category);
	}
	
	// Action methods to click on search product name
	public void clickOnProduct(WebDriver driver1, String productName) {
		List<WebElement> search_results = driver1.findElements(By.tagName("a"));
		for(int i=0; i<search_results.size(); i++)
		{
			List<WebElement> search_results1 = driver1.findElements(By.tagName("a"));
			if(search_results1.get(i).getText().toLowerCase().equals(productName.toLowerCase()))
			{
				WebElement correctLinkToClick = search_results1.get(i);
				WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
				WebElement link = wait.until(ExpectedConditions.elementToBeClickable(correctLinkToClick));
				((JavascriptExecutor)driver1).executeScript("arguments[0].click();", link);
			}
		}
	}

}
