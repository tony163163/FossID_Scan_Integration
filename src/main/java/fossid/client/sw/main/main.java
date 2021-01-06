package fossid.client.sw.main;

import java.util.ArrayList;
import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import fossid.client.sw.main.printInfo;
import fossid.client.sw.setdata.setLoginInfo;
import fossid.client.sw.setdata.setProjectInfo;
import fossid.client.sw.setdata.updateScanInfo;
import fossid.client.sw.uploading.compressFiles;
import fossid.client.sw.uploading.deleteCompressedFile;
import fossid.client.sw.uploading.osValidator;
import fossid.client.sw.uploading.uploadFiles;
import fossid.client.sw.values.projectValues;
import fossid.client.sw.scan.runScan;
import fossid.client.sw.scan.runDependencyScan;
import fossid.client.sw.scan.setIgnoreRules;
import fossid.client.sw.scan.downloadContentfromGit;;

public class main {
	
	static printInfo printInfo = new printInfo();
	static setLoginInfo loginInfo = new setLoginInfo();
	static setProjectInfo projectInfo = new setProjectInfo();	
	static updateScanInfo updateScaninfo = new updateScanInfo();	
	static runScan runscan = new runScan();	
	static runDependencyScan runDependencyScan = new runDependencyScan();
	static downloadContentfromGit downloadSourcefromGit = new downloadContentfromGit();
	static setIgnoreRules ignoreRules = new setIgnoreRules();
	static projectValues pvalues = projectValues.getInstance();
	static uploadFiles uploadfile = new uploadFiles();	
	static compressFiles compressFiles = new compressFiles();
	static deleteCompressedFile deletefile = new deleteCompressedFile();
	static osValidator validator = new osValidator();
	
