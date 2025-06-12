package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReusableMethods {

    public static void wait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
// Create a method that takes a list of WebElements,
// iterates through each WebElement,
// extracts the text from each element,
// adds it to a new list,
// and finally returns a list of Strings after processing all elements.

    public static List<String> convertToStringList(List<WebElement> webElementList) {

        List<String> stringList = new ArrayList<>();

        for (WebElement eachElement : webElementList) {
            stringList.add(eachElement.getText());
        }

        return stringList;


    }


    public static void switchToTargetUrlWindow(WebDriver driver, String targetUrl) {
        // Get the set of all currently open window handles
        Set<String> allOpenWindowHandles = driver.getWindowHandles();
        // Iterate through each window handle
        for (String eachHandle : allOpenWindowHandles) {
            // Switch to the current window
            driver.switchTo().window(eachHandle);
            // Get the current URL of the active window
            String currentUrl = driver.getCurrentUrl();
            // If the current URL matches the target URL, stop the loop
            if (targetUrl.equals(currentUrl)) {
                break;
            }
        }
    }

    public static void titleWindowSwitch(WebDriver driver, String targetTitle) {
        // Get the set of all currently open window handles
        Set<String> allOpenWindowHandles = driver.getWindowHandles();

        // Iterate through each window handle
        for (String eachHandle : allOpenWindowHandles) {
            // Switch to the current window using its handle
            driver.switchTo().window(eachHandle);

            // Get the title of the currently active window
            String currentTitle = driver.getTitle();

            // If the current title matches the target title, exit the loop
            if (targetTitle.equals(currentTitle)) {
                break;
            }
        }
    }

    public static void captureFullPageScreenshot(WebDriver driver) {
        // Step 1: Create a TakesScreenshot object from the WebDriver
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Step 2: Create the file where the screenshot will be permanently saved
        File finalScreenshotFile = new File("target/screenshots/fullPageScreenshot.jpg");

        // Step 3: Take the screenshot and store it in a temporary file
        File tempScreenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Step 4: Copy the temporary file to the final destination file
        try {
            FileUtils.copyFile(tempScreenshot, finalScreenshotFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the screenshot", e);

        }
    }

    public static void takeFullPageScreenshotWithName(WebDriver driver, String reportName) {

        // Wait for half a second to ensure the page is fully loaded
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Step 1: Create a TakesScreenshot object from the WebDriver
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Step 2: Define the file where the screenshot will be saved
        File finalScreenshotFile = new File("target/screenshots/" + reportName + ".jpg");

        // Step 3: Take the screenshot and save it as a temporary file
        File tempScreenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Step 4: Copy the temporary screenshot file to the final destination
        try {
            FileUtils.copyFile(tempScreenshot, finalScreenshotFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot with report name: " + reportName, e);
        }
    }

    public static void takeTimestampedFullPageScreenshot(WebDriver driver) {

        // Wait briefly to ensure the page has fully loaded before capturing the screenshot
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // To prevent screenshots from being overwritten with the same filename,
        // we append a timestamp to the screenshot file name.
        // Example: fullPageScreenshot_290525_190923.jpg

        // Step 1: Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Step 2: Format the timestamp to match the desired pattern: _ddMMyy_HHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_ddMMyy_HHmmss");
        String timestamp = currentDateTime.format(formatter);

        // Step 3: Create a TakesScreenshot object from the WebDriver
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Step 4: Define the file where the screenshot will be saved
        File finalScreenshotFile = new File("target/screenshots/fullPageScreenshot" + timestamp + ".jpg");

        // Step 5: Take the screenshot and save it as a temporary file
        File temporaryScreenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Step 6: Copy the temporary screenshot file to the final destination
        try {
            FileUtils.copyFile(temporaryScreenshot, finalScreenshotFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the screenshot with timestamp", e);
        }
    }

    public static void takeTimestampedScreenshotWithName(WebDriver driver, String reportName) {

        // Wait briefly to ensure the page is fully loaded before taking the screenshot
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Step 1: Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Step 2: Define the desired timestamp format: _ddMMyy_HHmmss (e.g., _290525_190923)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_ddMMyy_HHmmss");

        // Step 3: Format the current date-time using the formatter
        String timestamp = currentDateTime.format(formatter);

        // Step 4: Create a TakesScreenshot object from the WebDriver
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Step 5: Define the final file path where the screenshot will be saved
        File finalScreenshotFile = new File("target/screenshots/" + reportName + timestamp + ".jpg");

        // Step 6: Take the screenshot and save it temporarily
        File tempScreenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Step 7: Copy the temporary screenshot file to the final destination
        try {
            FileUtils.copyFile(tempScreenshot, finalScreenshotFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + reportName + timestamp, e);
        }
    }

    public static void takeElementScreenshot(WebElement targetElement) {

        // Step 1: Locate the WebElement you want to capture
        // In this case, we assume the element is already located and passed as a parameter

        // Step 2: Define the file path where the screenshot will be saved
        String filePath = "target/screenshots/targetWebElement.jpg";
        File finalImage = new File(filePath);

        // Step 3: Use the WebElement to capture the screenshot
        // and store it as a temporary file
        File temporaryImage = targetElement.getScreenshotAs(OutputType.FILE);

        // Step 4: Copy the temporary file to the final destination
        try {
            FileUtils.copyFile(temporaryImage, finalImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save WebElement screenshot", e);
        }
    }

    public static void takeElementScreenshotWithName(WebElement targetElement, String imageName) {

        // Step 1: Locate the WebElement you want to capture
        // In this method, we assume the element is already located and passed as a parameter

        // Step 2: Define the file path where the screenshot will be saved
        String filePath = "target/screenshots/" + imageName + ".jpg";
        File finalImage = new File(filePath);

        // Step 3: Use the WebElement to take a screenshot
        // and store it temporarily
        File temporaryImage = targetElement.getScreenshotAs(OutputType.FILE);

        // Step 4: Copy the temporary file to the final destination
        try {
            FileUtils.copyFile(temporaryImage, finalImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save element screenshot: " + imageName, e);
        }
    }

    public static void takeTimestampedElementScreenshot(WebElement targetElement) {

        // Step 1: Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Step 2: Define the desired format: _ddMMyy_HHmmss (e.g., _290525_190923)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_ddMMyy_HHmmss");

        // Step 3: Format the current timestamp
        String timestamp = currentDateTime.format(formatter);

        // Step 4: Define the file path where the screenshot will be saved
        String filePath = "target/screenshots/targetWebElement" + timestamp + ".jpg";
        File finalImage = new File(filePath);

        // Step 5: Take the screenshot of the WebElement and store it temporarily
        File temporaryImage = targetElement.getScreenshotAs(OutputType.FILE);

        // Step 6: Copy the temporary screenshot to the final file
        try {
            FileUtils.copyFile(temporaryImage, finalImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save timestamped element screenshot", e);
        }
    }

    public static void takeTimestampedElementScreenshotWithName(WebElement targetElement, String imageName) {

        // Step 1: Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Step 2: Define the desired format for the timestamp (e.g., _290525_190923)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_ddMMyy_HHmmss");

        // Step 3: Format the timestamp string
        String timestamp = currentDateTime.format(formatter);

        // Step 4: Define the file path where the screenshot will be saved
        String filePath = "target/screenshots/" + imageName + timestamp + ".jpg";
        File finalImage = new File(filePath);

        // Step 5: Take the screenshot using the WebElement and save it temporarily
        File temporaryImage = targetElement.getScreenshotAs(OutputType.FILE);

        // Step 6: Copy the temporary screenshot file to the final destination
        try {
            FileUtils.copyFile(temporaryImage, finalImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the timestamped element screenshot: " + imageName, e);
        }
    }

    public static String addScreenshotToReport (String testName) throws IOException{
        // Get current timestamp for unique filename
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyMMdd_HHmmss");
        String date = localDateTime.format(formatter); // e.g. _241219_080623

        // Step 1: Create TakesScreenshot object and capture temporary screenshot
        TakesScreenshot screenshotTaker = (TakesScreenshot) Driver.getDriver();
        File tempFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Step 2: Define path and create destination file for saving the actual screenshot
        String filePath = System.getProperty("user.dir") + "/test-output/Screenshots/" + testName + date + ".jpg";
        File finalScreenshotFile = new File(filePath);

        // Step 3: Copy temporary screenshot to final destination
        FileUtils.copyFile(tempFile, finalScreenshotFile);

        return filePath; // Return the path of the saved screenshot
    }

}
