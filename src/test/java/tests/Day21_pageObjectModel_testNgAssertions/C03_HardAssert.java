package tests.Day21_pageObjectModel_testNgAssertions;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C03_HardAssert {

    @Test
    public void detailedSearchTest() {

        // 1. Navigate to Testotomasyonu homepage and verify that the URL contains "testotomasyonu"
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));

        // 2. Perform a search using the specified keyword,
        //    and verify that at least one product is found in the results

        TestAutomationPage testPage = new TestAutomationPage();

        // Perform search using the keyword from config file
        testPage.searchBox.sendKeys(ConfigReader.getProperty("searchKeyword") + Keys.ENTER);

        // Verify that the result text is NOT equal to the unexpected result ("0 Products Found")
        String unexpectedResultText = ConfigReader.getProperty("unexpectedResultText");
        String actualResultText = testPage.searchResultText.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);

        // 3. Click on the first product from the results
        testPage.foundProductElementsList
                .get(0)
                .click();

        // Verify that the product name on the new page contains the search keyword (case-insensitive)
        String expectedNameContent = ConfigReader.getProperty("searchKeyword").toLowerCase();
        String actualProductName = testPage.firstProductNameElement.getText().toLowerCase();

        Assert.assertTrue(actualProductName.contains(expectedNameContent));

        // Close the browser
        Driver.quitDriver();
    }
}