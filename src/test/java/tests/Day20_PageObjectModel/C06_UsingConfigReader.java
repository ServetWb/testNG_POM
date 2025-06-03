package tests.Day20_PageObjectModel;

import Utilities.ConfigReader;
import org.testng.annotations.Test;

public class C06_UsingConfigReader {


    @Test
    public void test01() {

        // Prints the value of "toUrl" key → https://www.testotomasyonu.com
        System.out.println(ConfigReader.getProperty("toUrl"));

        // Prints the value of "toValidEmail" key → wise@gmail.com
        System.out.println(ConfigReader.getProperty("toValidEmail"));

        // Prints the value of "toInvalidPassword" key → 654321
        System.out.println(ConfigReader.getProperty("toInvalidPassword"));

        // This key does not exist in the properties file, so it prints null
        System.out.println(ConfigReader.getProperty("whatAreWeEatingTonight?")); // null
    }
}