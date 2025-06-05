package tests.Day21_pageObjectModel_testNgAssertions;

import Pages.ZeroWebappPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class C05_SoftAssertion {

    @Test
    public void test01() {
        // 1. Navigate to “http://zero.webappsecurity.com/”
        Driver.getDriver().get(ConfigReader.getProperty("zeroUrl"));

        // 2. Verify that you are on the webappsecurity homepage
        SoftAssert softAssert = new SoftAssert();

        String expectedUrl = ConfigReader.getProperty("zeroUrl");
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl, expectedUrl, "Actual URL is different from expected URL");

        // 3. Click the "Sign in" button
        ZeroWebappPage zeroWebappPage = new ZeroWebappPage();
        zeroWebappPage.signInLinkOnHomePage.click();

        // 4. Enter “username” in the login input box
        zeroWebappPage.loginInputBox.sendKeys(ConfigReader.getProperty("zeroUsername"));

        // 5. Enter “password” in the password input box
        zeroWebappPage.passwordInputBox.sendKeys(ConfigReader.getProperty("zeroPassword"));

        // 6. Click the "Sign in" button on the login page
        zeroWebappPage.signInButtonOnLoginPage.click();

        // 7. Click the browser's back button
        Driver.getDriver().navigate().back();

        // 8. Verify that login was successful (check if settings link is visible)
        softAssert.assertTrue(zeroWebappPage.settingsLink.isDisplayed(), "Login was not successful");

        // 9. Click the Online Banking menu
        zeroWebappPage.onlineBankingMenu.click();

        // 10. Go to the "Pay Bills" page
        zeroWebappPage.payBillsLink.click();

        // 11. Click the “Purchase Foreign Currency” button
        zeroWebappPage.purchaseForeignCurrencyLink.click();

        // 12. Verify that the Currency dropdown menu is accessible
        WebElement currencyDropdown = zeroWebappPage.settingsLink;
        softAssert.assertTrue(currencyDropdown.isDisplayed() && currencyDropdown.isEnabled(),
                "Currency dropdown is not accessible");

        // 13. Select “Eurozone (euro)” from the dropdown menu
        Select select = new Select(currencyDropdown);
        select.selectByVisibleText("Eurozone (euro)");

        // 14. Verify that "Eurozone (euro)" is selected
        String selectedOption = select.getFirstSelectedOption().getText();
        softAssert.assertEquals(selectedOption, "Eurozone (euro)", "Eurozone (euro) was not selected");

        // 15. Verify that the dropdown contains 16 options
        int optionCount = select.getOptions().size();
        softAssert.assertEquals(optionCount, 16, "Currency dropdown does not contain 16 options");

        // 16. Verify that "Canada (dollar)" is one of the options in the dropdown
        boolean canadaFound = select.getOptions().stream()
                .anyMatch(option -> option.getText().equals("Canada (dollar)"));
        softAssert.assertTrue(canadaFound, "'Canada (dollar)' is not found in the dropdown");

        // Report all soft assertions
        softAssert.assertAll();

        // 17. Close the browser
        Driver.quitDriver();
    }
}
