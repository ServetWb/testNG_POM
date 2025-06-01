package tests.Day19_TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_TestNG_First_Test {
    @Test
    public void searchTest() {
        // Make necessary settings
        // Create a new Chrome browser instance
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set implicit wait timeout to 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the Test Otomasyonu homepage
        driver.get("https://www.testotomasyonu.com");

        // Search for "phone" using the search box
        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that products are found in the search results
        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        // Define the unexpected result text
        String unexpectedResultText = "0 Products Found";

        // Get the actual text from the result element
        String actualResultText = resultTextElement.getText();

        // Assert that the actual result text is NOT equal to the unexpected one
        Assert.assertNotEquals(actualResultText, unexpectedResultText);

        // Close the browser
        driver.quit();
    }
}