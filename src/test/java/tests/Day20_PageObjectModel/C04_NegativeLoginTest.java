package tests.Day20_PageObjectModel;


import Pages.TestAutomationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.ReusableMethods;

public class C04_NegativeLoginTest {

    // 1- Navigate to https://www.testotomasyonu.com/ homepage
    // 2- Click on the account link
    // 3- Create 3 separate test methods:
    //    - valid email, invalid password
    //    - invalid email, valid password
    //    - invalid email, invalid password
    // 4- Try to log in by clicking the login button
    // 5- Verify that login was not successful

    @Test
    public void invalidPasswordTest() {
        // 1- Go to the homepage
        Driver.getDriver().get("https://www.testotomasyonu.com/");
        ReusableMethods.wait(1);

        // 2- Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        ReusableMethods.wait(1);
        testAutomationPage.accountLink.click();

        // 3- Valid email, invalid password
        testAutomationPage.emailField.sendKeys("wise@gmail.com");
        testAutomationPage.passwordField.sendKeys("54321");

        // 4- Attempt to log in
        testAutomationPage.loginButton.click();

        // 5- Verify that login was not successful
        Assert.assertTrue(testAutomationPage.emailField.isDisplayed());
    }

    @Test
    public void invalidEmailTest() {
        // 1- Go to the homepage
        Driver.getDriver().get("https://www.testotomasyonu.com/");
        ReusableMethods.wait(1);

        // 2- Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.accountLink.click();

        // 3- Invalid email, valid password
        testAutomationPage.emailField.sendKeys("wise111@gmail.com");
        testAutomationPage.passwordField.sendKeys("123456");

        // 4- Attempt to log in
        testAutomationPage.loginButton.click();

        // 5- Verify that login was not successful
        Assert.assertTrue(testAutomationPage.emailField.isDisplayed());
    }

    @Test
    public void invalidEmailAndInvalidPasswordTest() {
        // 1- Go to the homepage
        Driver.getDriver().get("https://www.testotomasyonu.com/");
        ReusableMethods.wait(1);

        // 2- Click on the account link
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.accountLink.click();

        // 3- Invalid email, invalid password
        testAutomationPage.emailField.sendKeys("wise111@gmail.com");
        testAutomationPage.passwordField.sendKeys("654321");

        // 4- Attempt to log in
        testAutomationPage.loginButton.click();

        // 5- Verify that login was not successful
        Assert.assertTrue(testAutomationPage.emailField.isDisplayed());
    }
}