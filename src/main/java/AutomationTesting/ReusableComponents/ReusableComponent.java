package AutomationTesting.ReusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.pageObjects.CartPage;

public class ReusableComponent {
	
	WebDriver d;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public ReusableComponent(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d, this);
	}


	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(d);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(d);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);//the below two lines of code perfectly works but the application dsign is as such that there is a hidden spinner 
		                   // which appears according to the load of the site.So the below lines of code check for that spinner as weell and waits
		                   //until it is invisible.So,instead use thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
