package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import pages.LoginPage;
import pages.GistPage;

public class EditGistTest extends BaseTest {

    @Test
    public void editExistingGist() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("GITHUB_USERNAME"), ConfigReader.get("GITHUB_PASSWORD"));
        System.out.println("Step: Login berhasil");

        // Edit gist langsung dari list
        GistPage gistPage = new GistPage(driver);
        gistPage.editLatestGist("QA.java", "System.out.println(\"Edited\");", ConfigReader.get("GITHUB_USERNAME"));
        System.out.println("Step: Gist berhasil diedit");

        // Assertion
        Assert.assertTrue(driver.getPageSource().contains("Edited"),
                "Gist tidak berhasil diupdate dengan konten baru");
    }
}