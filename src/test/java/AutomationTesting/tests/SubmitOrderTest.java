package AutomationTesting.tests;

import java.io.IOException;
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

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrderTest(String email,String password,String productName) throws IOException, InterruptedException
	{

		// WebDriverManager.chromedriver().setup();
		//LandingPage landingPage= launchingApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match); // no assertions should be written in page-object files beacuase validations are
									// done in test case files only
									// Page-object files should only have the code to perform actions
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "Thankyou for the order.".toUpperCase());

	}
	
	@Test(dependsOnMethods= {"submitOrderTest"},dataProvider="getData",groups= {"Purchase"})
	public void OrderHistoryTest(String email,String password,String productName) //Here we are checking when order is placed successfully whether the order is present  in the orders section or not
	{
		//also this method depends on the previous test if we observe carefully,so we write dependsonMethods
		//since this is a dependent test,first testNg run the submitordertest method then only this method runs
		ProductCatalogue productCatalogue = landingPage.loginApplication("praneeth@gmail.com", "#praneeth*K123"); 
		OrderPage ordersPage= productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object [] [] { {"praneeth@gmail.com","#praneeth*K123","QWERTY"},{"praneeth@gmail.com","#praneeth*K123","ADIDAS ORIGINAL"} };
	}

}
