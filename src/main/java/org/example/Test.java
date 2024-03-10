package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        List<Integer> number = new ArrayList<Integer>();

        number.add(5);
        number.add(10);
        number.add(15);
        number.add(20);
        number.add(8);
        number.add(12);

        List<Integer> sortedList = number.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedList.get(sortedList.size()-2));





        List<String> name = new ArrayList<String>();

        name.add("Time Swimmer");
        name.add("Hammer Time");

        int count = 0;

        for (String s : name) {
            String[] words = s.split(" ");
            for (String word : words) {
                if (word.contains("me"))
                    count++;
            }
        }
        System.out.println("The count of 'me' = "+count);





        File file = new File("D:\\RBC_Test\\file1.xlsx");
//        Assert.assertTrue(file.exists(), "File DOES NOT Exist");

        if (file.exists())
            System.out.println("File Exists");
        else
            System.out.println("File DOES NOT Exist");






        String dirName="D:\\RBC_Test";
        File dir = new File(dirName);
        File[] dir_files = dir.listFiles();

        for (File file1 : dir_files) {
//            System.out.println(file.getName());
            if (file1.getName().contains("file1"))
                System.out.println("File Exists");
        }



        try {
            FileInputStream fis = new FileInputStream("D:\\RBC_Test\\TestData.properties");
            Properties property = new Properties();
            property.load(fis);

            System.out.println(property.getProperty("firstname"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        File file1 = new File("D:\\RBC_Test\\testData_1.txt");
        File file2 = new File("D:\\RBC_Test\\testData_2.txt");

        try {
            Scanner sc_file1 = new Scanner(file1);
            Scanner sc_file2 = new Scanner(file2);

            if (sc_file1.nextLine().equals(sc_file2.nextLine()))
                System.out.println("Both the files are equal");
            else
                System.out.println("Both the files are NOT equal");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



        try {
            FileInputStream fis_1 = new FileInputStream("D:\\RBC_Test\\file1.xlsx");
            FileInputStream fis_2 = new FileInputStream("D:\\RBC_Test\\file2.xlsx");

            XSSFWorkbook wb_1 = new XSSFWorkbook(fis_1);
            XSSFWorkbook wb_2 = new XSSFWorkbook(fis_2);

            XSSFSheet sheet_1 = wb_1.getSheet("Sheet1");
            XSSFSheet sheet_2 = wb_2.getSheet("Sheet1");

            Iterator<Row> rows_1 = sheet_1.iterator();
            Iterator<Row> rows_2 = sheet_2.iterator();

            boolean match = true;

            while (rows_1.hasNext() && rows_2.hasNext()) {
                Row row_1 = rows_1.next();
                Row row_2 = rows_2.next();

                Iterator<Cell> cells_1 = row_1.cellIterator();
                Iterator<Cell> cells_2 = row_2.cellIterator();

                String text1 = "", text2 = "";

                while (cells_1.hasNext() && cells_2.hasNext()) {
                    text1 = cells_1.next().toString();
                    text2 = cells_2.next().toString();

                    System.out.println(text1+"\t"+text2);
                    if (!text1.equals(text2)){
                        match = false;
                    }
                }
            }

            if (match)
                System.out.println("Both the files are equal");
            else System.out.println("Both the files are NOT equal");

//            String text_1 = sheet_1.getRow(1).getCell(1).toString();



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
