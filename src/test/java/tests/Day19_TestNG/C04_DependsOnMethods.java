package tests.Day19_TestNG;

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
        Regardless of method order, if tests depend on each other
        (e.g., it makes no sense for test B to run if test A fails):

        1 - If you write dependsOnMethods = "A" in test B,
            then test B will **only run if test A passes**.

        2 - Even though dependsOnMethods doesn't define order directly,
            when it’s test B's turn to run, it will make sure test A has passed.
            If A didn’t run yet, it will run it first.

        3 - Normally, each test can run independently.
            But if you try to run **only B**, it will first execute A,
            and **only if A passes**, it runs B.

        ⚠ BUT this behavior works **only for direct dependencies**.
          If you have A → B → C and you try to run only C directly,
          it will say: “No tests were found,” because it can’t auto-traverse multiple layers.
     */

    // Create 3 test methods to perform the following:
    // 1. Visit the homepage and verify the URL contains "testotomasyonu"
    // 2. Search for "phone" and verify products are found
    // 3. Click the first product and verify its name contains "phone" (case-insensitive)

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup before any test in this class runs
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {
        // Cleanup after all tests in this class finish
        driver.quit();
    }

    @Test // Base test - no dependency
    public void homePageTest() {
        // Step 1: Visit the homepage and verify the URL

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));
    }

    @Test(dependsOnMethods = "homePageTest")
    public void phoneSearchTest() {
        // Step 2: Search for "phone" and verify results are found

        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found";
        String actualResultText = resultTextElement.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);
    }

    @Test(dependsOnMethods = "phoneSearchTest")
    public void firstProductNameTest() {
        // Step 3: Click the first product

        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]")).click();

        // Verify the product name contains "phone" (case insensitive)

        String expectedNameContent = "phone";
        String actualName = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
                .getText()
                .toLowerCase();

        Assert.assertTrue(actualName.contains(expectedNameContent));
    }
}

