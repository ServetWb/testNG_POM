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

public class C03_Priority {
      /*
        TestNG runs tests in alphabetical order by default.

        BUT, if you want a specific execution order,
        you can define it using the 'priority' attribute.

        Rules:
        1- Tests run from the smallest to the largest priority value.
        2- If a test method has no priority assigned,
           TestNG treats it as if it has priority = 0 by default.
        3- If multiple methods share the same priority value,
           they run in alphabetical order among themselves.
     */

    // Tasks:
    // 1. Go to the Test Otomasyonu homepage and verify the URL contains "testotomasyonu"
    // 2. Search for "phone" and verify that products are found in the results
    // 3. Click the first product and verify that its name contains "phone" (case insensitive)

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

    @Test // priority = 0 (default)
    public void homePageTest() {
        // Task 1: Go to the homepage and check if the URL contains "testotomasyonu"

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));
    }

    @Test // priority = 0 (default, same as above)
    public void phoneSearchTest() {
        // Task 2: Search for "phone" and verify that products are found

        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found";
        String actualResultText = resultTextElement.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);
    }

    @Test(priority = 20)
    public void firstProductNameTest() {
        // Task 3: Click the first product
        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]")).click();

        // Check if the product name contains "phone" (not case-sensitive)
        String expectedNameContent = "phone";
        String actualName = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
                .getText()
                .toLowerCase();

        Assert.assertTrue(actualName.contains(expectedNameContent));
    }
}