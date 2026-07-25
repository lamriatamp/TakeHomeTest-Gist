package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import pages.LoginPage;
import pages.GistPage;

public class DeleteGistTest extends BaseTest {

    @Test
    public void deleteExistingGist() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("GITHUB_USERNAME"), ConfigReader.get("GITHUB_PASSWORD"));
        System.out.println("Step: Login berhasil");

        GistPage gistPage = new GistPage(driver);
        gistPage.deleteLatestGist("QA.java", ConfigReader.get("GITHUB_USERNAME"));
        System.out.println("Step: Gist berhasil dihapus");


        By gistLocator = By.xpath("//strong[@class='css-truncate-target' and text()='TestGist.java']");


        if (!driver.findElements(gistLocator).isEmpty()) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
        }

        Assert.assertTrue(driver.findElements(gistLocator).isEmpty(),
                "Gist 'QA.java' masih ada, delete gagal");
    }
}