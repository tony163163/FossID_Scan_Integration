package fossid.client.sw.main;

import fossid.client.sw.values.loginValues;
import fossid.client.sw.values.projectValues;

public class printInfo {
	
	static void usage() {		   
		System.out.println("Usage:");
		System.out.println("$ java -jar class [args....]");
		System.out.println();
		System.out.println("e.g:");
		System.out.println("$ java -jar fossidclientsw.jar --protocol http --address fossid.co.kr --username unsername --apikey a22d2s2s23 --projectname testProject "
				+ "--scanname testScan --prscid 0000 --filepath /path/to/scan --filename filename.zip --decompresstime 30 --interval 60 " +
				"--sourcepath /fossid/uploads/files/scans --ignorevalue licenses,lib --ignoretype directory,directory --excludepath /exclude/path1/*,/exclude/path2/*,*.txt");
		System.out.println();
		System.out.println();
		System.out.println("Arguments:");		
		System.out.println("--protocol: (Optional) FossID web interface protocol");
		System.out.println("            (default: http)");
		System.out.println("--address: FossID address");
		System.out.println("--username: username");
		System.out.println("--apikey: apikey");
		System.out.println("--projectname: Project Name");
		System.out.println("--scanname: Scan Name");
		System.out.println("--prscid: (Optional) Project / Scan ID");
		System.out.println("          (default: today date)");
		System.out.println("--filepath: full file path with file name to be analyzed. The souce code must be archived before starting this tool");
		System.out.println("--filename: compressed file name");
		System.out.println("--decompresstime: (Optional) Set the seconds to decompress the compressed file");
		System.out.println("                  (default: 30)");
		System.out.println("--interval: (Optional) Set the seconds for intervals displaying the scan log");
		System.out.println("            (default: 10)");
		System.out.println("--sourcepath: (Optional) target path to be scanned");		
		System.out.println("              (default: /fossid/uploads/files/scans)");
		System.out.println("--ignorevalue: (Optional) Set ignore values. This value is separated by commas and the order of this values is matched with the order of ignoretype value");
		System.out.println("--ignoretype: (Optional) Set ignore types. This value is separated by commas and the order of this values is matched with the order of ignorevalue value");
		System.out.println("--excludepath: (Optional) Set exclude path/file. This value is separated by commas");		
	}
	
	public static void startFOSSID() {		
		System.out.println();
		System.out.println("******                                 *****    ****");
		System.out.println("*                                        *      *   *");
		System.out.println("*                                        *      *    *");
		System.out.println("*                                        *      *     *");
		System.out.println("******    ****     *****     *****       *      *     *");
		System.out.println("*        *    *   *         *            *      *     *");
		System.out.println("*        *    *    *****     *****       *      *    *");
		System.out.println("*        *    *         *         *      *      *   *");		
		System.out.println("*         ****     *****     *****     *****    ****");
		System.out.println();
	}
	
	public static void printinfo() {
		loginValues lvalues = new loginValues();
		projectValues pvalues = new projectValues();
		
		System.out.println();
		System.out.println("Server URL: " + lvalues.getServerApiUri());
		System.out.println("UserName: " + lvalues.getUsername());
		System.out.println("ApiKey: " + "*******");
		System.out.println("Project Name/Code: " + pvalues.getProjectName() + " / " + pvalues.getProjectCode());
		System.out.println("Scan Name/Code: " + pvalues.getScanName() + " / " + pvalues.getScanCode());		
		System.out.println();
	}

}
