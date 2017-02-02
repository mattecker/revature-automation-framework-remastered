package SDET1611.testingframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	/**
	 * Reads an excel file and returns a worksheet object
	 * @param filePath 		The path to the excel file
	 * @param fileName		The name of the excel file
	 * @param sheetName		The name of the requested worksheet in the excel file.
	 * @return				The requested worksheet
	 * @throws 				IOException If the file cannot be found
	 */
	public static Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workBook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		if(fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		} else if(fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}
		
		Sheet sheet = workBook.getSheet(sheetName);
		
		return sheet;
	}
}
