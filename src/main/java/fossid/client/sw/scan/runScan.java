package fossid.client.sw.scan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fossid.client.sw.values.loginValues;
import fossid.client.sw.values.projectValues;

public class runScan {
	
	loginValues lvalues = loginValues.getInstance();
	projectValues pvalues = projectValues.getInstance(); 

	public void runscan(String interval) {
		
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
		

		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getScanCode());
        
        if(!props.getProperty("limit").equals("")){        
        	dataObject.put("limit", props.getProperty("limit"));
        	pvalues.setScanOption("limit: " + props.getProperty("limit"));
        }
        
        if(!props.getProperty("sensitivity").equals("")){        
        	dataObject.put("sensitivity", props.getProperty("sensitivity"));
        	pvalues.setScanOption("sensitivity: " + props.getProperty("sensitivity"));
        }
        
        if(!props.getProperty("replace_existing_identifications").equals("")){
        	dataObject.put("replace_existing_identifications", props.getProperty("replace_existing_identifications"));
        	pvalues.setScanOption("replace_existing_identifications: " + props.getProperty("replace_existing_identifications"));
        }
        
        if(!props.getProperty("reuse_identification").equals("")){
        	dataObject.put("reuse_identification", props.getProperty("reuse_identification"));
        	pvalues.setScanOption("reuse_identification: " + props.getProperty("reuse_identification"));
        }
        
        if(!props.getProperty("identification_reuse_type").equals("")){
        	dataObject.put("identification_reuse_type", props.getProperty("identification_reuse_type"));
        	pvalues.setScanOption("identification_reuse_type: " + props.getProperty("identification_reuse_type"));
        }
        
        if(!props.getProperty("specific_code").equals("")){
        	dataObject.put("specific_code", props.getProperty("specific_code"));
        	pvalues.setScanOption("specific_code: " + props.getProperty("specific_code"));
        }
        
        if(!props.getProperty("auto_identification_detect_declaration").equals("")){
        	dataObject.put("auto_identification_detect_declaration", props.getProperty("auto_identification_detect_declaration"));
        	pvalues.setScanOption("auto_identification_detect_declaration: " + props.getProperty("auto_identification_detect_declaration"));
        }
        
        if(!props.getProperty("auto_identification_detect_copyright").equals("")){
        	dataObject.put("auto_identification_detect_copyright", props.getProperty("auto_identification_detect_copyright"));
        	pvalues.setScanOption("auto_identification_detect_copyright: " + props.getProperty("auto_identification_detect_copyright"));
        }
        
        if(!props.getProperty("auto_identification_detect_component").equals("")){
        	dataObject.put("auto_identification_detect_component", props.getProperty("auto_identification_detect_component"));
        	pvalues.setScanOption("auto_identification_detect_component: " + props.getProperty("auto_identification_detect_component"));
        }
        
        if(!props.getProperty("scan_failed_only").equals("")){
        	dataObject.put("scan_failed_only", props.getProperty("scan_failed_only"));
        	pvalues.setScanOption("scan_failed_only: " + props.getProperty("scan_failed_only"));
        }
        
        if(!props.getProperty("delta_only").equals("")){
        	dataObject.put("delta_only", props.getProperty("delta_only"));
        	pvalues.setScanOption("delta_only: " + props.getProperty("delta_only"));
        }
        
        if(!props.getProperty("full_file_only").equals("")){
        	dataObject.put("full_file_only", props.getProperty("full_file_only"));
        	pvalues.setScanOption("full_file_only: " + props.getProperty("full_file_only"));
        }
                        
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "run");
		rootObject.put("data", dataObject);
		
		if(pvalues.getScanOption().size() > 0) {
			System.out.println();
			System.out.println("Set below scan options:");				
			for(int i=0; pvalues.getScanOption().size() > i; i++) {				
				System.out.println("- " + pvalues.getScanOption().get(i));	
			}
		}		
				
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
						
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
			
			System.out.println();
			System.out.println("Scan has been launched!!");
			System.out.println();			
					
			checkScanStatus(interval);
			
			System.out.println();	
			System.out.println("Scan has been finished!!");
			System.out.println();			
			
		} catch (Exception e) {				
			e.printStackTrace();
			pvalues.setSuccess(0);
			System.exit(1);
		}
	}
	
	private void checkScanStatus(String interval) {

		// to map scan to project
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getScanCode());
        dataObject.put("type", "SCAN");
        
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "check_status");
		rootObject.put("data", dataObject);		
						
		String finished = "0";
		//1000 = 1 second
		int intervals = Integer.parseInt(interval) * 1000;

	   
		
		try {
			int i = 1;
			//int loopCount = 1;
			
			while(finished.equals("0")) {
				HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
				CloseableHttpClient httpClient = HttpClientBuilder.create().build();		
					
				HttpResponse httpClientResponse = null;
			
				//int postCount = 1;			
					
				StringEntity entity = new StringEntity(rootObject.toString());
				httpPost.addHeader("content-type", "application/json");
				httpPost.setEntity(entity);
				httpClientResponse = httpClient.execute(httpPost);					
						
				if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
					pvalues.setSuccess(0);
					System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
					System.exit(1);					
				}
					
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
				String result = br.readLine();
					
				JSONParser jsonParser = new JSONParser();
			    JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());            
			    JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
			       		        
			    if(jsonObj2.get("finished") != null) {
			        finished = jsonObj2.get("finished").toString();		        	
			    }		       
			        
			    System.out.println(i + ". "  + jsonObj2.get("comment") + " / " + jsonObj2.get("percentage_done"));	        
			    i++;
			        
			    Thread.sleep(intervals);	
			    
			    httpClient.close();
		    }
			
		} catch (Exception e) {			
			e.printStackTrace();
			pvalues.setSuccess(0);
			System.exit(1);
		}
	}
}
