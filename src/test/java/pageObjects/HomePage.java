package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	// Elements for top navigation bar
	@FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul")
	WebElement entireNavbar;
	
	@FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[2]/a")
	WebElement laptopsAndNotebooks_navbar;
	
	@FindBy(linkText = "Show All Laptops & Notebooks")
	WebElement laptopsAndNotebooks_showAll;
	
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
	
	public void click_showAllLaptopsAndNotebooks() {
		Actions action = new Actions(driver);
		action.moveToElement(laptopsAndNotebooks_navbar).click().perform();
		action.moveToElement(laptopsAndNotebooks_showAll).click().perform();
	}

}
