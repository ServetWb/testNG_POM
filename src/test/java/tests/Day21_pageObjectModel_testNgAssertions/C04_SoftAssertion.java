package tests.Day21_pageObjectModel_testNgAssertions;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class C04_SoftAssertion {

     /*
        The advantage of SoftAssert:
        - It continues executing all assertions until we call assertAll()

        The downside:
        - The failure is reported only when assertAll() is called,
          so debugging the exact failing line requires extra effort
     */

    @Test
    public void detailedSearchTest() {

        // 1. Navigate to the Testotomasyonu homepage and verify that the URL contains "testotomasyonu111"
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        String expectedUrlContent = "testotomasyonu111";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Homepage URL does not contain 'testotomasyonu'");

        // 2. Perform a search using the keyword from the config file,
        //    and verify that products are found in the results

        // Perform the search
        TestAutomationPage testPage = new TestAutomationPage();
        testPage.searchBox.sendKeys(ConfigReader.getProperty("searchKeyword") + Keys.ENTER);

        // Verify that the result text is NOT equal to the "no product found" message
        String unexpectedResultText = ConfigReader.getProperty("unexpectedResultText");
        String actualResultText = testPage.searchResultText.getText();

        softAssert.assertNotEquals(actualResultText, unexpectedResultText,
                "No products were found when searching for the keyword");

        // 3. Click on the first product in the search results
        testPage.foundProductElementsList
                .get(0)
                .click();

        // Verify that the product name on the product page
        // contains the search keyword (case-insensitive)

        String expectedNameContent = ConfigReader.getProperty("searchKeyword") + 111;
        String actualProductName = testPage.firstProductNameElement
                .getText()
                .toLowerCase();

        softAssert.assertTrue(actualProductName.contains(expectedNameContent),
                "Product name does not contain the expected keyword");

        // Report all soft assertion results
        softAssert.assertAll();

        // Close the browser
        Driver.quitDriver();
    }
}