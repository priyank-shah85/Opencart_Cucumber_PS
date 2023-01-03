package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.cucumber.java.DataTableType;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	
	//Elements
	@FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span")
	WebElement lnkMyAccount;
	
	@FindBy(linkText = "Register")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	// Elements to implement Search feature
	@FindBy(name = "search")
	WebElement searchBox;
	
	@FindBy(xpath = "//*[@id=\"search\"]/button")
	WebElement iconSearch;
	
	@FindBy(xpath = "//*[@id=\"content\"]/h2")
	WebElement searchHeading;
	
	@FindBy(xpath = "//*[@id=\"content\"]/p")
	WebElement noResultHeading;
	
	@FindBy(xpath = "//*[@id=\"input-description\"]")
	WebElement chkInProductDesc;
	
	@FindBy(xpath = "//*[@id=\"button-search\"]")
	WebElement btnSearch;
	
	//Action Methods
	public void clickMyAccount() {
		
		lnkMyAccount.click();
		
	}
	
	public void clickRegister() {
		
		lnkRegister.click();
		
	}
	
	public void clickLogin() {
		
		lnkLogin.click();
		
	}
	
	// Action method to find all search results
	@DataTableType(replaceWithEmptyString = "[blank]")
	public void txtSearch(String word) {
		searchBox.sendKeys(word);
	}
	
	public void clickSearch() {
		iconSearch.click();
	}
	
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
	
	

}
