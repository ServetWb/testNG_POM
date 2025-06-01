package tests.Day19_TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_Before_After {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Make necessary settings before each test method
        driver = new ChromeDriver(); // Start a new Chrome browser session
        driver.manage().window().maximize(); // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait to 10 seconds
    }

    @AfterMethod
    public void teardown() {
        // Close the browser after each test method
        driver.quit();
    }

    @Test
    public void searchTest() {
        // Go to the Test Otomasyonu homepage
        driver.get("https://www.testotomasyonu.com");

        // Search for "phone" using the search box
        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that some products are found in the search results
        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found"; // What we do NOT want to see
        String actualResultText = resultTextElement.getText(); // What we actually see

        // Make sure the result is NOT "0 Products Found"
        Assert.assertNotEquals(actualResultText, unexpectedResultText);
    }
}