package tests.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_TestNG_FirstTest {


    @Test
    public void searchTest() {
        // Set up necessary configurations
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Go to the homepage of testotomasyonu
        driver.get("https://www.testotomasyonu.com");

        // Search for "phone"
        WebElement searchBox = driver.findElement(By.id("global-search"));
        searchBox.sendKeys("phone" + Keys.ENTER);

        // Verify that products are found in the search results
        WebElement resultTextElement = driver.findElement(By.className("product-count-text"));

        String unexpectedResultText = "0 Products Found";
        String actualResultText = resultTextElement.getText();

        Assert.assertNotEquals(actualResultText, unexpectedResultText);

        // Close the browser
        driver.quit();
    }
}