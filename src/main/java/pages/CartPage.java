package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    // Locators
    @FindBy(xpath = "//div[@class='cart-items']//li")
    private List<WebElement> cartItems;

    @FindBy(className = "checkout-button")
    private WebElement placeOrderButton;

    @FindBy(className = "total")
    private WebElement totalPriceElement;

    @FindBy(className = "remove-icon")
    private List<WebElement> removeButtons;

    @FindBy(className = "item-name")
    private List<WebElement> itemNames;

    @FindBy(xpath = "//h2[contains(text(),'Your Cart')]")
    private WebElement cartHeader;

    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getCartItemsCount() {
        return cartItems.size();
    }

    public boolean isCartHeaderDisplayed() {
        return isElementDisplayed(cartHeader);
    }

    public void clickPlaceOrder() {
        scrollToElement(placeOrderButton);
        clickElement(placeOrderButton);
        System.out.println("✓ Clicked 'Place Order' button");
    }

    public String getTotalPrice() {
        return getElementText(totalPriceElement);
    }

    public boolean isItemInCart(String itemName) {
        for (WebElement nameElement : itemNames) {
            if (nameElement.getText().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeFirstItem() {
        if (!removeButtons.isEmpty()) {
            clickElement(removeButtons.get(0));
            System.out.println("✓ Removed first item from cart");
        }
    }

    public boolean isPlaceOrderButtonDisplayed() {
        return isElementDisplayed(placeOrderButton);
    }

    public List<String> getAllItemNames() {
        java.util.ArrayList<String> names = new java.util.ArrayList<>();
        for (WebElement nameElement : itemNames) {
            names.add(nameElement.getText());
        }
        return names;
    }
}
