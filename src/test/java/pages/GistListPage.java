package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class GistListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public GistListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean hasGists(String username) {
        driver.get("https://gist.github.com/" + username);
        By gistLocator = By.cssSelector("strong.css-truncate-target");
        return !wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(gistLocator)).isEmpty();
    }

    public boolean gistExists(String username, String filename) {
        driver.get("https://gist.github.com/" + username);
        By gistLocator = By.xpath("//strong[@class='css-truncate-target' and text()='" + filename + "']");
        return !driver.findElements(gistLocator).isEmpty();
    }
}