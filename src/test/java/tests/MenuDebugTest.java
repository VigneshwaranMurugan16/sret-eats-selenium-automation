package tests;

import base.BaseTest;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class MenuDebugTest extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("testUserEmail"),
                ConfigReader.getProperty("testUserPassword"));

        try {
            Thread.sleep(3000); // Wait for login
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inspectMenuPage() {
        System.out.println("=== After Login ===");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Try to find navigation links
        System.out.println("\n=== Navigation Links ===");
        try {
            var navLinks = driver.findElements(By.cssSelector(".nav-links a"));
            System.out.println("Nav links found: " + navLinks.size());
            for (WebElement link : navLinks) {
                System.out.println("  Link text: " + link.getText());
            }
        } catch (Exception e) {
            System.out.println("Nav links not found: " + e.getMessage());
        }

        // Click Menu link if exists
        try {
            WebElement menuLink = driver.findElement(By.xpath("//a[contains(text(),'Menu')]"));
            menuLink.click();
            Thread.sleep(2000);
            System.out.println("\n=== After Clicking Menu ===");
            System.out.println("URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Menu link not found or already on menu page");
        }

        // Find snack cards
        System.out.println("\n=== Snack Cards ===");
        try {
            var snackCards = driver.findElements(By.className("snack-card"));
            System.out.println("Snack cards found: " + snackCards.size());

            if (snackCards.size() > 0) {
                WebElement firstCard = snackCards.get(0);
                System.out.println("\n=== First Snack Card Details ===");

                // Find snack name
                try {
                    WebElement nameElement = firstCard.findElement(By.className("snack-name"));
                    System.out.println("Snack name: " + nameElement.getText());
                } catch (Exception e) {
                    System.out.println("Name not found");
                }

                // Find price
                try {
                    WebElement priceElement = firstCard.findElement(By.className("PriceName"));
                    System.out.println("Price: " + priceElement.getText());
                } catch (Exception e) {
                    System.out.println("Price not found");
                }

                // Find Add to Cart button
                try {
                    WebElement cartButton = firstCard.findElement(By.className("cart-button"));
                    System.out.println("Cart button text: " + cartButton.getText());
                    System.out.println("Cart button displayed: " + cartButton.isDisplayed());
                } catch (Exception e) {
                    System.out.println("Cart button not found: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Snack cards not found: " + e.getMessage());
        }

        // Find cart icon in navbar
        System.out.println("\n=== Cart Navigation ===");
        try {
            WebElement cartLink = driver.findElement(By.xpath("//a[contains(text(),'Cart')]"));
            System.out.println("Cart link found: " + cartLink.getText());
        } catch (Exception e) {
            System.out.println("Cart link not found");
        }
    }
}
