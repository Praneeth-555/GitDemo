package AutomationTesting.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReaderUsingJson {
	
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
		//The below code reads json to string format
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//AutomationTesting//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);
        //now convert string to hashmap - for these we need to add extra dependencies to pom.xml file- Jackson Databind dependency must be added
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String ,String>> data= mapper.readValue(jsonContent, new TypeReference<List <HashMap<String,String>>>(){});
		// data is a list with two arguments containing two HashMaps(since in json we gave two data sets) - {{map},{map1}}
		return data;
		
	}
	

}
