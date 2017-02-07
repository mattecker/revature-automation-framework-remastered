package SDET1611.testingframework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HybridTest {
	static int threadCount = 0; // tests.properties count
	WebDriver driver;
	private String OSName;
	private String driverName;
	private String Bit;
	static File home = new File(System.getProperty("user.dir"));
	private String DataFilePath;
	private String KeywordFilePath;
	private Sheet keywordSheet;
	private Sheet dataSheet;
	private String keywordSheetName;
	private String dataSheetName;
	private int kdRowCount;
	private int ddRowCount;

	WebOperation WebOp;

	public HybridTest() throws IOException {
		PropObj testProp = PropObj.getInstance();
		String[] testInfo = testProp.getInfo();

		DataFilePath = testInfo[0].replace("\\", "");
		System.out.println(DataFilePath);
		KeywordFilePath = testInfo[1].replace("\\", "");
		System.out.println(KeywordFilePath);

		keywordSheetName = testInfo[3];
		System.out.println(keywordSheetName);
		dataSheetName = testInfo[4];
		System.out.println(dataSheetName);

		OSName = testInfo[5];
		System.out.println(OSName);
		Bit = testInfo[6];
		System.out.println(Bit);

		System.out.println(Thread.currentThread().getName().toUpperCase());
		driverName = Thread.currentThread().getName().toUpperCase();
	}

	/**
	 * @throws IOException
	 *             If the data provided is not found.
	 */
	@BeforeSuite
	public void setUp() throws IOException {
		driver = DriverHolder.getDriver(OSName, driverName, Bit);
		WebOp = new WebOperation(driver);
	}

	/**
	 * Closes driver and requests garbage collection after tests.
	 */
	@AfterSuite
	public void closeDown() {
		driver.quit();
		PropObj.removeDriverExists(Thread.currentThread().getName());
		System.gc();
	}

	/**
	 * Each parameter represents a column in the keyword File. They are required
	 * for a keyword driven test.
	 * 
	 * @throws AssertionError
	 *             When a test has failed this error gets thrown.
	 */
	@Test(dataProvider = "hybridData")
	public void allTests(String testCaseName, String keyword, String objectName, String objectType, String value)
			throws AssertionError {
		try {
			Assert.assertEquals(WebOp.action(keyword, objectName, objectType, value), true,
					"\n\nFailed at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value + "\n");
			System.out.println(
					"Success at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value);
		} catch (AssertionError e) {
			System.out.println(
					"\n\nFailed at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value + "\n");
			Assert.fail("Failed at " + testCaseName + " " + keyword); // TODO
																		// make
																		// better
																		// failure
																		// message?
		} catch (InvalidObjectSelectorException e) {
			System.out.println("\n\n");
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		} catch (NoSuchElementException e){
			System.out.println("\n\n");
			System.out.println("Failed at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value + ". " + e.getMessage() + "\n");
			Assert.fail("Failed at: " + testCaseName + " " + keyword + " " + objectName + " " + objectType + " " + value);
		}
	}

	/**
	 * @return Data to be tested
	 * @throws IOException In case of unreadable files
	 * 
	 * You may have to read this function more than once to understand the logic here.
	 * 
	 * This method uses 5 nested for loops to iterate through
	 * combinations of two 2D arrays, a keyword file and a data
	 * File, for each pair of data and keyword sheets. This data
	 * provider returns a 2D array of Strings, where each row is a
	 * test to be performed, and each step.
	 * 
	 * EX:
	 * 
	 * |ScenarioName|  Keyword  |  Object   | Object Type |     Value        |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |   Login    |           |           |             |                  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | Go To URL |           |             |    UrlColumn     |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | InputText | username  |    name     |  UserNameColumn  |
	 * |------------|-----------|-----------|-------------|------------------|
	 * |            | InputText | password  |    name     |  passwordColumn  |
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
	 * |(anotherURL) |      johnDoe     |      myPass       |
	 * |-------------|------------------|-------------------|
	 * 
	 */
	@DataProvider(name = "hybridData")
	public Object[][] getDataFromProvider() throws IOException {
		// List of Object[] that will contain testing values for the action in the @test
		ArrayList<Object[]> testingValues = new ArrayList<Object[]>();

		boolean isDataValue = false;

		// Temp variable used to assign values into the ArrayObject
		String cellData = null;

		// Temp variable for storing the scenario name for each step in a scenario
		String scenarioName = "";

		keywordSheet = ReadExcelFile.readExcel(KeywordFilePath, keywordSheetName);
		dataSheet = ReadExcelFile.readExcel(DataFilePath, dataSheetName);
		kdRowCount = keywordSheet.getLastRowNum() - keywordSheet.getFirstRowNum();
		ddRowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();

		// Adjusts kdRowCount to ensure tests aren't run on empty rows
		boolean readyToTest = false;
		while (!readyToTest) {
			if ((keywordSheet.getRow(kdRowCount).getCell(0) == null)
					&& (keywordSheet.getRow(kdRowCount).getCell(2) == null)) {
				keywordSheet.removeRow(keywordSheet.getRow(kdRowCount));
				kdRowCount--;
			} else if (kdRowCount == 0)
				break;
			else
				readyToTest = true;
		}

		/*
		 * For each row in the Data sheet. We want to set up test for all
		 * scenarios in the keyword sheet for a single row before going to the
		 * next row in the data sheet in order to prevent data mismatches. We
		 * start out at 1 becasue row 0 is the column names.
		 */
		for (int r = 1; r <= ddRowCount; r++) {
			Row ddRow = dataSheet.getRow(r);

			// For each row in the keyword sheet we want to write tests for our given data row.
			for (int i = 0; i < kdRowCount; i++) {
				Object[] rowObject = new Object[5];

				// First row contains description words therefore start at second row.
				Row kdRow = keywordSheet.getRow(i + 1);

				/*
				 * Skip scenario name rows, and sets first cell for steps to the next row
				 */
				if ((kdRow.getCell(0) != null) && (!kdRow.getCell(0).toString().equals(""))) {
					scenarioName = kdRow.getCell(0).toString();
					continue;
				} else {
					rowObject[0] = scenarioName;
				}

				// For each column in a row of keyword sheet, skip first column
				// because it is set as test name
				for (int j = 1; j < kdRow.getLastCellNum(); j++) {
					// If there is a value in Value column get it from data sheet
					if (j == 4 && kdRow.getCell(j) != null) {
						String value = kdRow.getCell(j).toString();

						/*
						 * For each column in a row of the data row, we search
						 * for the column in the data sheet that matches the
						 * value from the keyword sheet.
						 */
						for (int l = 0; l < ddRow.getLastCellNum(); l++) {
							if (value.equals(dataSheet.getRow(0).getCell(l).toString())) {
								cellData = ddRow.getCell(l).toString();
								isDataValue = true;
							}
						}
					}

					/*
					 * If the column of the row we are looking at is null, set
					 * it to an Empty string, because null pointers.
					 * 
					 * If the column is not null, and is not the value column
					 * (determined by the isDataValue boolean)
					 */
					if (kdRow.getCell(j) == null) {
						cellData = "";
					} else if (isDataValue == false) {
						cellData = kdRow.getCell(j).toString();
					}

					isDataValue = false;
					rowObject[j] = cellData;
				}

				/*
				 * Now that rowObject has been filled with the test to be run,
				 * add that row to the master ArrayObject.
				 */
				testingValues.add(rowObject);
			}
		}

		/*
		 * Convert testingValues to 2D array so it can be returned.
		 */
		Object[][] newObject = new Object[testingValues.size()][5];
		for (int i = 0; i < testingValues.size(); i++) {
			for (int j = 0; j < 5; j++) {
				newObject[i][j] = testingValues.get(i)[j];
			}
		}
		return newObject;
	}
}