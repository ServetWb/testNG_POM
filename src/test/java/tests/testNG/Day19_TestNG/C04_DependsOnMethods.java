package tests.testNG.Day19_TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C04_DependsOnMethods {

     /*
        Regardless of order,
        if there are dependent tests—meaning,
        if Test B doesn't make sense unless Test A has run and passed:

        1- If you add "dependsOnMethods = 'A'" in front of Test B,
           Test B will not run at all unless Test A runs and passes.

        2- Although the 'dependsOnMethods' feature is not used for ordering,
           when it’s time to run method B, it checks whether method A has run and passed.
           If A hasn't run yet, it will make sure to run A first.

        3- Normally, we can run each test independently.
           However, if you try to run B on its own,
           it will make sure A runs first; if A passes, then B will run too.

           But this behavior only works for **two** methods.
           If you have three tests that depend on each other,
           and you try to directly run the 3rd one,
           it will say: "No tests were found" and none of the tests will run.
     */

    // Create 3 separate test methods to perform the following tasks:
    // 1. Navigate to Testotomasyonu homepage and verify that the URL contains "testotomasyonu"
    // 2. Search for "phone" and verify that products are found in the results
    // 3. Click the first product and verify that the product name on the opened page
    //    contains "phone" (case insensitive)

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Launch Chrome browser
        driver.manage().window().maximize(); // Maximize browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }

    @AfterClass
    public void teardown() {
        driver.quit(); // Close the browser
    }

    @Test // priority = 0
    public void homepageTest() {
        // 1. Go to Testotomasyonu homepage and verify that the URL contains "testotomasyonu"

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));
        // Assert that the actual URL contains the expected content
    }

    @Test(dependsOnMethods = "homepageTest") // priority = 0
    public void phoneSearchTest() {
        // 2. Search for "phone" and verify that products are found

        // Perform the search
        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that the search result shows products
        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found";
        String actualResultText = resultTextElement.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);
        // Assert that the result does NOT say "0 Products Found"
    }

    @Test(dependsOnMethods = "phoneSearchTest") // priority = 20
    public void firstProductNameTest() {
        // 3. Click on the first product
        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]"))
                .click();

        // Verify that the product name on the opened page contains "phone"
        // in a case-insensitive way

        String expectedNameContent = "phone";

        String actualName = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
                .getText()
                .toLowerCase(); // Convert to lowercase for case-insensitive comparison

        Assert.assertTrue(actualName.contains(expectedNameContent));
        // Assert that the actual name includes "phone"

    }
}