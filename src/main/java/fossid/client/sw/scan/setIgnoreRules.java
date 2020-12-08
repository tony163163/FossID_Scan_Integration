package fossid.client.sw.scan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//import fossid.client.sw.uploading.deleteCompressedFile;
import fossid.client.sw.values.loginValues;
import fossid.client.sw.values.projectValues;

public class setIgnoreRules {
	
	loginValues lvalues = loginValues.getInstance();
	projectValues pvalues = projectValues.getInstance();
	//deleteCompressedFile dvalue = new deleteCompressedFile(); 

	public void setignoreRules(String valueTemp, String typeTemp) {
	//public void setignoreRules() {
		Properties props = new Properties();
		
		try {			
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
		} catch (IOException e1) {			
			// TODO Auto-generated catch block
			e1.printStackTrace();
			pvalues.setSuccess(0);
			//dvalue.deletecomparessedfile();
			System.exit(1);
		}
		
		List<String> values = Arrays.asList(valueTemp.split(","));
		List<String> types = Arrays.asList(typeTemp.split(","));
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();		
		
		System.out.println();
		System.out.print("Ignored Values/Types: ");
		for(int i=0; i < values.size(); i++) {
			
			JSONObject dataObject = new JSONObject();
	        dataObject.put("username", lvalues.getUsername());
	        dataObject.put("key", lvalues.getApikey());
	        dataObject.put("scan_code", pvalues.getScanCode());
	        dataObject.put("type", types.get(i));
	        dataObject.put("value", values.get(i));
	        //dataObject.put("apply_to", props.getProperty("apply_to").toString());
	        dataObject.put("apply_to", "scan");
	        
	        JSONObject rootObject = new JSONObject();
	        rootObject.put("group", "scans");
	        rootObject.put("action", "ignore_rules_add");
			rootObject.put("data", dataObject);
			
			try {

				StringEntity entity = new StringEntity(rootObject.toString());
				httpPost.addHeader("content-type", "application/json");
				httpPost.setEntity(entity);
						
				HttpResponse httpClientResponse = httpClient.execute(httpPost);
					
				if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
					pvalues.setSuccess(0);
					System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
					System.exit(1);
				}	
						
				 if(i < values.size() - 1){
					 System.out.print(values.get(i) + "/" + types.get(i) + ", ");
				 } else if (i == values.size() - 1){
					 System.out.print(values.get(i) + "/" + types.get(i)); 
				 }				  
				
			} catch (Exception e) {				
				e.printStackTrace();
				pvalues.setSuccess(0);
				//dvalue.deletecomparessedfile();
				System.exit(1);
			}	
		}
		
		System.out.println();
	}

	private String types(int i) {
		// TODO Auto-generated method stub
		return null;
	}	

}
