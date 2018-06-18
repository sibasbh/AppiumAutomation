package testdata_properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class getPropertyData {
	static Properties properties;
	
	
	 /** 
     * Loads Data from the *.properties file.
     * Create Object for Properties Class.
     * Call getProperties method from System Class and pass as argument to File Class.
     * Create object for FileReader class with file name as argument.
     * call load method.
     * 
     * @param null
     * @return deleteitem - Returns true if item is delected from the shopping cart.
     */
	
	public static void loadData() throws IOException{
		properties = new Properties();
		File f = new File(System.getProperty("\\Users\\anoopasidharth\\automation_test\\e-bayNativeApp\\src\\test\\java\\testdata_properties\\TestData.properties"));
		FileReader obj = new FileReader(f);
		properties.load(obj);
	}
	
	
	 /** 
     * getObject method to fetch data from properties file.
     * 
     * @param String Data
     * @return data - Returns Value for the respetive Key.
     */
	
	public static String getObject(String Data) throws IOException{
		loadData();
		String data = properties.getProperty(Data);
		return data;
	}
}
