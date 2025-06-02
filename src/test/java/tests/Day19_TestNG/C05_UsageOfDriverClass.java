package tests.Day19_TestNG;


import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class C05_UsageOfDriverClass {

        /*
        TestNG uses the Page Object Model (POM)

        Our main goal:
        There should be no static (hardcoded) data inside the test class
        (So that when a value changes, there is no need to come back and modify the test class)
     */

    @Test
    public void searchTest() throws InterruptedException {

        // Navigate to the website using the Driver utility
        Driver.getDriver().get("https://www.testotomasyonu.com");
        Thread.sleep(2000); // Wait for 2 seconds

        // Locate the search input box using its ID and assign it to 'searchBox'
        WebElement searchBox = Driver.getDriver().findElement(By.id("global-search"));

        // Enter the word "phone" and simulate pressing the Enter key
        searchBox.sendKeys("phone" + Keys.ENTER);
        Thread.sleep(2000); // Wait for the results to load

        // Locate the element showing the number of products found
        WebElement resultTextElement = Driver.getDriver().findElement(By.className("product-count-text"));

        // Define the result text we do NOT expect to see
        String unexpectedResultText = "0 Products Found";

        // Get the actual result text from the webpage
        String actualResultText = resultTextElement.getText();

        // Assert that the actual result is not equal to the unexpected result
        Assert.assertNotEquals(actualResultText, unexpectedResultText);

        // Close the browser and end the session
        Driver.quitDriver();
    }

}
