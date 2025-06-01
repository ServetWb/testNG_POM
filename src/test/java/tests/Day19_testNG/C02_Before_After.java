package tests.Day19_testNG;

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
            // Perform necessary setup
            driver = new ChromeDriver(); // Create a Chrome browser instance
            driver.manage().window().maximize(); // Maximize the browser window
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait time to 10 seconds
        }

        @AfterMethod
        public void teardown() {
            // Close the browser
            driver.quit(); // Quits the driver, closing all associated windows
        }

        @Test
        public void searchTest() {
            // Go to the homepage of testotomasyonu
            driver.get("https://www.testotomasyonu.com");

            // Search for "phone"
            WebElement searchBox = driver.findElement(By.id("global-search")); // Locate the search input box
            searchBox.sendKeys("phone" + Keys.ENTER); // Type "phone" and press ENTER

            // Verify that at least one product is found in the search results
            WebElement resultTextElement = driver.findElement(By.className("product-count-text")); //Locate the result summary text

            String unexpectedResultText = "0 Products Found"; // Define the undesired result text
            String actualResultText = resultTextElement.getText(); // Get the actual result text from the element

            Assert.assertNotEquals(actualResultText, unexpectedResultText);
            // Assert that the result does not show "0 Products Found"
            // i.e., confirm that at least one product was found
        }
    }
