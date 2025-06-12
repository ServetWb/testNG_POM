package tests.Day23_HtmlReportsProvider;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C03_BulkSearchTest {

    @Test
    public void bulkSearchTest() {
        /*
            When running multiple tests in a loop,
            the code stops at the first failed search.

            To test all items regardless of failures,
            we can use try-catch blocks or SoftAssert
            to continue testing all search terms.
         */

        // List of items to search
        List<String> searchItems = new ArrayList<>(Arrays.asList(
                "apple", "shoe", "java", "samsung", "dress", "cokoprens", "nutella"));

        // Navigate to the Testotomasyonu homepage
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // Search each item from the list and verify a product is found in the search results
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        SoftAssert softAssert = new SoftAssert();

        for (String searchTerm : searchItems) {
            testAutomationPage = new TestAutomationPage();
            ReusableMethods.wait(1);
            testAutomationPage.searchBox.sendKeys(searchTerm + Keys.ENTER);

            String unexpectedResultText = ConfigReader.getProperty("toUnexpectedResultMessage");

            testAutomationPage = new TestAutomationPage();
            ReusableMethods.wait(1);
            String actualResultText = testAutomationPage.searchResultText.getText();

            softAssert.assertNotEquals(
                    actualResultText,
                    unexpectedResultText,
                    searchTerm + " was not found");
        }

        softAssert.assertAll();
    }
}

