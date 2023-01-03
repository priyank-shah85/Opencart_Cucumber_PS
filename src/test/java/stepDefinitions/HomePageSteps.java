package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@Then("page displays heading as {string}")
	public void page_displays_heading_as(String expectedMsg) {
		hp = new HomePage(driver);
		String actualMsg = hp.compareSearchPage();
		try
		{
			logger.info("Correct search page displays ");
			Assert.assertEquals(expectedMsg, actualMsg);
		} catch (Exception e)
		{
			Assert.fail("Incorrect message for product search " + actualMsg + " ");
		}
	}
	
	@Then("page displays message as {string}")
	public void page_displays_message_as(String expectedMsg) {
		hp = new HomePage(driver);
		String actualMsg = hp.noResultReturned();
		try
		{
			logger.info("Correct message for no results ");
			Assert.assertEquals(expectedMsg, actualMsg);
		} catch (Exception e)
		{
			Assert.fail("Incorrect message when no results returned for given search " + actualMsg + " ");
		}
	}
	
	@And("page title contains {string} word")
	public void page_title_contains_word(String expectedTitle) {
		hp = new HomePage(driver);
		String actualTitle = driver.getTitle();
		try
		{
			if (actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()))
			{
				logger.info("Search keyword "+ expectedTitle + " present in page title. ");
				Assert.assertTrue(true);
			}
		}catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@And("page returns all products matching with search keyword {string}")
	public void page_returns_all_products_matching_with_search_keyword(String searchWord) {
		hp = new HomePage(driver);
		List<WebElement> list_of_products = driver.findElements(By.tagName("h4"));
		String product_name;
		int total_products = 0;
		try
		{
			for(int i=0; i<list_of_products.size(); i++)
			{
				product_name = list_of_products.get(i).getText();
				if(product_name.toLowerCase().contains(searchWord.toLowerCase()))
				{
					Assert.assertTrue(true);
					total_products = total_products + 1;
				}
				else
				{
					continue;
				}
			}
			logger.info("Total products found: " + total_products + ".");
		}catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	@And("page returns no products as search results")
	public void page_returns_no_products_as_search_results() {
		hp = new HomePage(driver);
		List<WebElement> list_of_products = driver.findElements(By.tagName("h4"));
		try
		{
			if(list_of_products.size() == 0)
			{
				logger.info("Correct behavior when searching with blank/incorrect keyword ");
				Assert.assertTrue(true);
			} else
			{
				logger.info("Search returned products without matching keyword ");
				Assert.assertTrue(false);
			}
		} catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	// Verify search with Product Description checked
	@And("user checks Search in product descriptions checkbox")
	public void user_checks_Search_in_product_descriptions_checkbox() {
		hp = new HomePage(driver);
		hp.chkProductDesc();
	}
	
	@And("user clicks on Search button")
	public void user_clicks_on_Search_button() {
		hp = new HomePage(driver);
		hp.clickSearchbtn();
	}
	
	@Then("page returns all products where {string} is present either in product name or description")
	public void page_returns_all_products_where_is_present_either_in_product_name_or_description(String keyword) {
		hp = new HomePage(driver);
		List<WebElement> initial_list_of_products = driver.findElements(By.tagName("h4"));
		WebElement product_link;
		String product_name;
		int total_products = 0;
		try
		{
			for(int i=0; i<initial_list_of_products.size(); i++)
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				logger.info("Product name assignment starts ");
				List<WebElement> list_of_products = driver.findElements(By.tagName("h4"));
				product_name = list_of_products.get(i).getText();
				//product_name = list_of_products.get(i).getText();
				//product_link = list_of_products.get(i).findElement(By.linkText(product_name));
				//logger.info(product_link.getText());
				logger.info("Product name assigned as: " + product_name + " ");
				if(product_name.toLowerCase().contains(keyword.toLowerCase()))
				{
					logger.info("Product name if block starts");
					Assert.assertTrue(true);
					total_products = total_products+1;
					logger.info("Product name if block ends");
				}
				else
				{
					product_link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(product_name)));
					logger.info("Clicking on product name link ");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", product_link);
					//product_link.click();
					WebElement descriptionTab = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Description")));
					logger.info("Clicking on Description tab ");
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", descriptionTab);
					logger.info("Clicked on Description tab ");
					//wait.wait(10000);
					WebElement descriptionText = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-description")));
					logger.info(descriptionText.getText());
					if(descriptionText.getText().toLowerCase().contains(keyword.toLowerCase()))
					{
						logger.info("Product " + product_name + "'s description contains " + keyword + ".");
						Assert.assertTrue(true);
						total_products = total_products+1;
					}
					else
					{
						logger.info("Product " + product_name + "'s description does not contain " + keyword + ".");
						Assert.assertTrue(false);
					}
					driver.navigate().back();
				}
			}
			logger.info("Total products returned as search results: " + total_products + ".");
		} catch (Exception e)
		{
			Assert.assertTrue(e.getMessage(), false);
		}
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
