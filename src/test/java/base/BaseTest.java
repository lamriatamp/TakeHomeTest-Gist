package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // ChromeOptions untuk headless mode di CI
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(screenshot.toPath(),
                            new File("screenshots/" + result.getName() + ".png").toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }
    }

    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void waitAndType(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
}