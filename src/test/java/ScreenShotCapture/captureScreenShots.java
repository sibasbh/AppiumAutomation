package ScreenShotCapture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Precondition_SetCapablity.DerivedClass;

public class captureScreenShots extends DerivedClass{
	
    /** 
     * Call DerivedClass class get the driver instance.
     * 
     * @param null
     * @return null
     */
	
    public captureScreenShots() {
        super();
    }       
    
    
    /** 
     * generate Screen shot and store in a folder as png file.
     * 
     * @param null
     * @return null
     */
    
	public void takeScreenshot() throws IOException {
		String folder_name = "Screenshot";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); 
		new File(folder_name).mkdir();
		String screenShotName = timeStamp.format(new Date()) +".png";
		FileUtils.copyFile(scrFile, new File(folder_name + "/" + screenShotName));
		}
}
