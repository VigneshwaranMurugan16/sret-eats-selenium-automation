package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    // Locators
    @FindBy(xpath = "//div[contains(@class,'cart-item')]")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[contains(text(),'Proceed to Checkout') or contains(text(),'Checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//span[contains(@class,'total') or contains(@class,'price-total')]")
    private WebElement totalPrice;

    @FindBy(xpath = "//button[contains(text(),'Remove') or contains(@class,'remove')]")
    private List<WebElement> removeButtons;

    @FindBy(xpath = "//button[contains(text(),'+') or contains(@class,'increment')]")
    private List<WebElement> incrementButtons;

    @FindBy(xpath = "//button[contains(text(),'-') or contains(@class,'decrement')]")
    private List<WebElement> decrementButtons;

    @FindBy(xpath = "//input[contains(@class,'promo') or @placeholder='Promo Code']")
    private WebElement promoCodeInput;

    @FindBy(xpath = "//button[contains(text(),'Apply') and contains(@class,'promo')]")
    private WebElement applyPromoButton;

    @FindBy(xpath = "//p[contains(text(),'empty') or contains(text(),'no items')]")
    private WebElement emptyCartMessage;

    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getCartItemsCount() {
        return cartItems.size();
    }

    public void clickCheckoutButton() {
        clickElement(checkoutButton);
    }

    public String getTotalPrice() {
        return getElementText(totalPrice);
    }

    public void removeFirstItem() {
        if (!removeButtons.isEmpty()) {
            clickElement(removeButtons.get(0));
        }
    }

    public void incrementFirstItemQuantity() {
        if (!incrementButtons.isEmpty()) {
            clickElement(incrementButtons.get(0));
        }
    }

    public void decrementFirstItemQuantity() {
        if (!decrementButtons.isEmpty()) {
            clickElement(decrementButtons.get(0));
        }
    }

    public void applyPromoCode(String promoCode) {
        enterText(promoCodeInput, promoCode);
        clickElement(applyPromoButton);
    }

    public boolean isCartEmpty() {
        return isElementDisplayed(emptyCartMessage);
    }

    public boolean isCheckoutButtonDisplayed() {
        return isElementDisplayed(checkoutButton);
    }

    public void removeItemByName(String itemName) {
        String xpath = "//div[contains(text(),'" + itemName + "')]/ancestor::div[contains(@class,'cart-item')]//button[contains(text(),'Remove')]";
        WebElement removeBtn = driver.findElement(By.xpath(xpath));
        clickElement(removeBtn);
    }
}
