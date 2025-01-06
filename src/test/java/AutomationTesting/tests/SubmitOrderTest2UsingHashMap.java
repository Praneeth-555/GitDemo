package AutomationTesting.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTesting.ReusableComponents.OrderPage;
import AutomationTesting.TestComponents.BaseTest;
import AutomationTesting.pageObjects.CartPage;
import AutomationTesting.pageObjects.CheckoutPage;
import AutomationTesting.pageObjects.ConfirmationPage;
import AutomationTesting.pageObjects.ProductCatalogue;

public class SubmitOrderTest2UsingHashMap extends BaseTest {
	
	@Test(dataProvider="getData",groups= {"Purchase1"})
	public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException
	{

		// WebDriverManager.chromedriver().setup();
		//LandingPage landingPage= launchingApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match); // no assertions should be written in page-object files beacuase validations are
									// done in test case files only
									// Page-object files should only have the code to perform actions
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "Thankyou for the order.".toUpperCase());

	}
	
	@Test(dependsOnMethods= {"submitOrderTest"},dataProvider="getData",groups= {"Purchase1"})
	public void OrderHistoryTest(HashMap<String,String> input) //Here we are checking when order is placed successfully whether the order is present  in the orders section or not
	{
		//also this method depends on the previous test if we observe carefully,so we write dependsonMethods
		//since this is a dependent test,first testNg run the submitordertest method then only this method runs
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password")); 
		OrderPage ordersPage= productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","praneeth@gmail.com" );
		map.put("password","#praneeth*K123" );
		map.put("product","QWERTY" );
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","praneeth@gmail.com" );
		map1.put("password","#praneeth*K123" );
		map1.put("product","ADIDAS ORIGINAL" );
		return new Object [] [] { {map},{map1}};
	}

}
