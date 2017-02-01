package SDET1611.testingframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {
		
		//Get file
		File file = new File(filePath);
		//Read file bytes
		FileInputStream inputStream = new FileInputStream(file);
		//Create excel workbook
		Workbook workBook = null;
		//Get file extension to determine workbook object
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		//Check if extension is .xlsx file (Excel 2007 and up)
		//Check if extension is .xls file (Excel 2003 and down)
		if(fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		} else if(fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}
		//Get sheet
		Sheet sheet = workBook.getSheet(sheetName);
		
		return sheet;
	}
}
