Prerequisite
------------------------------------------------

### FossID
- Create FossID Username
- Create Api Key of Username
- Edit the /fossid/etc/fossid.conf file
```
;Enable API usage of target paths
webapp_scan_path_enable=1
```

### Set Scan options
- Set config.properties file

### Java JDK
- This tool is tested with Open JDK 1.8


Usage
------------------------------------------------

### To run this tool
```bash
$ java -jar class [args....]

e.g)
$ java -jar fossidclientsw.jar --protocol http --address fossid.co.kr --username unsername --apikey a22d2s2s23 --projectname testProject --scanname testScan --prscid 0000 --targetpath /path/to/scan --dependencyScanRun 0 --gitrepourl https://github.com/twbs/bootstrap.git --gitbranch master --sourcepath /fossid/uploads/files/scans --ignorevalue licenses,lib --ignoretype directory,directory --interval 30


--protocol: (Optional) FossID web interface protocol
            (default: http)
--address: FossID address
--username: username
--apikey: apikey
--projectname: Project Name
--scanname: Scan Name
--prscid: (Optional) Project / Scan ID
          (default: today date) 
--targetpath: Full path including source code to be analyzed in FossID server
--dependencyScanRun: (Optional) Set 0 or 1 to trigger dependency scan after source code scan
			         (default: 0)
--gitrepourl: (Optional) Set git repo url 
--gitbranch: (Optional) Set git repo branch	         
--sourcepath: (Optional) target path of FossID server to be scanned
  + Need to change 'webapp_scan_path_enable=1' to use this option in /fossid/etc/fossid.conf file
  + This option is also related with 'webapp_scan_path_prefixes' option of /fossid/etc/fossid.conf file. If you change/apply 'webapp_scan_path_prefixes', you need to apply the path of 'uploadSourcePath' under 'webapp_scan_path_prefixes'
  (default: /fossid/uploads/files/scans)
--ignorevalue: (Optional) Set ignore values. This value is separated by commas and the order of this values is matched with the order of ignoretypes value
--ignoretype: (Optional) Set ignore types. This value is separated by commas and the order of this values is matched with the order of ignorevalue value
--interval: (Optional) Set interval time to check status running source code and dependency scan
			(default: 10 seconds)
```