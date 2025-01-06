package AutomationTesting.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.ReusableComponents.ReusableComponent;

public class ConfirmationPage extends ReusableComponent {
	
WebDriver d;
	
	public ConfirmationPage(WebDriver d)
	{
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{ 
		return confirmationMessage.getText();
	}
	
	

}
