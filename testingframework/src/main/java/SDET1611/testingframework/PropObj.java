package SDET1611.testingframework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Single instance of this class exists for any one instance of the GUI It
 * contains the information necessary to complete the test.
 */

public class PropObj implements Serializable {
	private static String dataFilePath;
	private static String keywordFilePath;
	private static String propertiesFilePath;
	private static String keywordSheetNames;
	private static String dataSheetNames;
	private static String OS;
	private static String bit;
	private static String[] drivers;

	private static PropObj instance = null;
	
	private static List<String> driverExists = new ArrayList<>();
	
	public static boolean getDriverExistence(){
		if(driverExists.isEmpty()){
			deleteInstance();
			return false;
		}
		return true;
	}
	
	public static void setDriverExists(String driverName){
		driverExists.add(driverName);
	}
	public static void removeDriverExists(String driverName){
		for(String driver:driverExists){
			if(driver.equals(driverName)){
				driverExists.remove(driver);
				break;
			}
		}
	}

	private PropObj(String dataFilePath, String keywordFilePath, String keywordSheetNames,
			String dataSheetNames, String OS, String bit, String[] drivers) {
		this.dataFilePath = dataFilePath;
		this.keywordFilePath = keywordFilePath;
		//this.propertiesFilePath = propFilePath;
		this.keywordSheetNames = keywordSheetNames;
		this.dataSheetNames = dataSheetNames;
		this.OS = OS;
		this.bit = bit;
		this.drivers = drivers;
	}

	public static PropObj tryToCreateInstance(String dataFilePath, String keywordFilePath,
			String keywordSheetNames, String dataSheetNames, String OS, String bit, String[] drivers) {
		if (instance == null) {
			instance = new PropObj(dataFilePath, keywordFilePath, keywordSheetNames, dataSheetNames, OS,
					bit, drivers);
		}
		return instance;
	}

	public static PropObj getInstance() {
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	public String[] getInfo() {
		String[] info = { dataFilePath, keywordFilePath, propertiesFilePath, keywordSheetNames, dataSheetNames, OS,
				bit };
		return info;
	}

	public String[] getDrivers() {
		return drivers;
	}

}
