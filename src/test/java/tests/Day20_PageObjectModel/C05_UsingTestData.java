package tests.Day20_PageObjectModel;

import Pages.TestAutomationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;

public class C05_UsingTestData {

    @Test
    public void positiveLoginTest() {
        /*
            The main purpose of the Page Object Model (POM)
            is to make test methods dynamic.
            In other words, if a locator, browser, or test data changes,
            we shouldn't have to update this C05 class manually.

            We already handled locators using the Page class.

            Now we want to handle test data using the configuration.properties file.

            In Java, to read data from a file, we typically need:
            file path, FileInputStream, and loops.

            However, doing this every time is tedious.
            So in POM, we use a **helper/messenger** class (ConfigReader)
            that retrieves the value of a given key from the configuration.properties file.
         */

        // 1- Navigate to https://www.testotomasyonu.com/ homepage
        // Driver.getDriver().get("https://www.testotomasyonu.com/"); // Not dynamic
        Driver.getDriver().get(ConfigReader.getProperty("toUrl")); // Dynamic

        // 2- Click the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        ReusableMethods.wait(2);
        testAutomationPage.accountLink.click(); // Dynamic

        // 3- Enter valid user email
        // testAutomationPage.emailField.sendKeys("wise@gmail.com"); // Not dynamic
        testAutomationPage.emailField.sendKeys(ConfigReader.getProperty("toValidEmail")); // Dynamic

        // 4- Enter valid user password
        // testAutomationPage.passwordField.sendKeys("123456"); // Not dynamic
        testAutomationPage.passwordField.sendKeys(ConfigReader.getProperty("toValidPassword")); // Dynamic

        // 5- Click the Login button
        testAutomationPage.loginButton.click(); // Dynamic

        // 6- Verify successful login
        Assert.assertTrue(testAutomationPage.logoutButton.isDisplayed()); // Dynamic

        // 7- Log out
        testAutomationPage.logoutButton.click(); // Dynamic

        // 8- Close the browser
        Driver.quitDriver();
    }
}
