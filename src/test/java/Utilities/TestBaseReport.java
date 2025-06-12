package Utilities;

import Utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TestBaseReport {

    protected static ExtentReports extentReports;              // Initializes ExtentReports
    protected static ExtentSparkReporter extentSparkReporter;  // Configures the HTML report
    protected static ExtentTest extentTest;                    // Logs test status (pass/fail) and screenshots

    /**
     * This method runs once before the entire test suite starts (not before each test method).
     * It initializes the reporting tools and sets basic metadata for the report.
     */
    @BeforeTest(alwaysRun = true)
    public void setUpReport() {
        extentReports = new ExtentReports();

        // Generate a timestamped report file name to avoid overwriting
        String date = new SimpleDateFormat("_yyMMdd_HHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Report" + date + ".html";

        // Initialize HTML reporter and attach it to ExtentReports
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);

        // Add system/environment information to the report
        extentReports.setSystemInfo("Environment", "Live");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));  // e.g., Chrome, Firefox
        extentReports.setSystemInfo("Automation Engineer", "Yusuf TONBUL");

        // Configure the appearance and title of the report
        extentSparkReporter.config().setDocumentTitle("TestNG Reports");
        extentSparkReporter.config().setReportName("HTML Test Report");
    }

    /**
     * This method runs after each test method.
     * If the test fails, it captures a screenshot and attaches it to the report.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ReusableMethods.addScreenshotToReport(result.getName());
            extentTest.fail(result.getName());
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.fail(result.getThrowable());  // Log the exception/error
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test Case Skipped: " + result.getName());
        }

        // Clean up WebDriver after each test
        Driver.quitDriver();
    }

    /**
     * This method runs once after the entire test suite finishes.
     * It finalizes and writes all information to the HTML report.
     */
    @AfterTest(alwaysRun = true)
    public void tearDownReport() {
        extentReports.flush();  // Write everything to the report file
    }
}