package AutomationTesting.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer{

	int count =0;
	int maxTry=1;
	// how many times you want to run the test if it is failed,that is declared above
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}
	
	

}
