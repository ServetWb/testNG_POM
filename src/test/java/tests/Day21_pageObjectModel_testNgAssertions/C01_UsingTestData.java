package tests.Day21_pageObjectModel_testNgAssertions;


import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;


public class C01_UsingTestData {

     /*
        The main goal of the Page Object Model (POM) is to ensure that:
        - If there is any change in locators, test data, or test run configurations,
        - Instead of updating each test method individually,
        - You make the fix once at the designated place,
        - And ALL test methods are automatically updated as a result.
     */

    @Test
    public void positiveLoginTest() {

        // 1- Go to the homepage of https://www.testotomasyonu.com
        // Driver.getDriver().get("https://www.testotomasyonu.com/"); // Not dynamic
        Driver.getDriver().get(ConfigReader.getProperty("toUrl")); // Dynamic

        // 2- Click on the account link
        TestAutomationPage testPage = new TestAutomationPage();
        ReusableMethods.wait(2);
        testPage.accountLink.click(); // Dynamic

        // 3- Enter a valid email address in the user email field
        // testPage.emailBox.sendKeys("wise@gmail.com"); // Not dynamic
        testPage.emailBox.sendKeys(ConfigReader.getProperty("toValidEmail")); // Dynamic

        // 4- Enter a valid password in the user password field
        // testPage.passwordBox.sendKeys("123456"); // Not dynamic
        testPage.passwordBox.sendKeys(ConfigReader.getProperty("toValidPassword")); // Dynamic

        // 5- Click the login button to log in
        testPage.loginButton.click(); // Dynamic

        // 6- Verify that login was successful
        Assert.assertTrue(testPage.logoutButton.isDisplayed()); // Dynamic

        // 7- Log out
        testPage.logoutButton.click(); // Dynamic

        // 8- Close the browser
        Driver.quitDriver();
    }
}