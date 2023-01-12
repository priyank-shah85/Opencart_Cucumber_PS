package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDisplayPage extends BasePage {

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
	}
	
	// Product details elements
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/h1")
	WebElement productTitle;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[1]/div/a/img")
	WebElement productLargeImage;
	
	@FindBy(className = "price-new")
	WebElement productUnitPrice;
	
	@FindBy(id = "input-quantity")
	WebElement productQty;
	
	@FindBy(id = "button-cart")
	WebElement btnAddToCart;
	
	// Light Box view elements
	@FindBy(className = "mfp-img")
	WebElement currentLightBoxImage;
	
	@FindBy(className = "mfp-arrow-left")
	WebElement leftArrow;
	
	@FindBy(className = "mfp-arrow-right")
	WebElement rightArrow;
	
	@FindBy(className = "mfp-close")
	WebElement closeLightBox;
	
	// Elements for Review tab
	@FindBy(xpath = "//*[@id=\"content\"]/ul/li[2]/a")
	WebElement tabReview;
	
	@FindBy(id = "input-name")
	WebElement txtYourName;
	
	@FindBy(id = "input-text")
	WebElement txtYourReview;
	
	@FindBy(id = "input-rating")
	WebElement ratingSection;
	
	@FindBy(id = "button-review")
	WebElement btnContinue;
	
	@FindBy(xpath = "//*[@id=\"alert\"]/div")
	WebElement msgSuccess;
	
	// Action methods for product details
	public String productTitle() {
		return productTitle.getText();
	}
	
	public boolean productImage() {
		return productLargeImage.isDisplayed();
	}
	
	public void clickProductImage() {
		productLargeImage.click();
	}
	
	public String UnitPrice() {
		return (productUnitPrice.getText());
	}
	
	public void clearExistingQty() {
		productQty.clear();
	}
	
	public void changeQty(int qty) {
		productQty.sendKeys(String.valueOf(qty));
	}
	
	public void clickAddTocart() {
		btnAddToCart.click();
	}
	
	/* Capture product large image and store in local project structure
	public void captureLargeImage(WebDriver driver1) throws IOException {
	    Screenshot LargeImageScreenshot = new AShot().takeScreenshot(driver1, productLargeImage);
	    ImageIO.write(LargeImageScreenshot.getImage(),"png",new File(System.getProperty("user.dir") + "\\Images\\Nikon D300.png"));
	    File f = new File(System.getProperty("user.dir") + "\\Images\\Nikon D300.png");
	    if(f.exists())
	    {
	    	System.out.println("Image File Captured.");
	    }
	    else
	    {
	    	System.out.println("Image File NOT exists.");
	    }
	}
	
	// Now comparing both images
	public void LightBoxImageComparison(WebDriver driver2) throws IOException {
		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Nikon D300.png"));
        
        Screenshot LightBoxImageScreenshot = new AShot().takeScreenshot(driver2, currentLightBoxImage);
        BufferedImage actualImage = LightBoxImageScreenshot.getImage();
               
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        if(diff.hasDiff()==true)
        {
        	System.out.println("Images are Not Same.");
        }
        else
        {
        	System.out.println("Images are Same.");
        }
	} */
	
	// Action methods for Light Box images
	public boolean verifyLightBoxCurrentImage() {
		if(currentLightBoxImage.isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public String getCurrentImageName() {
		return currentLightBoxImage.getAttribute("src");
	}
	
	public void clickLeftArrow() {
		leftArrow.click();
	}
	
	public void clickRightArrow() {
		rightArrow.click();
	}
	
	public void clickClose() {
		closeLightBox.click();
	}
	
	// Action methods for adding new review
	public void clickReviewTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement review = wait.until(ExpectedConditions.elementToBeClickable(tabReview));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", review);
	}
	
	public void enterName(String fullName) {
		txtYourName.sendKeys(fullName);
	}
	
	public void enterReview(String review) {
		txtYourReview.sendKeys(review);
	}
	
	public WebElement ratingSection() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement rating = wait.until(ExpectedConditions.elementToBeClickable(ratingSection));
		return rating;
	}
	
	public void clickContinue() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement add = wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", add);
	}
	
	public String chkMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement msg = wait.until(ExpectedConditions.elementToBeClickable(msgSuccess));
		return (msg.getText());
	}

}
