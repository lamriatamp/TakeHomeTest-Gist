package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import pages.LoginPage;
import pages.GistListPage;

public class ViewGistTest extends BaseTest {

    @Test
    public void viewListOfGists() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("GITHUB_USERNAME"), ConfigReader.get("GITHUB_PASSWORD"));
        System.out.println("Step: Login berhasil");

        // View gist list
        GistListPage gistListPage = new GistListPage(driver);
        String username = ConfigReader.get("GITHUB_USERNAME");
        boolean gistFound = gistListPage.gistExists(username, "QA.java");
        System.out.println("Step: Halaman gist user terbuka");

        // Assertion
        Assert.assertTrue(gistFound, "Gist 'QA.java' tidak ditemukan di halaman user");
    }
}