package SDET1611.testingframework;

import java.io.File;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

public class TestFunctions {

	File home = new File(System.getProperty("user.dir"));
	/**
	 * This is just a demo that utilizes the WebOperation object
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TestFunctions tf = new TestFunctions();
		//tf.HardCodedLoginTest();
		tf.KeywordDrivenLoginTest();
	}
	public void HardCodedLoginTest() {
		//Get chrome driver
		WebDriver driver = DriverHolder.getDriver("WINDOWS","CHROME","64");
				
		//Create properties object
		Properties p = new Properties();
		
		//Add properties
		p.put("username", "userName");
		p.put("password", "password");
		p.put("url", "http://newtours.demoaut.com/");
		
		//Create WebOperation object
		WebOperation wo = new WebOperation(driver);
	
		//Perform web operation actions
		wo.action(p, "go_to_url", "", "", "url");
		wo.action(p, "input_text", "name", "username", "yuvi1");
		wo.action(p, "input_text", "name", "password", "yuvi1");
	}
	public void KeywordDrivenLoginTest() throws Exception {
		
		String propertiesFilePath = home.getAbsolutePath()+"/src/test/resources/TestData/MercuryData.properties";
		String excelFilePath = home.getAbsolutePath()+"/src/test/resources/TestData/MercuryData.xlsx";
		
		//Get chrome driver
		WebDriver driver = DriverHolder.getDriver("WINDOWS","CHROME","64");
		
		System.out.println("DEBUG----LOADING PROPERTIES----DEBUG");
		Properties objectProperties = ReadObjectFile.getObjectData(propertiesFilePath);
		WebOperation webOperation = new WebOperation(driver);
		
		//Read keyword excel sheet
		Sheet sheet = ReadExcelFile.readExcel(excelFilePath, "MercuryKeyword.xlsx", "TestSheet");
		//Get row count in excel sheet
		System.out.println("DEBUG----GETTING ROW COUNT----DEBUG");
		System.out.println("DEBUG----" + sheet + "----DEBUG");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println("row count: " + rowCount);
		//Loop through rows to read data
		//Start at second row (i=1)
		System.out.println("DEBUG----STARTING LOOP----DEBUG");
		for(int i=1; i< rowCount+1; i++) {
			//
			Row row = sheet.getRow(i);
			
			//If first cell contains data -> Then it is a test case
			if(row.getCell(0) == null) {
				System.out.println("DEBUG--- row count: " + row.getRowNum() + "----DEBUG");
				//Print test case information
				System.out.println(row.getCell(1) + " " +
								   row.getCell(2) + " " +
								   row.getCell(3) + " " +
		                           row.getCell(4) + ": 4th cell ");
				
				String objectName;
				String objectType;
				String objectValue;
				
				if (row.getCell(2) == null) {
					objectName = "";
				} else {
					objectName = row.getCell(2).toString();
				}
				if (row.getCell(3) == null) {
					objectType = "";
				} else {
					objectType = row.getCell(3).toString();
				}
				if (row.getCell(4) == null) {
					objectValue = "";
				} else {
					objectValue = row.getCell(4).toString();
				}
				
				//Call action to perform an event
				webOperation.action(objectProperties,
									row.getCell(1).toString(), 
									objectName, 
									objectType, 
									objectValue);
				
			}
			else {
					//Print the new testcase name when it started
	                System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
					
			}
		}
	}
}
