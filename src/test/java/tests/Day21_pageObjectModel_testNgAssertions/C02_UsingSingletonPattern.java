package tests.Day21_pageObjectModel_testNgAssertions;

import Utilities.Driver;
import Utilities.ReusableMethods;
import org.testng.annotations.Test;

public class C02_UsingSingletonPattern {

    @Test
    public void test01() {
        // Developers who use the Page Object Model (POM)
        // prefer the Singleton Pattern to prevent the Driver class
        // from being used in different (and possibly incorrect) ways

        // Driver driverInstance = new Driver(); // This is not allowed due to private constructor

        // Go to the homepage of testotomasyonu.com
        Driver.getDriver().get("https://www.testotomasyonu.com");

        ReusableMethods.wait(2);

        // Close the browser
        Driver.quitDriver();
    }
}