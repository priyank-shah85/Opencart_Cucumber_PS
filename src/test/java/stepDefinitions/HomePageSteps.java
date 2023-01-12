package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

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
	
	public static String featured_product_name = null;

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
	
	// Clicking on top container links
	@And("clicks on Shopping Cart button from top of the page")
	public void clicks_on_shopping_cart_button_from_top_of_the_page() {
		try
		{
			hp = new HomePage(driver);
			hp.clickShoppingCart();
			Thread.sleep(5000);
		} catch(Exception e)
		{
			Assert.assertTrue("Not able to click on Shopping Cart link.\n" + e.getMessage(), false);
		}
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
	
	// Verify clicks on top navigation bar menus
	@And("clicks on Show all Laptops & Notebooks link")
	public void clicks_on_Show_all_Laptops_Notebooks_link(){
		hp = new HomePage(driver);
		logger.info("Clicking on Show all Laptops & Notebooks menu--> ");
		hp.click_showAllLaptopsAndNotebooks();
	}
	
	@And("clicks on Cameras menu from top navigation bar")
	public void clicks_on_cameras_menu_from_top_navigation_bar(){
		hp = new HomePage(driver);
		try
		{
			logger.info("Clicking on Cameras menu--> ");
			hp.clickCameras();
		} catch (Exception e)
		{
			logger.info("Cameras menu is not exists or there is issue with the link. ");
			Assert.assertTrue(false);
		}
	}
	
	@And("clicks on Tablets menu from top navigation bar")
	public void clicks_on_Tablets_menu_from_top_navigation_bar() {
		hp = new HomePage(driver);
		try
		{
			logger.info("Clicking on Tablets menu--> ");
			hp.clickTablets();;
		} catch (Exception e)
		{
			logger.info("Tablets menu is not exists or there is issue with the link. ");
			Assert.assertTrue(false);
		}
	}
	
	// Click on first Featured product
	@And("clicks on first product from Featured list")
	public void clicks_on_first_product_from_featured_list() {
		hp = new HomePage(driver);
		//logger.info("Storing featured section");
		WebElement featured_product_section = driver.findElement(RelativeLocator.with(By.className("row")).below(By.xpath("//*[@id=\"content\"]/h3")));
		//logger.info("Storing featured products");
		List<WebElement> featured_products = featured_product_section.findElements(By.tagName("img"));
		
		// Getting element of first featured product and click on it
		if(featured_products.get(0).isDisplayed())
		{
			WebElement name = driver.findElement(RelativeLocator.with(By.tagName("h4")).below(featured_products.get(0)));
			featured_product_name = name.getText();
			Actions action = new Actions(driver);
			logger.info("Clicking on first featured product");
			action.moveToElement(featured_products.get(0)).click().build().perform();
			//featured_products.get(0).click();
		} else
		{
			logger.info("No product exists under Featured section.");
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
