package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//Elements
	@FindBy (xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy (xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement btnLogin;
	
	//Action Methods for valid login
	public void setEmail (String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword (String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	
	// Action method for invalid credentials warning
	public String chkWarning() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement msgWarning = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"alert\"]/div")));
		return (msgWarning.getText());
	}

}
