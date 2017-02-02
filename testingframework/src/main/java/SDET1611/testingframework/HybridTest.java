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
		//TODO Set up to test all available drivers
		Properties p = new Properties();
		p.load(new FileInputStream(new File(home+"/src/main/resources/tests"+threadCount+++".properties")));
		driverName = p.getProperty("driver");
		OSName = p.getProperty("OS");
		Bit = p.getProperty("bit");
		setPropertiesPath(p.getProperty("propertiesFilePath").replace("\\", ""));
		setKeywordPath(p.getProperty("keywordFilePath").replace("\\", ""));
		setDataPath(p.getProperty("dataFilePath").replace("\\", ""));
		
		keywordSheetName = p.getProperty("keywordSheetNames").split(",");
		dataSheetName = p.getProperty("dataSheetNames").split(",");
		
		System.out.println("DEBUG ---- key sheet name 1 --> "+keywordSheetName[0]);
		System.out.println("DEBUG ---- data sheet name 1 --> "+dataSheetName[0]);
		
		//driver = DriverHolder.getChromeDriver();
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
	
	@AfterSuite
	public void closeDown(){
		driver.close();
		System.gc();
	}
	
	
	/**
	 * Each parameter represents a column in the keyword File. The are required for a keyword driven test
	 * 
	 * @param testCaseName
	 * @param keyword
	 * @param objectName
	 * @param objectType
	 * @param value
	 * 
	 * 
	 * @throws AssertionError When a test has failed this error gets thrown.
	 */
	@Test(dataProvider="hybridData")
	public void allTests(String testCaseName, String keyword, String objectName, String objectType, String value) throws AssertionError {
		
		try{
			Assert.assertEquals(WebOp.action(objectProperties, keyword, objectName, objectType, value), true, "Success at "+testCaseName + " " +keyword);
		}catch(AssertionError e){
			Assert.fail("Failed at "+testCaseName + " " +keyword); //TODO make better failure message?
		}
		
	}
		
	/**
	 * 	 @return Data to be tested
	 *	 @throws IOException In case of unreadable files
	 * 
	 *   You may have to read this function more than once to understand the logic here. 
	 * 
	 *   In here is an Algorithm that uses 5 nested for loops so that we can iterate through combinations of two 2D 
	 * arrays (Keyword file and Data File), for each pair of data and keyword sheets. This data provider returns a 
	 * 2D array of Strings (ie. [x][y]. Where each object in the Object[][] is a String.), where each row (x) is a 
	 * test to be performed, and each step.
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
	 * This file hooks about to a data sheet formated as such:
	 * 
	 * |  UrlColumn  |  UserNameColumn  |  passwordColumn   | 
	 * |-------------|------------------|-------------------|
	 * |(someLongURL)|       yuvi1      |       yuvi1       | 
	 * |-------------|------------------|-------------------|
     * | (anotherURL)|      johnDoe     |       myPass      | 
	 * |-------------|------------------|-------------------|
	 *
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
	 *    Then function will perform a wacky algorithm the returns an object to the test so it will run each scenario 
	 * in the Keyword file, for every row in the Datafile. 
	 * 
	 * Larga vida al ciclo..
	 * 
	 */
	@DataProvider(name="hybridData")
	public Object[][] getDataFromProvider() throws IOException {
		
		//List of Object[] that will contain testing values for the action in the @test
		ArrayList<Object[]> ArrayObject = new ArrayList<Object[]>();
		
		boolean isDataValue = false;
		
		//Temp variable used to assign values into the ArrayObject 
		String cellData = null;
		
		//Temp variable for storing the scenario name for each step in a scenario
		String scenarioName = "";
		
			
		for(int s = 0; s < keywordSheetName.length; s++){
			//Read keyword sheet
			keywordSheet = ReadExcelFile.readExcel(KeywordFilePath, KeywordFileName, keywordSheetName[s]);
			//Read data sheet
	        dataSheet = ReadExcelFile.readExcel(DataFilePath, DataFileName, dataSheetName[s]);
	        kdRowCount = keywordSheet.getLastRowNum() - keywordSheet.getFirstRowNum();
			ddRowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();
			

			
			
			// adjusts kdRowCount to ensure tests aren't run on empty rows
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
				// Get current data row
				Row ddRow = dataSheet.getRow(r);
				
				/*
				 * For each row in the keyword sheet we want to write tests for our given data row.
				 */
				for(int i=0;i<kdRowCount;i++) {
					
					// Array has a length of 5 because the keyword sheet is required to have 5 columns
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
					//System.out.println();
					if((kdRow.getCell(0)!=null)&&(!kdRow.getCell(0).toString().equals(""))){
						scenarioName=kdRow.getCell(0).toString();
						continue;
					}else{
						rowObject[0] = scenarioName;
					}
					
		            	
		            /*
		             * For each column in a row of keyword sheet, skip first column because it is set as test name
		             */
					for(int j=1; j<kdRow.getLastCellNum(); j++) {
						
		                //If there is a value in Value column get it from data sheet
		                if(j == 4 && kdRow.getCell(j) != null) {
		                    
		                    //Store value word from keyword driven sheet
		                    String value = kdRow.getCell(j).toString();
	
	                        /*
	                         * For each column in a row of the data row. We will search for the column in the data sheet that 
	                         * matches the value from the keyword sheet.
	                         */
	                        for(int l=0;l<ddRow.getLastCellNum();l++) {
	                   
	                        	//if value is equal to column name in data sheet, ie getRow(0)
	                            if(value.equals(dataSheet.getRow(0).getCell(l).toString())) {                       
	                            	/*
	                            	 * Now that we have the correct column in the data row we are currently working on, we
	                            	 * know which cell contains the data associated with the current test we are trying to
	                            	 * write. We assign that data to cellData, which is eventually be put into the ArrayObject.
	                            	 */
	                                cellData = ddRow.getCell(l).toString();
	                                isDataValue = true;
	                            }        
	                        } 
		                }
						
		                /*
		                 * If the column of the row we are looking at is null, set it to an Empty string,
		                 * because null pointers *sigh*.
		                 * 
		                 * If the column is not null, and is not the value column (determined by the isDataValue boolean)
		                 */
						if (kdRow.getCell(j) == null) {
							cellData = "";
						}  else if(isDataValue == false){
		                    cellData = kdRow.getCell(j).toString();
		                }
						
						//Reset the boolean for the next round for looping
		                isDataValue = false;
						
		                // Assign proper value into our rowObject.
						rowObject[j] = cellData;
						
					} //<-- End of for each column in a row of keyword sheet
					
	                /*
	                 * Now the the rowObject has been completely filled with the exact test to be run, add that row
	                 * to the master ArrayObject. 
	                 */
					ArrayObject.add(rowObject);
					
				} //<-- End of for each row in the keyword sheet
				
			} //<-- End of for each row in the Data sheet.
		} //<--End of for each sheet pair.
		/*
		 * Data provider can only return a 2D array, so we must convert the ArrayObject
		 */
		Object[][] newObject = new Object[ArrayObject.size()][5];
		for(int i=0;i<ArrayObject.size();i++) {
			for(int j=0; j<5; j++) {
				newObject[i][j] = ArrayObject.get(i)[j];
			}
		}
		
		// WE DONE WITH THAT MINDF***.
		return newObject;
	}
	
	/**
	 * Regular old getter method.
	 * @return 
	 */
	public String getPropertiesPath(){
		return PropertiesFilePath;
	}
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getKeywordPath(){
		return KeywordFilePath;
	}
	
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getDataPath(){
		return DataFilePath;
	}
	
	/**
	 * Regular old getter method.
	 * @return 
	 */
	public String getPropertiesName(){
		return PropertiesFileName;
	}
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getKeywordName(){
		return KeywordFileName;
	}
	
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getDataName(){
		return DataFileName;
	}
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getKeywordSheetName(int index){
		return keywordSheetName[index];
	}
	
	/**
	 * Regular old getter method.
	 * @return
	 */
	public String getDataSheetName(int index){
		return dataSheetName[index];
	}
	
	/**
	 * Based on System properties, find the delimiter for the file system, and return it.
	 * @return A file Delimiter i.e. '/'
	 */
	private static String getFileDelimiter(){
		//return System.getProperty("file.separator").trim();
		return "/";
	}
	
	/**
	 * Regular old setter method.
	 * @param path User Provided path.
	 */
	public void setPropertiesPath(String path){
		PropertiesFilePath = path;
		PropertiesFileName =  getFileName(PropertiesFilePath);
	}
	/**
	 * Regular old setter method.
	 * @param path  User Provided path.
	 */
	public void setKeywordPath(String path){
		KeywordFilePath =  path;
		KeywordFileName =  getFileName(KeywordFilePath);
	}
	
	/**
	 * Regular old setter method.
	 * @param path  User Provided path.
	 */
	public void setDataPath(String path){
		DataFilePath =  path;
		DataFileName =  getFileName(DataFilePath);
	}
	/**
	 *  Method for setting Test Data.
	 */
	public void setPropertiesPath(){
		PropertiesFilePath = home.getAbsolutePath()+"/src/test/resources/TestData/test.properties";
		PropertiesFileName =  getFileName(PropertiesFilePath);
	}
	/**
	 *  Method for setting Test Data.
	 */
	public void setKeywordPath(){
		KeywordFilePath =  home.getAbsolutePath()+"/src/test/resources/TestData/testKeyword.xlsx";
		KeywordFileName =  getFileName(KeywordFilePath);
	}
	
	/**
	 *  Method for setting Test Data.
	 */
	public void setDataPath(){
		DataFilePath =  home.getAbsolutePath()+"/src/test/resources/TestData/testData.xlsx";
		DataFileName =  getFileName(DataFilePath);
	}
	
	
	public String getDriverName() {
		return driverName;
	}
	
	/**
	 * @param path Path which will be separated to find the filename.
	 */
	public void setDriverName(String name){
		driverName =  name;
	}
	
	public String getOSName() {
		return OSName;
	}
	
	/**
	 * @param path Path which will be separated to find the filename.
	 */
	public void setOStName(String name){
		OSName =  name;
	}
	
	/**
	 * @param path Path which will be separated to find the filename.
	 * @return Name of the file.
	 */
	private static String getFileName(String path){
		String[] split = path.split(getFileDelimiter());
		return split[split.length-1];
	}
	
}