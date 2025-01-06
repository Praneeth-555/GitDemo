package AutomationTesting.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AutomationTesting.TestComponents.BaseTest;
import AutomationTesting.pageObjects.CartPage;
import AutomationTesting.pageObjects.CheckoutPage;
import AutomationTesting.pageObjects.ConfirmationPage;
import AutomationTesting.pageObjects.LandingPage;
import AutomationTesting.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImplementations extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on E-Commerce page")
	public void I_landed_on_ECommerce_page() throws IOException
	{
		landingPage=launchingApplication(); 
	}
	
	public void syso() {
		// TODO Auto-generated method stub
            System.out.println("post completion of IST TIME");
            System.out.println("now its EST TIME");
	}
	
	@Given("^Logged in with the username (.+) and password (.+)$") //regex expressiom
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^Add product (.+) to cart$")
	public void Add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	 @And("^Checkout (.+)  and submit the order$") //here @When also can be used
	 public void checkout_submit_order(String productName)
	 {
		 CartPage cartPage = productCatalogue.goToCartPage();
		 Boolean match = cartPage.VerifyProductDisplay(productName);
		 Assert.assertTrue(match); 
		 CheckoutPage checkoutPage = cartPage.goToCheckout();
		 checkoutPage.selectCountry("india");
		 confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on the confirmation page")// here the text is static so we are using {} curly braces to capture
	 public void message_is_displayed_on_the_confirmation_page(String string)    //if the data was dynamic we use ^.....$
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
		 Assert.assertEquals(confirmMessage, string.toUpperCase());
		 d.close();

	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string)
	 {
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 d.close();
	 }
	 
	 
	 

}
