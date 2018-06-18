package PageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Precondition_SetCapablity.DerivedClass;
import ScreenShotCapture.captureScreenShots;
 
public class ebay_SignIn extends DerivedClass{
 
    PageObjects loginPage;
    Logger logger = Logger.getLogger(ebay_SignIn.class);
    captureScreenShots screen;
    
 
    /** 
     * Imports AndroidDriver from super class DerivedClass.
     * Create Object for PageObject class.
     * Call initElements method to create findEement call.
     * 
     * @param null
     * @return null
     */
    
    public ebay_SignIn() {
        super();
        loginPage = new PageObjects();
        PageFactory.initElements(driver, loginPage);
        screen = new captureScreenShots();
    }                   
  
    
    
    /** 
     * Logout of the ebay application.
     * 
     * @param null
     * @return logoutstatus Returns true if application is not Logged In and on Successful Logout
     */
    
    public boolean applogout() throws IOException {
        boolean logoutstatus = false;
        try{
	        if(loginPage.Signbtn.isDisplayed()) {
	        	logoutstatus = true;
	        }
	        else {
	        	loginPage.menubr.click();
	        	loginPage.dropdown.click();
	        	loginPage.signout.click();
	        	
			    if (loginPage.alerttext.getText().equalsIgnoreCase("eBay Mobile notifications are not delivered when signed out")) {
			        loginPage.alertokbtn.click();
			        logoutstatus = true;
		        	}
	        	else{
	        		logger.error("Error Logging out");
	        		screen.takeScreenshot();
	        		}
	        	}
	        }
    	catch (Exception e){
    		screen.takeScreenshot();
	    	logger.error("Exception: ",e);
    	}
        return logoutstatus;
    }


   
    /** 
     * Validate the fields in the Sign In page.
     * Check if all fileds are seen before Logging in.
     * 
     * @param null
     * @return elements Returns true if all the fields in login screen are displayed.
     */
    
    public boolean validateLoginpage() throws IOException{
    	boolean elements = false;
    	try {   
	        loginPage.Signbtn.click();
	        if(loginPage.emailField.isDisplayed()){
	            if(loginPage.passwordField.isDisplayed()){
	                if(loginPage.loginBtn.isDisplayed()){
	                        elements = true;
	                }
	            }
	        }
	        else{
	            logger.error("Error validating login page");
	            screen.takeScreenshot();
	        }
    	}
    	catch (Exception e){
    		screen.takeScreenshot();
	    	logger.error("Exception: ",e);
    	}
        return elements;
    }
    
    
	/** 
	 * Navigate to login page and sign in with valid crediantials.
	 * 
	 * @param userName passWord 
	 * @return loginStatus Returns true if login is successful.
	 */

    public boolean testValidLogin(String userName,String passWord) throws IOException {
        boolean loginStatus = false;
        try {
	        loginPage.emailField.sendKeys(userName);
	        loginPage.passwordField.sendKeys(passWord);
	        loginPage.loginBtn.click();
	        WebDriverWait wait = new WebDriverWait(driver, 15);
	        wait.until(ExpectedConditions.visibilityOf(loginPage.nothanks));
	        if(loginPage.nothanks.isDisplayed()){
	        	loginPage.nothanks.click();
	        	logger.info("Logged out Successfully");
	        	loginStatus = true;
	        }
	        else{
	        	logger.error("Error logging in");
	        	screen.takeScreenshot();
	        }
        }
     	catch (Exception e){
     		screen.takeScreenshot();
	    	logger.error("Exception: ",e);
    	}
        return loginStatus;
      }
    
 
    
    class PageObjects {
    	
        @FindBy(xpath = "//android.widget.Button[text='SIGN IN']")
        public WebElement Signbtn;
        
        @FindBy(xpath = "//android.widget.TextView[@text = 'Email or username']")
        public WebElement emailField;
 
        @FindBy(xpath = "//android.widget.TextView[@text = 'Password']")
        public WebElement passwordField;

        @FindBy(xpath = "//android.widget.Button[@text ='SIGN IN']")
        public WebElement loginBtn;
     
        @FindBy(xpath = "//android.widget.ImageButton[resource-id = 'com.ebay.mobile:id/home']")
        public WebElement menubr;
 
        @FindBy(xpath = "//android.widget.CheckedTextView[resource-id = 'com.ebay.mobile:id/textview_sign_in_status']")
        public WebElement dropdown;
        
        @FindBy(xpath = "//android.widget.TextView[resource-id = 'com.ebay.mobile:id/preonboarding_title']")
        public WebElement signout;
        
        @FindBy(xpath = "//android.widget.TextView[resource-id = 'com.ebay.mobile:id/alertTitle']")
        public WebElement alerttext;
        
        @FindBy(xpath = "//android.widget.Button[@text ='OK']")
        public WebElement alertokbtn;
        
        @FindBy(xpath = "//android.widget.Button[@text ='NO THANKS']")
        public WebElement nothanks;

        
    }
}