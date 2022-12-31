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
	@FindBy(xpath = "//*[@id=\"account\"]/div[1]/label")
	WebElement fldFirstName;
	
	@FindBy(xpath = "//*[@id=\"account\"]/div[2]/label")
	WebElement fldLastName;
	
	@FindBy(xpath = "//*[@id=\"account\"]/div[3]/label")
	WebElement fldEmail;
	
	@FindBy(xpath = "//*[@id=\"form-register\"]/fieldset[2]/div/label")
	WebElement fldPassword;
	
	@FindBy(xpath = "//*[@id=\"form-register\"]/fieldset[3]/div/label")
	WebElement fldNewsletter;
	
	@FindBy(xpath = "//*[@id=\"form-register\"]/div/div/div/label")
	WebElement fldPrivacyPolicy;
	
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
	
	public void chkSubscribe_yes() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"input-newsletter-yes\"]")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	public void chkSubscribe_no() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"input-newsletter-no\"]")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
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
	
	// Action methods to verify placeholder
	public String chkPh_fName() {
		String ph1 = txtFirstName.getAttribute("placeholder");
		return ph1;
	}
	
	public String chkPh_lName() {
		String ph2 = txtLastName.getAttribute("placeholder");
		return ph2;
	}
	
	public String chkPh_email() {
		String ph3 = txtEmail.getAttribute("placeholder");
		return ph3;
	}
	
	public String chkPh_pwd() {
		String ph4 = txtPassword.getAttribute("placeholder");
		return ph4;
	}
	
	// Action methods to verify fields on page
	public String isAvailable_fName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement fldFirstName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"account\"]/div[1]/label")));
		return (fldFirstName.getText());
	}
	
	public String isAvailable_lName() {
		return (fldLastName.getText());
	}
	
	public String isAvailable_email() {
		return (fldEmail.getText());
	}
	
	public String isAvailable_pwd() {
		return (fldPassword.getText());
	}
	
	public String isAvailable_newsletter() {
		return (fldNewsletter.getText());
	}
	
	public String isAvailable_prvPolicy() {
		return (fldPrivacyPolicy.getText());
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
	
	public boolean registerHeading() {
		try {
			Boolean b = msgRegisterPage.isDisplayed();
			return b;
		} catch (Exception e) {
			return (false);
		}
	}
	
	public boolean getValMsg_invlidEmail() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement valMsgInvalidEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"error-email\"]")));
			return (valMsgInvalidEmail.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

}
