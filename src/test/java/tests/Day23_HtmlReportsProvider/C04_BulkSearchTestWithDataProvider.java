package tests.Day23_HtmlReportsProvider;

import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class C04_BulkSearchTestWithDataProvider {

    @DataProvider
    public static Object[][] keywordDataProvider() {
        // Array of search keywords
        String[][] searchKeywords = {
                {"apple"}, {"shoe"}, {"java"}, {"samsung"}, {"dress"}, {"cokoprens"}, {"nutella"}
        };
        return searchKeywords;
    }

/*
    To create a test using DataProvider:
    1- First, write the test method as if you are searching for a single keyword.
    2- Add the search keyword as a parameter in the test method.
    3- Create a DataProvider method to supply the keywords as parameters.
    4- Link the DataProvider to the test method using the dataProvider attribute.

    Note: A DataProvider method returns a two-dimensional Object array.
*/

    @Test(dataProvider = "keywordDataProvider")
    public void testSearchWithDataProvider(String searchKeyword) {
        // Navigate to the Testotomasyonu homepage
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // Search for each keyword from the list
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.searchBox.sendKeys(searchKeyword + Keys.ENTER);

        // Verify that the search returns products (i.e., no unexpected 'no results' message)
        String unexpectedSearchResult = ConfigReader.getProperty("toUnexpectedResultMessage");
        String actualSearchResult = testAutomationPage.searchResultText.getText();

        Assert.assertNotEquals(actualSearchResult, unexpectedSearchResult);
    }
}