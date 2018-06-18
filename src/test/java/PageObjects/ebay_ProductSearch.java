package PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Precondition_SetCapablity.DerivedClass;
import ScreenShotCapture.captureScreenShots;
import io.appium.java_client.TouchAction;
 
public class ebay_ProductSearch extends DerivedClass{
 
    PageObjects productsearch;
    Logger logger;
    TouchAction t;
    captureScreenShots screen;
   

	/** 
	 * Imports AndroidDriver from super class DerivedClass.
	 * Create Object for PageObject class.
	 * Call initElements method to create findEement call.
	 * Create Object for captureScreenShots class.
	 * 
	 * @param null
	 * @return null
	 */

    public ebay_ProductSearch() {
        super();
        logger = Logger.getLogger(ebay_ProductSearch.class);
        screen = new captureScreenShots();
        t = new TouchAction(driver);
        productsearch = new PageObjects();
        PageFactory.initElements(driver, productsearch);
    }                   
 
    

    /** 
     * Search for a product from searchbar.
     * 
     * @param prodname - Name of the Product
     * @return Searchitem - Flag set to true if search is successful.
     */
    
    public boolean searchitem(String prodname) throws IOException {
	    boolean Searchitem = false;
	    try {
	    	productsearch.SearchBar.click();
	    	productsearch.SecondSearchBar.sendKeys(prodname);
	    	productsearch.keybtSearch.click();
	    	WebDriverWait wait = new WebDriverWait(driver, 15);
	        wait.until(ExpectedConditions.visibilityOf(productsearch.shopbybrand));
	        if(productsearch.shopbybrand.isDisplayed()){
	        	Searchitem = true;
	        	logger.info("Search Successful");
	        	}
	        else{
	        	 logger.error("Search Failed");
	        	 screen.takeScreenshot();
	        	 
		       }
    	}
    	catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
    		}
        return Searchitem;
    	}
    

    
    /** 
     * Scrolling and Selecting a brand in the Product list page.
     * 
     * @param null 
     * @return Choosebrand - Returns true if Scrolling and Selcting a brand is successfull. 
     */
    
    public boolean choosebrand() throws IOException {
    	boolean Choosebrand = false;
    	try {
	    	t.press(productsearch.brandApplebt).moveTo(productsearch.brandMotobt).release().perform();
	    	if (productsearch.brandMotobt.isDisplayed()){
	    		productsearch.brandMotobt.click();
	    		Choosebrand = true;
	    		logger.info("Scrolling Successful");
	    	}
	    	else{
	    		logger.error("Scrolling Failed");
	    		screen.takeScreenshot();
	    	}	
    	}
    	catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
    		}
        return Choosebrand;
    }
    
    
    /** 
     * Scrolling and Selecting a product in the product list is successful.
     * 
     * @param null
     * @return itemselected - Returns true if Scrollling and Selecting a product is successful.
     */
    
    public boolean selectproduct(String selector) throws IOException {
       boolean itemselected = false;
       try {
	       WebElement brand = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"+selector+\"));");
	       t.tap(brand).perform();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       if (productsearch.headingProdDetail.isDisplayed()){
	    	   itemselected = true;
	    	   logger.info("Product Selected Successfully");
	       }
	       else{
	    	   logger.error("Product Selection failed");
	    	   screen.takeScreenshot();
	       }
       }
       catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
   		}
       return itemselected;
    }

    
  
    class PageObjects {

        @FindBy(xpath = "//android.widget.TextView[@text ='Search for anything']")
        public WebElement SearchBar;
        
        @FindBy(xpath = "//android.widget.EditText[@text ='Search for anything']")
        public WebElement SecondSearchBar;
 
        @FindBy(className = "android.widget.ImageView")
        public WebElement keybtSearch;

        @FindBy(xpath = "//android.widget.TextView[@text ='Apple']")
        public WebElement brandApplebt;
 
        @FindBy(xpath = "//android.widget.TextView[@text ='Motorola']")
        public WebElement brandMotobt;
        
        @FindBy(className = "android.widget.RelativeLayout")
        public List<WebElement> itemlist;
       
        @FindBy(xpath = "//android.widget.ImageView[resource-id='com.ebay.mobile:id/logo']")
        public WebElement headingProdDetail;
        
        @FindBy(xpath = "//android.widget.TextView[@text ='Shop by Brand']")
        public WebElement shopbybrand;
        
        
    }
}