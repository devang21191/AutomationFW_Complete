package dataDrivers;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Log4J;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelData {
    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelSheet;

    public static Object[][] getTableArray(String filePath, String sheetName, int rows, int columns) {
        String[][] tableArray = null;

        try {
            FileInputStream excelFile = new FileInputStream(filePath);

            ExcelWBook = new XSSFWorkbook(excelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);

            int totalRows = 0, totalCols = 0;
            if (rows <= ExcelSheet.getLastRowNum())
                totalRows = rows;
            else {
                totalRows = ExcelSheet.getLastRowNum();
                String fileName = new File(filePath).getName();
                Log4J.info("Input Row Count("+rows+") is greater than total Rows("+totalRows+") present in Excel:'"+fileName+"', Sheet:'"+sheetName+"'.\nFetching data from all "+totalRows+" Rows.");
            }
            totalCols = columns;
            tableArray = new String[totalRows][totalCols];

            int startRow = 1, startCol = 0;
            int rowIndex = 0, colIndex;

            for (int i = startRow; i <= totalRows; i++, rowIndex++) {
                colIndex = 0;
                for (int j = startCol; j < totalCols; j++, colIndex++) {
                    tableArray[rowIndex][colIndex] = ExcelSheet.getRow(i).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tableArray;
    }
}
