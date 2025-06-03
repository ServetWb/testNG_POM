package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    /*
    This class has two main responsibilities:

    1- To read data from the configuration.properties file.
       When ConfigReader is used, we want the reading process to be done FIRST.
       For this reason, we perform the reading inside a static block.

       When the static block runs,
       it reads all key-value pairs from the configuration.properties file
       and loads them into the class-level "properties" object.

    2- After all key-value pairs are loaded into the properties object in the first step,
       the getProperty(requestedKey) method retrieves the value corresponding to the key
       we provide and returns it to the test method.
 */

    static Properties properties; // All key-value pairs from configuration.properties will be loaded here

    static {
        String filePath = "configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Could not read the properties file");
        }
    }
// The static block runs first and loads all key-value pairs from configuration.properties into the properties object

    public static String getProperty(String key) {
        return properties.getProperty(key);

    }
}