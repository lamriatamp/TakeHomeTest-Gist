package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class GistPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public GistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void createGist(String filename, String content) {
        driver.get("https://gist.github.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gist[contents][][name]")))
                .sendKeys(filename);

        By editorLocator = By.xpath("//div[contains(@class,'CodeMirror')]");
        wait.until(ExpectedConditions.elementToBeClickable(editorLocator)).click();
        driver.switchTo().activeElement().sendKeys(content);

        By createButton = By.xpath("//button[normalize-space()='Create secret gist']");
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void editLatestGist(String filename, String newContent, String username) {
        driver.get("https://gist.github.com/" + username);

        By gistLink = By.xpath("//strong[@class='css-truncate-target' and text()='" + filename + "']");
        wait.until(ExpectedConditions.elementToBeClickable(gistLink)).click();

        By editButton = By.xpath("//ul[@class='d-md-flex d-none pagehead-actions float-none']//a[@class='Button--secondary Button--small Button']//span[@class='Button-content']");
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

        By editorLocator = By.xpath("//div[contains(@class,'CodeMirror')]");
        wait.until(ExpectedConditions.elementToBeClickable(editorLocator)).click();
        driver.switchTo().activeElement().clear();
        driver.switchTo().activeElement().sendKeys(newContent);

        By updateButton = By.xpath("//button[normalize-space()='Update secret gist']");
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    public void deleteLatestGist(String filename, String username) {
        driver.get("https://gist.github.com/" + username);

        By gistLink = By.xpath("//strong[@class='css-truncate-target' and text()='" + filename + "']");
        wait.until(ExpectedConditions.elementToBeClickable(gistLink)).click();

        By deleteButton = By.xpath("//button[@class='Button--danger Button--small Button']");
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();


        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        alertWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();


        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        driver.get("https://gist.github.com/" + username);


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
    }
}