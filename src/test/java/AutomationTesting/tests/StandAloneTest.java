package AutomationTesting.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.pageObjects.LandingPage;
import org.testng.Assert;
public class StandAloneTest {
	
	public static void main(String[] args) {
		
		//WebDriverManager.chromedriver().setup();
		WebDriver d = new ChromeDriver();
		LandingPage landingPage= new LandingPage(d);
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName="ZARA COAT 3";
		d.get("https://rahulshettyacademy.com/client/");
		d.findElement(By.id("userEmail")).sendKeys("praneeth@gmail.com");
		d.findElement(By.id("userPassword")).sendKeys("#praneeth*K123");
		d.findElement(By.id("login")).click();
		List<WebElement> products = d.findElements(By.cssSelector(".mb-3"));
		WebElement l= products.stream().filter(p->p.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst().orElse(null);
		//findFirst is used here because there can be many products with same name ZARA COAT 3, so we are retreiving the first found WebElement
		//WebElement l= products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null); 
		//you can use css in this way 
		l.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));// using this step of code is taking lot of time
		wait.until(ExpectedConditions.invisibilityOf(d.findElement(By.cssSelector(".ng-animating"))));
		//ng-animating is for the spinner that appears when you click on the add to cart button,since it is difficult to inspect the instructor gave 
		//its class name and we need to wait until it is invisible
		d.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts= d.findElements(By.xpath("//div/p/following-sibling::h3"));
		Boolean match=cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		System.out.println(match);
		d.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(d);
		a.sendKeys(d.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		d.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		d.findElement(By.cssSelector(".action__submit")).click();
		Assert.assertEquals(d.findElement(By.cssSelector(".hero-primary")).getText(), "Thankyou for the order.".toUpperCase());
		
		
		
		
		
	}

}
