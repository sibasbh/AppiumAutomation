package TestCaseFile;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageObjects.ebay_SignIn;
import PageObjects.ebay_ProductSearch;
import PageObjects.ebay_ProductDetail;
import PageObjects.ebay_Checkout;
import ScreenShotCapture.captureScreenShots;
import testdata_properties.getObjects;

public class TestCase_ProductSearch {
	Logger logger = Logger.getLogger(TestCase_ProductSearch.class);
	String prodname;
	ebay_SignIn loginPage = new ebay_SignIn();
	ebay_ProductSearch prodlist = new ebay_ProductSearch();
	ebay_ProductDetail proddetail = new ebay_ProductDetail();
	ebay_Checkout checkout = new ebay_Checkout();
	captureScreenShots screen = new captureScreenShots();
	getObjects testdata = new getObjects();
	
	
	@BeforeTest 
	 /**
     * Logout of ebay application before startig the Test.
     */
	public void logoutBeforeTest() throws IOException {
		Assert.assertTrue(loginPage.applogout(), "Assertion Error : Error logging out");
	}
	
	
    /** 
     * Test Case:
     * Validate fileds in SignIn page
     * Sign in with valid crediantials.
     * Search for a product (eg: smartwatch)
     * Scroll and choose a Brand (eg: Motorola)
     * Scroll the page and select a product.
     * Product detail page : copy the product name to variable (eg: prodname)
     * Product detail page : Tap on Add to cart
     * Shopping Cart : Compare the product name in shopping cart with 'prodname'
     * Shopping Cart : Delete the item.
     */
	@Test
	public void ebay_shopping() throws IOException{
		boolean loginpg = loginPage.validateLoginpage();
		Assert.assertTrue(loginpg, "Assertion Error : Error Navigating to Login Page");
		
		String[] inputdata = testdata.initialise();
		Assert.assertNotNull(inputdata.length , "Asertion Error : Unable to get input from Properties file");
		
		boolean validatelogin = loginPage.testValidLogin(inputdata[0],inputdata[1]);
		Assert.assertTrue(validatelogin, "Assertion Error : Login Failed");
		
		boolean searchitem = prodlist.searchitem(inputdata[2]);
		Assert.assertTrue(searchitem, "Assertion Error : Error while searching product");
		
		boolean choosebrnd = prodlist.choosebrand();
		Assert.assertTrue(choosebrnd, "Assertion Error : Error choosing brand");
		
		boolean itemselected = prodlist.selectproduct(inputdata[3]);
		Assert.assertTrue(itemselected, "Assertion Error : Error during Product Selection");
		
		String prodname = proddetail.copyprodname();
		Assert.assertEquals(prodname, inputdata[4], "Assertion Error : Product name not matched with Testdata");
		
		boolean addcart = proddetail.addtocart();
		Assert.assertTrue(addcart, "Assertion Error : Error adding product to cart");
		
		boolean validateprodname = checkout.prodnamevalidation();
		Assert.assertTrue(validateprodname, "Assertion Error : Error validating product name");
		
		boolean deletitem = checkout.removeitem();
		Assert.assertTrue(deletitem, "Assertion Error : Item removed successfully");
		
	}
    
    /**
     * Logout of ebay application after completing the test.
     */
	
	@AfterTest
	public void logoutAfterTest() throws IOException {
		Assert.assertTrue(loginPage.applogout(), "Assertion Error : Error logging out");
	}
}
    

    		

    

 