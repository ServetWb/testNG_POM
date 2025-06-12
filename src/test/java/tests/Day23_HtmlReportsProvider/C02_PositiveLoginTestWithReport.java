package tests.Day23_HtmlReportsProvider;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import Utilities.TestBaseReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_PositiveLoginTestWithReport extends TestBaseReport {

    @Test
    public void positiveLoginTest() {
        extentTest = extentReports.createTest("Positive Login Test",
                "User should be able to log in with valid credentials");

        // 1 - Navigate to the homepage of https://www.testotomasyonu.com/
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("User navigates to the Testotomasyonu homepage");

        // 2 - Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        ReusableMethods.takeTimestampedFullPageScreenshot(Driver.getDriver());
        ReusableMethods.wait(2);
        testAutomationPage.accountLink.click();
        extentTest.info("User clicks the account link");

        // 3 - Enter a valid email address
        testAutomationPage.emailBox.sendKeys(ConfigReader.getProperty("toValidEmail"));
        extentTest.info("User enters a valid email");

        // 4 - Enter a valid password
        testAutomationPage.passwordBox.sendKeys(ConfigReader.getProperty("toValidPassword"));
        extentTest.info("User enters a valid password");

        // 5 - Click the login button
        testAutomationPage.loginButton.click();
        extentTest.info("User clicks the login button");

        // 6 - Verify successful login
        Assert.assertTrue(testAutomationPage.logoutButton.isDisplayed());
        extentTest.pass("User successfully logs into the system");

        // 7 - Click the logout button
        testAutomationPage.logoutButton.click();
        extentTest.info("User logs out");

        // 8 - Close the page
        extentTest.info("User closes the page");
    }


}
