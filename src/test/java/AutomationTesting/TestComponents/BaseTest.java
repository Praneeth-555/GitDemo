package AutomationTesting.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationTesting.pageObjects.LandingPage;

public class BaseTest {
  public WebDriver d;
  
  
 public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		//the below two FileInputStream lines of code are working
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//AutomatonTesting//resources//GlobalData.properties");
		//FileInputStream fis= new FileInputStream("C://Users//kpran//eclipse-workspace//SeleniumFrameworkDesign//src//main//java//AutomatonTesting//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=	System.getProperty("browser")!=null ?System.getProperty("browser"): prop.getProperty("browser");
		//String browserName= prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");//this line makes the browser execution without invoking
			}
		    d = new ChromeDriver(options);
		    d.manage().window().setSize(new Dimension(1440,900)); // this is used for full screen mode,not necessary generally,but if test fails 
		                                                          //becuase od element not clickable,this can be used
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 d = new FirefoxDriver();	
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 d = new EdgeDriver();	
		}
		//WebDriver d = new ChromeDriver(); // this should not be written the above code should work,check it
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return d;
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		//The below code reads json to string format
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
        //now convert string to hashmap - for these we need to add extra dependencies to pom.xml file- Jackson Databind dependency must be added
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String ,String>> data= mapper.readValue(jsonContent, new TypeReference<List <HashMap<String,String>>>(){});
		// data is a list with two arguments containing two HashMaps(since in json we gave two data sets) - {{map},{map1}}
		return data;
		
	}
	
	public String getScreenShot(String testCaseName,WebDriver d) throws IOException
	 {
		 TakesScreenshot ts = (TakesScreenshot)d;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	 }
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchingApplication() throws IOException
	{
	    d=initializeDriver();
		landingPage = new LandingPage(d);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void driverClose()
	{
		d.close();
	}
}
