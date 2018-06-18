package PageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ScreenShotCapture.captureScreenShots;


import Precondition_SetCapablity.DerivedClass;
 
public class ebay_Checkout extends DerivedClass{
 
    PageObjects checkout;
    Logger logger;
    ebay_ProductDetail prodname;
    String pname;
    captureScreenShots screen;
    
    
    /** 
     * Create Object for getLogger class.
     * Imports AndroidDriver from super class DerivedClass.
	 * Create Object for PageObject class.
	 * Call initElements method to create findEement call.
	 * Create Object for captureScreenShots class.
	 * 
	 * 
     * @param null
     * @return null
     */
    
    public ebay_Checkout() {
        super();
        logger = Logger.getLogger(ebay_ProductSearch.class);
        prodname = new ebay_ProductDetail();
        checkout = new PageObjects();
        screen = new captureScreenShots();
        PageFactory.initElements(driver, checkout);
    }                   
  
    
    
    /** 
     * Validate if the Product name matches in the Product Detail and Shopping Cart pages.
     * 
     * @param null
     * @return productmatch - Returns true of the Product name matches in Product detail and shopping cart pages.
     */
    
    public Boolean prodnamevalidation() throws IOException {
    	boolean productmatch = false;
    	try {
	    	String nameProddetailPg= prodname.copyprodname();
	    	if (checkout.cartprodname.getText() == nameProddetailPg){
	    		logger.info("Item Name same it Prod Detail and Checkout Pages");
	    		productmatch = true;
	    	}
	    	else {
	    		logger.error("Error matching Prod info");
	    		screen.takeScreenshot();
	    	}
    	}
    	catch (Exception e){
	    	logger.error("Exception: ",e);
	    	screen.takeScreenshot();
   		}
    	return productmatch;
    }
    
    
    /** 
     * Clear the Shopping Cart after adding an item.
     * 
     * @param null
     * @return deleteitem - Returns true if item is delected from the shopping cart.
     */
    
    public Boolean removeitem() throws IOException {
    	 boolean deleteitem = false;
    	 try {
	    	 checkout.removeitm.click();
	    	 WebDriverWait wait = new WebDriverWait(driver, 15);
	         wait.until(ExpectedConditions.visibilityOf(checkout.removalert));
	         checkout.removalertbtn.click();
	         if (checkout.emptycart.isDisplayed()){
	        	 checkout.closecart.click();
	        	 logger.info("Deleted from Cart");
	        	 deleteitem = true;
	         }
	         else{
	        	 logger.error("Error deleting item");
	        	 screen.takeScreenshot();
	         }
    	 }
    	 catch (Exception e){
 	    	logger.error("Exception: ",e);
 	    	screen.takeScreenshot();
    		}
    	return deleteitem;
    }
    
    
  
    class PageObjects {
        @FindBy(xpath = "//android.widget.TextView[resource-id ='com.ebay.mobile:id/title']")
        public WebElement cartprodname;    
        
        @FindBy(xpath = "//android.widget.Button[@text ='Remove']")
        public WebElement removeitm;
        
        @FindBy(xpath = "//android.widget.TextView[@text ='Remove item']")
        public WebElement removalert;
        
        @FindBy(xpath = "//android.widget.Button[@text ='REMOVE']")
        public WebElement removalertbtn;
        
        @FindBy(xpath = "//android.widget.TextView[@text ='Your shopping basket is empty. Fill it up!']")
        public WebElement emptycart;
        
        @FindBy(className = "android.widget.ImageButton")
        public WebElement closecart;
    }
}

    
  
   