package dataDrivers;

import java.io.*;
import java.util.Properties;

public class ReadPropertyFile {

    public static String readData(String key) {
        String value = "";
        try {
            InputStream input = new FileInputStream("DataFolder"+ File.separator +"testData.properties");
            Properties properties = new Properties();
            try {
                properties.load(input);
                value = properties.getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
//                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
        return value;
    }




}
