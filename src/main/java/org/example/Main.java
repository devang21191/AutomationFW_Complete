package org.example;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelSheet;
    public static void main(String[] args) {

//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_z");
//        Date date = new Date();
//        String fileName = "TestReport_"+dateFormat.format(date);
//        System.out.println(fileName);

//        System.out.println("methodName"+"_"+dateFormat.format(date)+".png");

//        File file = new File("D:\\SampleTestData_2.xlsx");
//        System.out.println(file.getName());

//        System.out.println(new File("D:\\SampleTestData_2.xlsx").getName());


        /**
         * To read data from Property file
         */
/*        try {
            InputStream input = new FileInputStream("DataFolder"+File.separator+"testData.properties");
            Properties property = new Properties();
            property.load(input);

            System.out.println(property.getProperty("url"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


        /**
         * To read data from XLS file
         */
/*        File file = new File("D:\\Test Data\\SampleTestData_1.xls");

        HSSFWorkbook wb = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            wb = new HSSFWorkbook(fis);
            HSSFSheet XlsSheet = wb.getSheet("userData");

            Iterator<Row> rows = XlsSheet.iterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
//                    switch (cell.getCellType()) {
//                        case cell.CELL_TYPE_STRING:
//                    }
                    System.out.print(cell.getStringCellValue());
                }
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/





        /**
         * To read data from XLSX file
         */
        String[][] tableArray = null;
        String filePath = "D:\\Test Data\\SampleTestData_2.xlsx", sheetName = "userData";

        int rows = 15, columns = 2;
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
                System.out.println("Input Row Count("+rows+") is greater than total Rows("+totalRows+") present in Excel:'"+fileName+"', Sheet:'"+sheetName+"'.\nFetching data from all "+totalRows+" Rows.");
            }
            totalCols = columns;
            tableArray = new String[totalRows][totalCols];

            int startRow = 1, startCol = 0;
            int rowIndex = 0, colIndex;

//            System.out.println(ExcelSheet.getRow(1).getCell(0).toString());

            for (int i = startRow; i <= totalRows; i++, rowIndex++) {
                colIndex = 0;
                for (int j = startCol; j < totalCols; j++, colIndex++) {
                    tableArray[rowIndex][colIndex] = ExcelSheet.getRow(i).getCell(j).toString();
                    System.out.println(tableArray[rowIndex][colIndex]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


/*        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");

        WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));

        email.sendKeys("testemail@test.test");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']"));
        System.out.println(errorMessage.getText()+": "+errorMessage.getText().contains("Login was unsuccessful. Please correct the errors and try again."));
        List<WebElement> c = errorMessage.findElements(By.xpath("./child::*"));
        for (WebElement e : c){
            System.out.println(e.getText()+": "+e.getText().contains("No customer account found"));
        }*/



/*        String fileName = getScreenshotName("test_method_01");
        String directory = System.getProperty("user.dir")+File.separator+"Screenshots";
        new File(directory).mkdirs();
        String path = directory + File.separator + fileName;
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Path: "+path);*/


    }

/*    public static boolean isElementPresent(WebElement element, String message) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                System.out.println(message+" Element is Present");
                return true;
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(message+" Element is NOT Present");
            return false;
        }
    }*/
/*    public static String getScreenshotName(String methodName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_z");
        Date date = new Date();
        return "Screenshot_"+methodName+"_"+dateFormat.format(date)+".png";
    }*/
}