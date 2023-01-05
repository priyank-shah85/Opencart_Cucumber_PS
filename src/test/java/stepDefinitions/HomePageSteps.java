package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class HomePageSteps {
	static WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;

	List<HashMap<String, String>> datamap; // Data driven

	static Logger logger; // for logging
	ResourceBundle rb; // for reading properties file
	String br; // to store browser name

	@Before
	public void setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");

	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======>" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
		driver.quit();
	}

	@Given("User launch browser")
	public void user_launch_browser() {
		if (br.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			driver = new ChromeDriver(options);
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@And("opens URL {string}")
	public void opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User navigates to MyAccount menu")
	public void user_navigate_to_my_account() {
		hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account ");

	}

	@And("click on Login")
	public void click_on_login() {
		hp = new HomePage(driver);
		hp.clickLogin();
		logger.info("Clicked on Login ");
	}

	/*
	 * @And("User enters email as {string} and password as {string}")
	 * public void user_enters_email_as_and_password_as(String email, String pwd) 
	 * { 
	 * 		lp = new
	 * 		LoginPage(driver);
	 * 
	 * 		lp.setEmail(email);
	 * 		logger.info("Provided Email ");
	 * 		lp.setPassword(pwd);
	 *		logger.info("Provided Password ");
	 * }
	 * 
	 * @And("click on Login button")
	 * public void click_on_login_button()
	 * {
	 * 		lp.clickLogin();
	 * 		logger.info("Clicked on Login button");
	 *  }
	 */

	/*
	@Then("User navigates to MyAccount page")
	public void user_navigates_to_my_account_page() {
		macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();

		if (targetpage) {
			logger.info("Login Success ");
			Assert.assertTrue(true);
		} else {
			logger.error("Login Failed ");
			Assert.assertTrue(false);
		}

	}
	*/
	
	@And("click on Register")
	public void click_on_register() {
		hp = new HomePage(driver);
		hp.clickRegister();
		logger.info("Clicked to register new account ");
	    
	}
	
	// Verify search result(s)
	@And("user enters {string} word in search box")
	public void user_enters_word_in_search_box(String word) {
		hp = new HomePage(driver);
		logger.info("Entered search keyword as "+ word +" ");
		hp.txtSearch(word);
	}
	
	@And("user clicks on Search icon")
	public void user_clicks_on_Search_icon() {
		hp = new HomePage(driver);
		logger.info("Clicked on search icon ");
		hp.clickSearch();
	}
	
	// Clicking on Show all Laptops & Notebooks link from top navigation bar
	@And("clicks on Show all Laptops & Notebooks link")
	public void clicks_on_Show_all_Laptops_Notebooks_link(){
		hp = new HomePage(driver);
		logger.info("Clicking on Show all Laptops & Notebooks menu ");
		hp.click_showAllLaptopsAndNotebooks();
	}

	//******* Data Driven test method **************
	@Then("check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
	public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) {
		datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\OpenCart_LoginData.xlsx", "Sheet1");

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		String exp_res = datamap.get(index).get("result");

		lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);

		lp.clickLogin();
		macc = new MyAccountPage(driver);
		try {
			boolean targetpage = macc.isMyAccountPageExists();

			if (exp_res.equals("Valid")) {
				if (targetpage == true) {
					macc.clickMyAccount();
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp_res.equals("Invalid")) {
				if (targetpage == true) {
					macc.clickMyAccount();
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {

			Assert.assertTrue(false);
		} 
		driver.close();
	} 
}
