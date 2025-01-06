package AutomationTesting.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.ReusableComponents.ReusableComponent;

public class ProductCatalogue extends ReusableComponent {
	
	WebDriver d;
	public ProductCatalogue(WebDriver d)
	{
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);// here d is passed argumrnt
	}

	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".btn.w-10.rounded");
	By toastMessage=By.cssSelector("#toast-container");
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		//WebElement productDesired= getProductList().stream().filter(p->p.findElement(By.xpath("//b")).getText().trim().equals(productName)).findFirst().orElse(null);
		//the above xpath though correct is not working
		WebElement productDesired= getProductList().stream().filter(p->p.findElement(By.cssSelector("b")).getText().trim().equals(productName)).findFirst().orElse(null);
		return productDesired;	
		
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement productDesired=getProductByName(productName);
		productDesired.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}
	
}
