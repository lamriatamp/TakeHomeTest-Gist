package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.GistPage;
import utils.ConfigReader;

public class CreateGistTest extends BaseTest {

    @Test
    public void createPublicGist() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("GITHUB_USERNAME"), ConfigReader.get("GITHUB_PASSWORD"));
        System.out.println("Step: Login berhasil dilakukan");

        GistPage gistPage = new GistPage(driver);
        gistPage.createGist("QA.java", "System.out.println(\"Halo\");");
        System.out.println("Step: Gist berhasil dibuat");

        // Assertion
        Assert.assertTrue(driver.getPageSource().contains("QA.java"),
                "Gist tidak ditemukan di halaman setelah create");
    }
}