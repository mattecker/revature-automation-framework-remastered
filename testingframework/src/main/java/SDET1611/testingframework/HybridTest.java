package SDET1611.testingframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HybridTest {
	static int threadCount = 0; //tests.properties count
	WebDriver driver;
	private String OSName;
	private String driverName;
	private String Bit;
	static File home = new File(System.getProperty("user.dir"));
	private String PropertiesFilePath;
	private String PropertiesFileName;
	private Properties objectProperties;
	private String DataFilePath;
	private String DataFileName;
	private String KeywordFilePath;
	private String KeywordFileName;
	private Sheet keywordSheet;
	private Sheet dataSheet;
	private String[] keywordSheetName;
	private String[] dataSheetName;
	private int kdRowCount;
	private int ddRowCount;

	WebOperation WebOp;
	
	public HybridTest() throws IOException{
		driverName = PropObj.getDriver();
		Thread.currentThread().setName(driverName);
		OSName = PropObj.OS;
		Bit = PropObj.bit;
		setPropertiesPath(PropObj.propertiesFilePath.replace("\\", ""));
		setKeywordPath(PropObj.keywordFilePath.replace("\\", ""));
		setDataPath(PropObj.dataFilePath.replace("\\", ""));
		
		keywordSheetName = PropObj.keywordSheetNames.split(",");
		dataSheetName = PropObj.dataSheetNames.split(",");
	}

	/**
	 * @throws IOException If the data provided is not found.
	 */
	@BeforeSuite
	public void setUp() throws IOException{
		driver = DriverHolder.getDriver(OSName, driverName, Bit);
		WebOp = new WebOperation(driver);
		
		objectProperties = ReadObjectFile.getObjectData(PropertiesFilePath);
        // TODO: find way to dynamically change the sheet name
		//Get row counts
	}
	
	/**
	 * Closes driver and requests garbage collection after tests.
	 */
	@AfterSuite
	public void closeDown(){
		driver.close();
		System.gc();
	}
	
	/**
	 * Each parameter represents a column in the keyword File. They are required for a keyword driven test.
	 * @throws AssertionError When a test has failed this error gets thrown.
	 */
	@Test(dataProvider="hybridData")
	public void allTests(String testCaseName, String keyword, String objectName, String objectType, String value) throws AssertionError {	
		try{
			Assert.assertEquals(WebOp.action(objectProperties, keyword, objectName, objectType, value), true, "Success at "+testCaseName + " " +keyword);
			System.out.println("Success at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value);
		}catch(AssertionError e){
			System.out.println("Failed at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value);
			Assert.fail("Failed at "+testCaseName + " " +keyword); //TODO make better failure message?
		}
	}
		
	/**
	 * 	 @return Data to be tested
	 *	 @throws IOException In case of unreadable files
	 * 
	 *   You may have to read this function more than once to understand the logic here. 
	 * 
	 *   This method uses 5 nested for loops to iterate through combinations of two 2D arrays, a keyword file and 
	 *   a data File, for each pair of data and keyword sheets. This data provider returns a 2D array of Strings, 
	 *   where each row is a test to be performed, and each step.
	 * 
	 *   EX: 
	 *   
	 * |ScenarioName|  Keyword  |  Object   | Object Type |      Value       |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |  Login     |           |           |             |                  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | Go To URL |           |             |     UrlColumn    |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | InputText |  username |    name     |  UserNameColumn  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | InputText |  password |    name     |  passwordColumn  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            |   Click   |   login   |    name     |                  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * 
	 * This file hooks to a data sheet formatted as such:
	 * 
	 * |  UrlColumn  |  UserNameColumn  |  passwordColumn   | 
	 * |-------------|------------------|-------------------|
	 * |(someLongURL)|       yuvi1      |       yuvi1       | 
	 * |-------------|------------------|-------------------|
     * | (anotherURL)|      johnDoe     |       myPass      | 
	 * |-------------|------------------|-------------------|
	 *
	 *    Also required is a properties file, which relates the Object column of the keyword file to the elements
	 *  on the webpage. It acts similar to an Object repository.
	 *
	 *   ------------------------
	 *   | # comments			|
	 *   |						|
	 *   | username=userName	|
	 *   | password=userPass	|
	 *   |						|
	 *   |______________________|
	 * 
	 */
	@DataProvider(name="hybridData")
	public Object[][] getDataFromProvider() throws IOException {
		//List of Object[] that will contain testing values for the action in the @test
		ArrayList<Object[]> testingValues = new ArrayList<Object[]>();
		
		boolean isDataValue = false;
		
		//Temp variable used to assign values into the ArrayObject 
		String cellData = null;
		
		//Temp variable for storing the scenario name for each step in a scenario
		String scenarioName = "";
		
		for(int s = 0; s < keywordSheetName.length; s++){
			keywordSheet = ReadExcelFile.readExcel(KeywordFilePath, KeywordFileName, keywordSheetName[s]);
	        dataSheet = ReadExcelFile.readExcel(DataFilePath, DataFileName, dataSheetName[s]);
	        kdRowCount = keywordSheet.getLastRowNum() - keywordSheet.getFirstRowNum();
			ddRowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();
			
			// Adjusts kdRowCount to ensure tests aren't run on empty rows
			boolean readyToTest = false;
			while(!readyToTest){
				if((keywordSheet.getRow(kdRowCount).getCell(0)==null) && (keywordSheet.getRow(kdRowCount).getCell(2)==null)){
					keywordSheet.removeRow(keywordSheet.getRow(kdRowCount));
					kdRowCount--;
				}
				else if(kdRowCount==0)
					break;
				else
					readyToTest=true;
			}
			
			/*
			 * For each row in the Data sheet.
			 * We want to set up test for all scenarios in the keyword sheet for a single row before going to
			 * the next row in the data sheet in order to prevent data mismatches. We start out at 1  becasue row 0
			 * is the column names.
			 */
			for(int r = 1; r <= ddRowCount; r++){
				Row ddRow = dataSheet.getRow(r);
				
				//For each row in the keyword sheet we want to write tests for our given data row.
				for(int i = 0; i < kdRowCount; i++){
					Object[] rowObject = new Object[5];
					
					// First row contains description words therefore start at second row.
		            Row kdRow = keywordSheet.getRow(i+1);
	       
					/*
					 * Skip scenario name rows, and sets first cell for steps to the scenario name EX:
					 *  __________________
					 * |  Name  | keyword | 
					 * |--------|---------|
					 * |  Login |         | <-- Skip this row
					 * ---------|---------|
					 * |        |inputText| <-- Set test name in this row
					 * --------------------
					 */
					if((kdRow.getCell(0) != null) && (!kdRow.getCell(0).toString().equals(""))){
						scenarioName = kdRow.getCell(0).toString();
						continue;
					} else {
						rowObject[0] = scenarioName;
					}
					
		            //For each column in a row of keyword sheet, skip first column because it is set as test name
					for(int j = 1 ; j < kdRow.getLastCellNum() ; j++){
		                //If there is a value in Value column get it from data sheet
		                if(j == 4 && kdRow.getCell(j) != null) {
		                    String value = kdRow.getCell(j).toString();
	
	                        /*
	                         * For each column in a row of the data row, we search for the column in the data sheet that 
	                         * matches the value from the keyword sheet.
	                         */
	                        for(int l = 0 ; l < ddRow.getLastCellNum() ; l++){
	                            if(value.equals(dataSheet.getRow(0).getCell(l).toString())){
	                                cellData = ddRow.getCell(l).toString();
	                                isDataValue = true;
	                            }        
	                        } 
		                }
						
		                /*
		                 * If the column of the row we are looking at is null, set it to an Empty string,
		                 * because null pointers.
		                 * 
		                 * If the column is not null, and is not the value column (determined by the isDataValue boolean)
		                 */
						if (kdRow.getCell(j) == null){
							cellData = "";
						} else if(isDataValue == false){
		                    cellData = kdRow.getCell(j).toString();
		                }
						
		                isDataValue = false;
						rowObject[j] = cellData;
					}
					
	                /*
	                 * Now that rowObject has been filled with the test to be run, add that row
	                 * to the master ArrayObject. 
	                 */
					testingValues.add(rowObject);
				}
			}
		}
		
		/*
		 * Convert testingValues to 2D array so it can be returned.
		 */
		Object[][] newObject = new Object[testingValues.size()][5];
		for(int i = 0; i < testingValues.size(); i++) {
			for(int j = 0; j < 5; j++){
				newObject[i][j] = testingValues.get(i)[j];
			}
		}
		return newObject;
	}
	
	public String getPropertiesPath(){
		return PropertiesFilePath;
	}
	
	public String getKeywordPath(){
		return KeywordFilePath;
	}
	
	public String getDataPath(){
		return DataFilePath;
	}
	
	public String getPropertiesName(){
		return PropertiesFileName;
	}
	
	public String getKeywordName(){
		return KeywordFileName;
	}
	
	public String getDataName(){
		return DataFileName;
	}
	
	public String getKeywordSheetName(int index){
		return keywordSheetName[index];
	}
	
	public String getDataSheetName(int index){
		return dataSheetName[index];
	}
	
	private static String getFileDelimiter(){
		//return System.getProperty("file.separator").trim();
		return "/";
	}
	
	public void setPropertiesPath(String path){
		PropertiesFilePath = path;
		PropertiesFileName =  getFileName(PropertiesFilePath);
	}

	public void setKeywordPath(String path){
		KeywordFilePath =  path;
		KeywordFileName =  getFileName(KeywordFilePath);
	}
	
	public void setDataPath(String path){
		DataFilePath =  path;
		DataFileName =  getFileName(DataFilePath);
	}
	
	public void setPropertiesPath(){
//		PropertiesFilePath = home.getAbsolutePath()+"/src/test/resources/TestData/test.properties";
		PropertiesFileName =  getFileName(PropertiesFilePath);
	}
	
	public void setKeywordPath(){
//		KeywordFilePath =  home.getAbsolutePath()+"/src/test/resources/TestData/testKeyword.xlsx";
		KeywordFileName =  getFileName(KeywordFilePath);
	}
	
	public void setDataPath(){
//		DataFilePath =  home.getAbsolutePath()+"/src/test/resources/TestData/testData.xlsx";
		DataFileName =  getFileName(DataFilePath);
	}
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String name){
		driverName =  name;
	}
	
	public String getOSName() {
		return OSName;
	}
	
	public void setOStName(String name){
		OSName =  name;
	}
	
	private static String getFileName(String path){
		String[] split = path.split(getFileDelimiter());
		return split[split.length-1];
	}
}