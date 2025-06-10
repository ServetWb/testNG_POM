package tests.Day20_PageObjectModel;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C07_DynamicNegativeLoginTest {

    @Test
    public void test01() {
        // 1- Navigate to https://www.testotomasyonu.com/ homepage
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // 2- Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.accountLink.click();

        // 3- Create a test method:
        //    - valid email, invalid password
        testAutomationPage.emailBox.sendKeys(ConfigReader.getProperty("toValidEmail"));
        testAutomationPage.passwordBox.sendKeys(ConfigReader.getProperty("toInvalidPassword"));

        // 4- Click the login button to attempt login
        testAutomationPage.loginButton.click();

        // 5- Verify that login was not successful
        Assert.assertTrue(testAutomationPage.emailBox.isDisplayed());

    }
}