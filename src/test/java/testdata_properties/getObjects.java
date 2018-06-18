package testdata_properties;

import java.io.IOException;

public class getObjects extends getPropertyData{
	
	 /** 
     * Call getObject method and store values from properties file in an array.
     * 
     * @param String Data
     * @return data - Returns Value for the respetive Key.
     */
	
		public String[] initialise() throws IOException{
			String[] data = new String[4];
			data[0] = getObject("emailaddress");
			data[1] = getObject("password");
			data[2] = getObject("productname");
			data[3] = getObject("itemname");
			return data;
		}
}