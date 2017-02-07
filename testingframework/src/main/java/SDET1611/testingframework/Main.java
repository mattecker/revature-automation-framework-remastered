package SDET1611.testingframework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the testing framework to be run headless using the commands:
 * mvn package
 * mvn exec:java -Dexec.args="arg0 arg1 ..."
 * Warning: using backslashes in the file path will throw FileNotFound exception unless you escape them first
 */

public class Main {
	// args are excelFilePath, keyword
	public static void main(String args[]) throws Exception {
		// 2 is the number of arguments necessary to have drivers
		System.out.println("ARGS IS " + args);
		System.out.println("ARGS[0] IS " + args[0]);
		if (args.length >= 2) {
			String path = args[0];
			File f = new File(path);
			List<String> drivers = new ArrayList<String>(); 
			System.out.println("ARGS.LENGTH IS ALWAYS ONE" + args.length);
			for(int i = 1; i < args.length; i++){
				drivers.add(args[i]);
			}
			System.out.println("drivers used are " + drivers);
			
			if (!verifyDrivers(drivers)){
				throw new Exception("Error: Driver not recognized");
			}
			else if(!f.exists()){
				throw new FileNotFoundException("Error: File not found");
			}
			else if(!path.substring(path.indexOf(".")).equals(".xlsx") && !path.substring(path.indexOf(".")).equals(".xls")){
				throw new FileNotFoundException("Error: Invalid file format");
			}
			else {
				String OS;
				if (System.getProperty("os.name").contains("Windows")) {
					OS = "WINDOWS";
				} else if (System.getProperty("os.name").contains("Mac")) {
					OS = "MAC";
				} else {
					OS = "LINUX";
				}

				PropObj.tryToCreateInstance(path, path, "Keywords", "Data", OS, "64", drivers.toArray(new String[drivers.size()]));
	
				for (int i = 0; i < drivers.size(); i++) {
					TestThread T = new TestThread(drivers.get(i));
					T.setName(drivers.get(i));
					T.start();
				}
			}
			System.gc();

		} else {
			throw new NullPointerException("Error: Invalid number of arguments");
		}
	}
	
	private static boolean verifyDrivers(List<String> drivers){
		for(String driver : drivers){
			System.out.println("validating " + driver);
			switch(driver.toLowerCase()){
			case "chrome":
			case "firefox":
			case "ie":
			case "internetexplorer":
			case "internet_explorer":
			case "internet explorer":
			case "edge":
			case "safari":
				break;
			default:
				return false;
			}
		}
		return true;
	}
	
}
