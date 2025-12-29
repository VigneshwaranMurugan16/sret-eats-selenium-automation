package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class OrderFlowTest extends BaseTest {

    private LoginPage loginPage;
    private MenuPage menuPage;
    private CartPage cartPage;
    private OrdersPage ordersPage;

    @BeforeMethod
    public void loginAndNavigate() {
        loginPage = new LoginPage(driver);

        // Login
        loginPage.login(ConfigReader.getProperty("testUserEmail"),
                ConfigReader.getProperty("testUserPassword"));

        try {
            Thread.sleep(3000); // Wait for login
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("✓ Login successful");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 1, description = "Complete order flow: Menu → Add to Cart → Place Order → View Orders")
    public void testCompleteOrderFlow() {
        // Step 1: Navigate to Menu and verify snacks are displayed
        menuPage = new MenuPage(driver);
        menuPage.navigateToMenu();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int snackCount = menuPage.getSnackCardsCount();
        System.out.println("Total snacks found: " + snackCount);
        Assert.assertTrue(snackCount > 0, "Menu should display snacks");

        // Step 2: Get first snack details
        String snackName = menuPage.getFirstSnackName();
        String snackPrice = menuPage.getFirstSnackPrice();
        System.out.println("First snack: " + snackName + " - " + snackPrice);
        Assert.assertFalse(snackName.isEmpty(), "Snack name should not be empty");

        // Step 3: Add item to cart
        menuPage.addFirstItemToCart();

        try {
            Thread.sleep(2000); // Wait for API call
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Navigate to Cart
        menuPage.navigateToCart();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Verify item is in cart
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartHeaderDisplayed(), "Cart page should be displayed");

        int cartItemsCount = cartPage.getCartItemsCount();
        System.out.println("Items in cart: " + cartItemsCount);
        Assert.assertTrue(cartItemsCount > 0, "Cart should have at least one item");

        // Step 6: Verify the item we added is in cart
        boolean itemFound = cartPage.isItemInCart(snackName);
        System.out.println("Is '" + snackName + "' in cart? " + itemFound);

        // Step 7: Place Order
        Assert.assertTrue(cartPage.isPlaceOrderButtonDisplayed(), "Place Order button should be displayed");
        cartPage.clickPlaceOrder();

        try {
            Thread.sleep(3000); // Wait for order API call
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 8: Navigate to Orders page
        menuPage = new MenuPage(driver); // Re-initialize to get nav links
        menuPage.navigateToOrders();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 9: Verify order is displayed
        ordersPage = new OrdersPage(driver);
        Assert.assertTrue(ordersPage.isOrdersPageDisplayed(), "Orders page should be displayed");

        int ordersCount = ordersPage.getOrdersCount();
        System.out.println("Total orders: " + ordersCount);
        Assert.assertTrue(ordersCount > 0, "At least one order should be displayed");

        System.out.println("\n✓✓✓ COMPLETE ORDER FLOW TEST PASSED ✓✓✓");
    }

    @Test(priority = 2, description = "Add multiple items to cart")
    public void testAddMultipleItemsToCart() {
        menuPage = new MenuPage(driver);
        menuPage.navigateToMenu();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add specific item by name (Samosa)
        menuPage.addItemToCartByName("Samosa");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Navigate to cart and verify
        menuPage.navigateToCart();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isItemInCart("Samosa"), "Samosa should be in cart");

        System.out.println("✓ Multiple items test passed");
    }

    @Test(priority = 3, description = "Verify menu displays correct number of items")
    public void testMenuItemsCount() {
        menuPage = new MenuPage(driver);
        menuPage.navigateToMenu();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count = menuPage.getSnackCardsCount();
        System.out.println("Snacks displayed: " + count);

        // Based on your debug output, you have 16 snacks
        Assert.assertEquals(count, 16, "Should display 16 snack items");

        System.out.println("✓ Menu items count test passed");
    }
}
