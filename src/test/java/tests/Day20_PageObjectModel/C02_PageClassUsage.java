package tests.Day20_PageObjectModel;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.TestAutomationPage;
import Utilities.Driver;

import java.util.List;

public class C02_PageClassUsage {

    @Test

    public void searchTest() {
        // 1. Navigate to TestOtomasyonu homepage
        // and verify that the URL contains "testotomasyonu"
        Driver.getDriver().get("https://www.testotomasyonu.com");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));

        // 2. Search for "phone" and verify that some products are found

        // Perform a search for "phone"
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that the search result does not say "0 Products Found"
        String unexpectedResultText = "0 Products Found";
        String actualResultText = testAutomationPage.searchResultText.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);

        // 3. Verify that 4 products are listed in the results

        int expectedProductCount = 4;
        int actualProductCount = testAutomationPage.searchResultItems.size();

        Assert.assertEquals(actualProductCount, expectedProductCount);

        // 4. Click on the first product
        testAutomationPage.searchResultItems.get(0).click();

        // Verify that the product name on the opened page
        // contains the word "phone" (case-insensitive)
        String expectedNameContent = "phone";
        String actualName = testAutomationPage.productNameOnDetailPage.getText().toLowerCase();

        Assert.assertTrue(actualName.contains(expectedNameContent));

        // Close the browser
        Driver.quitDriver();
    }
}