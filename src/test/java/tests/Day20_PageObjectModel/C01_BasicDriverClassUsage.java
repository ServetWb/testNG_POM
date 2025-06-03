package tests.Day20_PageObjectModel;


import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.Driver;

public class C01_BasicDriverClassUsage {

    @Test
    public void testAutomationTest() {

        // Go to TestOtomasyonu homepage
        Driver.getDriver().get("https://www.testotomasyonu.com");

        // Verify that the URL contains "testotomasyonu"
        String expectedUrlContent = "testotomasyonu";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));

        // Close the browser
        Driver.quitDriver();
    }

    @Test
    public void wiseQuarterTest() {

        // Go to WiseQuarter homepage
        Driver.getDriver().get("https://www.wisequarter.com");

        // Verify that the URL contains "wisequarter"
        String expectedUrlContent = "wisequarter";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));

        // Close the browser
        Driver.quitDriver();
    }

    @Test
    public void youtubeTest() {

        // Go to YouTube homepage
        Driver.getDriver().get("https://www.youtube.com");

        // Verify that the URL contains "youtube"
        String expectedUrlContent = "youtube";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent));

        // Close the browser
        Driver.quitDriver();

    }
}