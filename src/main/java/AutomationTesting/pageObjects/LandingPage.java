package AutomationTesting.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.ReusableComponents.ReusableComponent;

public class LandingPage extends ReusableComponent {
	
	WebDriver d;
	public LandingPage(WebDriver d)
	{
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);// here d is passed argument,PageFactory is the design pattern
	}

	//WebElement userEmails =d.findElement(By.id("userEmail"));
	//the same above step can be written by PageFactory Design Pattern as shown below
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	//div[aria-label='Incorrect email or password.']
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String mail,String passcode)
	{
		userEmail.sendKeys(mail);
		password.sendKeys(passcode);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(d);
		return productCatalogue;
	}
	
	public void goTo()
	{
		d.get("https://rahulshettyacademy.com/client/");
	}
	
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
	

}
