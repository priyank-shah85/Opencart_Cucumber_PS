package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

}
