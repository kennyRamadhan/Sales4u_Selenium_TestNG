package MBI.DST.Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UtilsExcel {
	
	 public static Object[][] getTestData(String excelPath, String sheetName) {
	        Object[][] data = null;
	        try (FileInputStream fis = new FileInputStream(excelPath);
	                Workbook workbook = WorkbookFactory.create(fis)) {

	               System.out.println("Excel opened: " + excelPath);
	               System.out.println("Available sheets: ");
	               for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	                   System.out.println("- " + workbook.getSheetName(i));
	               }

	               Sheet sheet = workbook.getSheet(sheetName);
	               if (sheet == null) {
	                   throw new RuntimeException("Sheet " + sheetName + " not found in Excel file!");
	               }

	               int rowCount = sheet.getPhysicalNumberOfRows();
	               int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	               data = new Object[rowCount - 1][colCount]; // skip header
	               for (int i = 1; i < rowCount; i++) {
	                   Row row = sheet.getRow(i);
	                   for (int j = 0; j < colCount; j++) {
	                       Cell cell = row.getCell(j);
	                       data[i - 1][j] = cell == null ? "" : cell.toString();
	                   }
	               }
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	           return data;
	    }
}
