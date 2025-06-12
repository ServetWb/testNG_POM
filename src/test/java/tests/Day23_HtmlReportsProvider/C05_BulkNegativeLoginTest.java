package tests.Day23_HtmlReportsProvider;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class C05_BulkNegativeLoginTest {

    @DataProvider
    public static Object[][] userCredentialsDataProvider() {
        // Array of email-password pairs for negative login tests
        String[][] usersArray = {
                {"yigit@kmail.com", "125687"},
                {"tugba@tmail.com", "345678"},
                {"yusuf@mmail.com", "456789"},
                {"sumeyra@smail.com", "342321"},
                {"canan@cmail.com", "987098"}};
        return usersArray;
    }

/*
    Perform the following negative login test for each email-password pair:
    yigit@kmail.com   125687
    tugba@tmail.com   345678
    yusuf@mmail.com   456789
    sumeyra@smail.com 342321
    canan@cmail.com   987098
*/

    @Test(dataProvider = "userCredentialsDataProvider")
    public void negativeLoginTest(String email, String password) {
        // 1- Go to https://www.testotomasyonu.com/ homepage
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        ReusableMethods.wait(1);

        // 2- Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        ReusableMethods.wait(1);
        testAutomationPage.accountLink.click();

        // 3- Try to login using the provided email and password
        testAutomationPage.emailBox.sendKeys(email);
        testAutomationPage.passwordBox.sendKeys(password);

        // 4- Click the login button and try to login
        testAutomationPage.loginButton.click();
        ReusableMethods.wait(1);

        // 5- Verify that login was unsuccessful by checking that email input is still displayed
        Assert.assertTrue(testAutomationPage.emailBox.isDisplayed());

        // 6- Close the driver
        Driver.quitDriver();
    }
}
