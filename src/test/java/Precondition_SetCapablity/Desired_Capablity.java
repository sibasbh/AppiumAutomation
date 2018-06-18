package Precondition_SetCapablity;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;

public class Desired_Capablity {
	Logger logger = Logger.getLogger(Desired_Capablity.class);
	private static AndroidDriver<WebElement> Driver = null;

	@BeforeClass
	public AndroidDriver<WebElement> getDriver() 
	{
        return Driver;
	}
	
	public void initDriver() throws MalformedURLException
	{
		logger.info("Starting Test : Create Desired Capablities");
		
        File appDir = new File("src");
        File app = new File (appDir , "ebayApplication.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        
	    cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
	    cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	    cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
	    cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());      

	    try 
	    { 
	    	logger.info("Driver Initialised and Session created");
	    	Driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	    }

		catch (NullPointerException | MalformedURLException ex) 
	    {
			logger.error("Driver Initialisation Failed");;
			throw new RuntimeException("Unable to Initialise Appium Driver");
	    } 
	}


	@AfterClass
	public void tearDown()
	{
		logger.info("Tear down of the Driver");
		Driver.quit();
	} 
}