	public static void main(String[] args) {				
		
		try {
			
			runwithArgu(args);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}	
				       
	private static void runwithArgu(String[] args){

		try {		

			if(args.length == 0 || args[0].equals("-h") || args[0].equals("--h") || args[0].equals("--help")) {
				printInfo.usage();
				System.exit(1);
			}
			
			ArrayList<String> param = new ArrayList<String>(Arrays.asList(args));
						
			if(args.length < 10 || !param.contains("--address") || !param.contains("--username") || !param.contains("--apikey") || !param.contains("--projectname") ||
					!param.contains("--scanname")){
				System.out.println();
				System.out.println();
				System.err.println("Please, check your parameters");
				System.out.println();
				System.out.println();
				printInfo.usage();
				System.exit(1);
			} 
			
			System.out.println("Start FossID Scan Integration");			
			printInfo.startFOSSID();	
			
			String date = new DateTime().toString(DateTimeFormat.forPattern("yyyyMMdd"));
			
			String protocol = "http";
			String address = "";
			String userName = "";
			String apikey = "";
			String projectName = "";
			String scanName = "";
			String prscId = date;
			String targetpath = "";
			String dependencyScanRun = "0";
			String gitRepoUrl = "";
			String gitBranch = "";			
			String ignoreValue = "";
			String ignoreType = "";
			String interval = "10";
			String filepath = "";
			String filename = "";
			String excludepath = "";
			String decompresstime = "30";
			
			String limit = "";
			String sensitivity = "";
			String replaceid = "";
			String reuseid = "";
			String idreusetype = "";
			String specificcode = "";
			String autoiddetectdeclare = "";
			String autoiddetectcopyright = "";
			String autoiddetectcomponent = "";
			String scanfailedonly = "";
			String deltaonly = "";
			String fullfileonly = "";
					
			
			for(int i = 0; i < args.length; i++) {
				if(args[i].equals("--protocol")) {
					protocol = args[i+1]; 
				}
				
				if(args[i].equals("--address")) {
					address = args[i+1];
				}
				
				if(args[i].equals("--username")) {
					userName = args[i+1]; 
				}
				
				if(args[i].equals("--apikey")) {
					apikey = args[i+1];
				}
				
				if(args[i].equals("--projectname")) {
					projectName = args[i+1]; 
				}
				
				if(args[i].equals("--scanname")) {
					scanName = args[i+1];
				}
				
				if(args[i].equals("--prscid")) {
					prscId = args[i+1]; 
				}
				
				if(args[i].equals("--targetpath")) {
					targetpath = args[i+1];
				}
				
				if(args[i].equals("--dependencyScanRun")) {
					dependencyScanRun = args[i+1];
				}
				
				if(args[i].equals("--gitrepourl")) {
					gitRepoUrl = args[i+1]; 
					pvalues.setGitUrl(gitRepoUrl);
				}
				
				if(args[i].equals("--gitbranch")) {
					gitBranch = args[i+1]; 
					pvalues.setGitBranch(gitBranch);
				}			
				
				if(args[i].equals("--ignorevalue")) {
					ignoreValue = args[i+1];
				}
				
				if(args[i].equals("--ignoretype")) {
					ignoreType = args[i+1]; 
				}
				
				if(args[i].equals("--interval")) {
					interval = args[i+1];
				}
				
				if(args[i].equals("--limit")) {
					limit = args[i+1];
				}
				
				if(args[i].equals("--sensitivity")) {
					sensitivity = args[i+1];
				}
				
				if(args[i].equals("--replaceid")) {
					replaceid = args[i+1];
				}
				
				if(args[i].equals("--reuseid")) {
					reuseid = args[i+1];
				}
				
				if(args[i].equals("--idreusetype")) {
					idreusetype = args[i+1];
				}
				
				if(args[i].equals("--specificcode")) {
					specificcode = args[i+1];
				}
				
				if(args[i].equals("--autoiddetectdeclare")) {
					autoiddetectdeclare = args[i+1];
				}
				
				if(args[i].equals("--autoiddetectcopyright")) {
					autoiddetectcopyright = args[i+1];
				}
				
				if(args[i].equals("--autoiddetectcomponent")) {
					autoiddetectcomponent = args[i+1];
				}
				
				if(args[i].equals("--scanfailedonly")) {
					scanfailedonly = args[i+1];
				}
				
				if(args[i].equals("--deltaonly")) {
					deltaonly = args[i+1];
				}
				
				if(args[i].equals("--fullfileonly")) {
					fullfileonly = args[i+1];
				}			
				
				if(args[i].equals("--filepath")) {
					filepath = args[i+1];
				}
				
				if(args[i].equals("--filename")) {
					filename = args[i+1];
				}
				
				if(args[i].equals("--excludepath")) {
					excludepath = args[i+1];
				}
				
				if(args[i].equals("--decompresstime")) {
					decompresstime = args[i+1];
				}
				
				i++;
			}
			
			loginInfo.setLogininfo(protocol, address, userName, apikey);		
			projectInfo.setInfo(projectName, scanName, prscId, gitRepoUrl, gitBranch);			
			printInfo.printinfo();
			
			if(!gitRepoUrl.equals("")) {
				downloadSourcefromGit.downloadfromGit(interval);
			}
			
			if(!filepath.equals("")){
				validator.setFileValues(filepath, filename);			
				deletefile.validationFile();
				compressFiles.compressfiles(excludepath);						
				//uploadfile.uploadfiles(decompressFile);			
				uploadfile.uploadingFile(decompresstime);
			}			
			
			updateScaninfo.updateScaninfo(targetpath);
			ignoreRules.setignoreRules(ignoreValue, ignoreType);			
		    runscan.runscan(interval, limit, sensitivity, replaceid, reuseid, idreusetype, specificcode, autoiddetectdeclare, 
		    		autoiddetectcopyright,  autoiddetectcomponent, scanfailedonly, deltaonly, fullfileonly);		    
		    if(dependencyScanRun.equals("1")){		    	
		    	runDependencyScan.runDependencyScan(interval);
		    }						
			
			System.out.println();
			
			if(!filepath.equals("")){
				deletefile.deletecomparessedfile();
			}
			
			if(pvalues.getSuccess() == 1) {
				System.out.println("All Scan process has been finished!!");
			} else if(pvalues.getSuccess() == 0) {
				System.out.println("This scan has been failed!!");
				System.out.println("Please, check 1) your configurations 2) FossID configurations 3) scan processes");
				System.exit(1);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}