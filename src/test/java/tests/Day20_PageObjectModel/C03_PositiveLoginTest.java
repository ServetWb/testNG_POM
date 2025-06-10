package tests.Day20_PageObjectModel;

import Pages.TestAutomationPage;
import Utilities.Driver;
import Utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C03_PositiveLoginTest {
    @Test
    public void positiveLoginTest() {
        // 1- Go to https://www.testotomasyonu.com/ homepage
        Driver.getDriver().get("https://www.testotomasyonu.com/");

        // 2- Click on the "Account" link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        ReusableMethods.wait(2);
        testAutomationPage.accountLink.click();

        // 3- Enter a valid email address
        testAutomationPage.emailBox.sendKeys("wise@gmail.com");

        // 4- Enter a valid password
        testAutomationPage.passwordBox.sendKeys("123456");

        // 5- Click the Login button
        testAutomationPage.loginButton.click();

        // 6- Verify that login was successful
        Assert.assertTrue(testAutomationPage.logoutButton.isDisplayed());

        // 7- Log out
        testAutomationPage.logoutButton.click();

        // 8- Close the browser
        Driver.quitDriver();
    }
}
