package Precondition_SetCapablity;

import io.appium.java_client.android.AndroidDriver;

public class DerivedClass extends Desired_Capablity
{
	protected static AndroidDriver driver;
	public void Driver() 
	{
        this.driver = super.getDriver();
    }
}
