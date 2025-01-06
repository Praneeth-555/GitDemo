package AutomationTesting.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTesting.TestComponents.BaseTest;
import AutomationTesting.TestComponents.RetryFailedTests;
import AutomationTesting.pageObjects.CartPage;
import AutomationTesting.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=RetryFailedTests.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{

		// WebDriverManager.chromedriver().setup();
		String productName = "QWERTY";
		landingPage.loginApplication("praneeth@gmail.com", "#praneeth123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//Assert.assertEquals("Incorrect email or pasfdd@@sword.", landingPage.getErrorMessage());// for failing assert statement
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		// WebDriverManager.chromedriver().setup();
		
		String productName = "QWERTY";
		//LandingPage landingPage= launchingApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("praneeth@gmail.com", "#praneeth*K123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match); 
		

	}

}
