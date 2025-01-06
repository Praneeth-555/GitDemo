package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="AutomationTesting.stepDefinitions",monochrome=true,plugin= {"html:target/cucumber.html"},
tags="@ErrorValidation")
public class TestNGTestRunner extends AbstractTestNGCucumberTests { //for JUnit test runner extends is not required
	
	
	
	

}
