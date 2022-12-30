package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Elements to enter values in fields
	@FindBy(name = "firstname")
	WebElement txtFirstName;
	
	@FindBy(name = "lastname")
	WebElement txtLastName;
	
	@FindBy(name = "email")
	WebElement txtEmail;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	// Elements to find field labels 
	@FindBy(xpath = "//label[@for='input-firstname']")
	WebElement fldFirstName;
	
	@FindBy(xpath = "//label[@for='input-lastname']")
	WebElement fldLastName;
	
	@FindBy(xpath = "//label[@for='input-email']")
	WebElement fldEmail;
	
	@FindBy(xpath = "//label[@for='input-password']")
	WebElement fldPassword;
	
	// Elements to capture mandatory validation message
	@FindBy(xpath = "//div[@id='error-lastname']")
	WebElement valMsgLastName;
	
	@FindBy(xpath = "//div[@id='error-email']")
	WebElement valMsgEmail;
	
	@FindBy(xpath = "//div[@id='error-password']")
	WebElement valMsgPassword;
	
	@FindBy(xpath = "//h1[normalize-space()='Register Account']")
	WebElement msgRegisterPage;
	
/*	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue; */
	
	//Action Methods for entering field values
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='agree']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	public void clickContinue() {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element1);
	}
	
	// Action methods to verify red asterisk sign
	public boolean isAstrkDisplayed_fName() {
		Boolean b = fldFirstName.getText().contains("*");
		return b;
	}
	
	public boolean isAstrkDisplayed_lName() {
		Boolean b = fldLastName.getText().contains("*");
		return b;
	}
	
	public boolean isAstrkDisplayed_email() {
		Boolean b = fldEmail.getText().contains("*");
		return b;
	}
	
	public boolean isAstrkDisplayed_pwd() {
		Boolean b = fldPassword.getText().contains("*");
		return b;
	}
	
	// Action methods to verify validation messages for mandatory fields
	public boolean getValMsg_fName() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement valMsgFirstName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='error-firstname']")));
			return (valMsgFirstName.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}
	
	public boolean getValMsg_lName() {
		try {
			return (valMsgLastName.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}
	
	public boolean getValMsg_email() {
		try {
			return (valMsgEmail.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}
	
	public boolean getValMsg_pwd() {
		try {
			return (valMsgPassword.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}
	
	public boolean isPrivacyPolicyChecked() {
		try {
			Boolean b = msgRegisterPage.isDisplayed();
			return b;
		} catch (Exception e) {
			return (false);
		}
	}

}
