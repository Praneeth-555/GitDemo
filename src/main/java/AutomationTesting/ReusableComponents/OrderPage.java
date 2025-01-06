package AutomationTesting.ReusableComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends ReusableComponent{
	WebDriver d;
	public OrderPage(WebDriver d)
	{
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}

}
