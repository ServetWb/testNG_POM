package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private Driver() {
        // There are several ways to prevent object creation from the Driver class
        // People who design using the Page Object Model (POM) prefer to use the Singleton Pattern
        // The Singleton Pattern is used to prevent object creation
        // This is done by making the constructor visible
        // but setting its access modifier to private
    }

    public static WebDriver driver;

    public static WebDriver getDriver() {

        String browserToUse = ConfigReader.getProperty("browser");
        // We get the browser preference from the configuration properties file

        // You can add a safety fallback here just in case the value is null
        if (browserToUse == null) {
            browserToUse = "chrome";
        }

        if (driver == null) {

            switch (browserToUse) {

                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        return driver;

    }
    public static void quitDriver(){
        driver.quit();
        driver=null;
    }
}
