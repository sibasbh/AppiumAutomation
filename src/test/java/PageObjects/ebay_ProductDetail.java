package PageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ScreenShotCapture.captureScreenShots;
import Precondition_SetCapablity.DerivedClass;
import io.appium.java_client.TouchAction;
 
public class ebay_ProductDetail extends DerivedClass{
 
    PageObjects productdetail;
    Logger logger;
    String pname;
    TouchAction t;
    captureScreenShots screen;
    
    
    /** 
     * Create Object for getLogger class.
     * Imports AndroidDriver from super class DerivedClass.
	 * Create Object for PageObject class.
	 * Create Object for TouchAction class.
	 * Call initElements method to create findEement call.
	 * Create Object for captureScreenShots class.
	 * 
	 * 
     * @param null
     * @return null
     */
  
    public ebay_ProductDetail() {
        super();
        logger = Logger.getLogger(ebay_ProductSearch.class);
        t = new TouchAction(driver);
        productdetail = new PageObjects();
        PageFactory.initElements(driver, productdetail);
        screen = new captureScreenShots();
    }                   
    
    
    
    /** 
     * Copy Product name from Product detail page and store in a variable.
     * 
     * @param null
     * @return pname - Contains the product name.
     */
    
    public String copyprodname() throws IOException {
    	try {
	    	pname= productdetail.prodname.getText();
	    	if (pname != null){
	    		logger.info("Stored Product Name successfully");	
	    	}
	    	else{
	    		logger.error("Error storing Product name");
	    		screen.takeScreenshot();
	    	}
    	}
    	catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
   		}
    	return pname;
    }
    
    
    
    /** 
     * Add Product to Shopping Cart.
     * 
     * @param null
     * @return prodname - Returns true if the product is successfully added to Shopping Cart.
     */
    
    public Boolean addtocart() throws IOException {
    	boolean prodname = false;
    	try {
	        WebElement brand = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"));");
	        t.tap(brand).perform();
	        if (productdetail.cartadded.isDisplayed()){
	        	logger.info("Product added to cart");
	        	productdetail.gotocart.click();
	        	prodname = true;
	        }
	        else{
	        	logger.error("Error while adding to cart");
	        	screen.takeScreenshot();
	        }
    	}
    	catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
   		}
        return prodname;
    }
    
    
  
    class PageObjects {

        @FindBy(xpath = "//android.widget.TextView[resource-id ='com.ebay.mobile:id/textview_item_name']")
        public WebElement prodname;
        
        @FindBy(xpath = "//android.widget.Button[@text ='ADD TO CART']")
        public WebElement Addcartbtn;
        
        @FindBy(xpath = "//android.widget.TextView[@text ='Added to cart']")
        public WebElement cartadded;
        
        @FindBy(xpath = "//android.widget.Button[@text ='GO TO CART']")
        public WebElement gotocart;
        
    }
}
    
  
   