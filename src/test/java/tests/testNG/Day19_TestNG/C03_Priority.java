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

public class C03_Priority {

    /*
        TestNG runs tests by default in alphabetical order of method names.

        IF you want a different order,
        you can set the order manually using the priority attribute.

        Rules:
        1- Tests run from smaller to larger priority values.
        2- If no priority value is assigned to a test method,
           TestNG assigns a default priority value of 0 to that method.
        3- If multiple methods have the same priority value,
           the ones with the same priority will run
           in alphabetical order of their method names.

     */

    // Create 3 different test methods and perform the following tasks:
    // 1. Navigate to the Testotomasyonu homepage and verify that the URL contains "testotomasyonu"
    // 2. Search for "phone" and verify that at least one product is found
    // 3. Click the first product and verify that the product name on the opened page
    //    contains the word "phone" (case-insensitive)

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set up the WebDriver before running any tests
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {
        // Close the WebDriver after all tests have run
        driver.quit();
    }

    @Test // priority = 0 by default
    public void anasayfaTesti() {
        // 1. Navigate to the Testotomasyonu homepage and verify that the URL contains "testotomasyonu"

        driver.get("https://www.testotomasyonu.com");

        String expectedUrlContent = "testotomasyonu";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));
    }

    @Test // priority = 0 by default
    public void phoneAramaTesti() {
        // 2. Search for "phone" and verify that at least one product is found

        // Perform a search for the word "phone"
        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that the search result shows one or more products
        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found";
        String actualResultText = resultTextElement.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);
    }

    @Test(priority = 20)
    public void ilkUrunIsimTesti() {
        // 3. Click the first product,
        driver.findElement(By.xpath("(//*[@class='prod-img'])[1]"))
                .click();

        // and verify that the product name on the opened page
        // contains the word "phone" (case-insensitive)

        String expectedNameContent = "phone";
        String actualName = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
                .getText()
                .toLowerCase(); // Make it lowercase for case-insensitive comparison

        Assert.assertTrue(actualName.contains(expectedNameContent));
    }
}