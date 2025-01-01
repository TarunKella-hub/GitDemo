package Sel.DataSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSourceJson {
	
	public List<HashMap<String, String>> getJsonData() throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src//main//java//Sel//Resources//Datasource.json"),StandardCharsets.UTF_8);
		ObjectMapper obj = new ObjectMapper();
		List<HashMap<String,String>> dataReader = obj.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return dataReader;
	}

}
