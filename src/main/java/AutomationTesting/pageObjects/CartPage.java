package AutomationTesting.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.ReusableComponents.ReusableComponent;

public class CartPage extends ReusableComponent {
	
	WebDriver d;
	//List<WebElement> cartProducts= d.findElements(By.xpath("//div/p/following-sibling::h3"));
	@FindBy(xpath="//div/p/following-sibling::h3")
	List<WebElement> cartProducts;
	
	//d.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	
	public CartPage(WebDriver d)
	{
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		return new CheckoutPage(d);
		
	}
	
	

	
	
}
