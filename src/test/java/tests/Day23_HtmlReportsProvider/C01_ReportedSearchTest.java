package tests.Day23_HtmlReportsProvider;

import Utilities.TestBaseReport;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.TestAutomationPage;
import Utilities.ConfigReader;
import Utilities.Driver;


public class C01_ReportedSearchTest extends TestBaseReport {

    /**
     * TestNG does not natively generate HTML reports,
     * but with the help of the AventStack ExtentReports dependency,
     * we can convert existing TestNG test methods into detailed HTML reports.
     * <p>
     * Steps:
     * 1. Create your test method using TestNG.
     * 2. Extend the TestBaseReport class to inherit reporting functionality.
     * 3. Three main objects are needed for HTML reporting:
     * - Two (ExtentReports & ExtentSparkReporter) are configured in TestBaseReport.
     * - The third, `extentTest`, must be re-initialized in each test method.
     * 4. Use `extentTest.info()` to log every step you'd like to appear in the report.
     */

    @Test
    public void detailedSearchTest() {
        extentTest = extentReports.createTest(
                "Detailed Search Test",
                "User should be able to search for a specified keyword and verify product details"
        );

        // 1. Go to Testotomasyonu homepage and verify that the URL contains "testotomasyonu"
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        extentTest.info("Navigates to the Testotomasyonu homepage");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));
        extentTest.pass("Verifies that the URL contains 'testotomasyonu'");

        // 2. Search for the keyword and verify that products are found in the search results
        TestAutomationPage testAutomationPage = new TestAutomationPage();
        testAutomationPage.searchBox.sendKeys(ConfigReader.getProperty("toSearchKeyword") + Keys.ENTER);
        extentTest.info("Performs search using the specified keyword");

        String unexpectedResultMessage = ConfigReader.getProperty("toUnexpectedResultMessage");
        String actualResultText = testAutomationPage.searchResultText.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultMessage);
        extentTest.pass("Verifies that the search returned product results");

        // 3. Click on the first product in the result list
        testAutomationPage.foundProductElementsList.get(0).click();
        extentTest.info("Clicks on the first product in the search results");

        //    Verify that the opened product page title contains the search keyword (case insensitive)
        String expectedProductNameKeyword = ConfigReader.getProperty("toSearchKeyword").toLowerCase();
        String actualProductName = testAutomationPage.firstProductNameElement.getText().toLowerCase();

        Assert.assertTrue(actualProductName.contains(expectedProductNameKeyword));
        extentTest.pass("Verifies that the product title contains the search keyword (case insensitive)");

        extentTest.info("Closes the page");
    }

}
