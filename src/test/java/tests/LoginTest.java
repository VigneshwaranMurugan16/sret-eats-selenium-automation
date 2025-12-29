package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login page is accessible")
    public void testLoginPageLoad() {
        LoginPage loginPage = new LoginPage(driver);

        // Verify login button is displayed
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button should be displayed on login page");

        System.out.println("✓ Login page loaded successfully");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
    }

    @Test(priority = 2, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.login(ConfigReader.getProperty("testUserEmail"),
                ConfigReader.getProperty("testUserPassword"));

        // Wait for navigation
        try {
            Thread.sleep(3000); // Wait for login API call and navigation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify redirect away from login page
        String currentUrl = driver.getCurrentUrl();
        System.out.println("After login URL: " + currentUrl);

        // Your app redirects to root or menu after login
        Assert.assertFalse(currentUrl.contains("login") && !currentUrl.equals("http://localhost:3001/"),
                "User should be redirected away from login page after successful login");

        System.out.println("✓ Valid login test passed");
    }

    @Test(priority = 3, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Attempt login with wrong credentials
        loginPage.login("wrong@email.com", "wrongpass");

        // Wait for error or page behavior
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify we're still on login page (authentication should fail)
        String currentUrl = driver.getCurrentUrl();
        System.out.println("After invalid login URL: " + currentUrl);

        // Should stay on login page OR show error
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "User should remain on login page after invalid credentials");

        System.out.println("✓ Invalid login test passed");
    }

    @Test(priority = 4, description = "Verify empty email validation")
    public void testEmptyEmailLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Try to login with empty email
        loginPage.enterPassword(ConfigReader.getProperty("testUserPassword"));
        loginPage.clickLoginButton();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Should stay on login page
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login should not proceed with empty email");

        System.out.println("✓ Empty email validation test passed");
    }
}
