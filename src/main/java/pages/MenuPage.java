package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPage extends BasePage {

    // Locators
    @FindBy(className = "snack-card")
    private List<WebElement> snackCards;

    @FindBy(className = "cart-button")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "fav-button")
    private List<WebElement> addToFavButtons;

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    private WebElement cartNavLink;

    @FindBy(xpath = "//a[contains(text(),'Menu')]")
    private WebElement menuNavLink;

    @FindBy(xpath = "//a[contains(text(),'Orders')]")
    private WebElement ordersNavLink;

    // Constructor
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getSnackCardsCount() {
        return snackCards.size();
    }

    public String getFirstSnackName() {
        if (!snackCards.isEmpty()) {
            WebElement firstCard = snackCards.get(0);
            WebElement nameElement = firstCard.findElement(By.className("snack-name"));
            return nameElement.getText();
        }
        return "";
    }

    public String getFirstSnackPrice() {
        if (!snackCards.isEmpty()) {
            WebElement firstCard = snackCards.get(0);
            WebElement priceElement = firstCard.findElement(By.className("PriceName"));
            return priceElement.getText();
        }
        return "";
    }

    public void addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            scrollToElement(addToCartButtons.get(0));
            clickElement(addToCartButtons.get(0));
            System.out.println("✓ Clicked 'Add to Cart' on first item");
        }
    }

    public void addItemToCartByName(String itemName) {
        for (WebElement card : snackCards) {
            try {
                WebElement nameElement = card.findElement(By.className("snack-name"));
                if (nameElement.getText().equalsIgnoreCase(itemName)) {
                    WebElement cartButton = card.findElement(By.className("cart-button"));
                    scrollToElement(cartButton);
                    clickElement(cartButton);
                    System.out.println("✓ Added '" + itemName + "' to cart");
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("⚠ Item '" + itemName + "' not found");
    }

    public void navigateToCart() {
        clickElement(cartNavLink);
        System.out.println("✓ Navigated to Cart");
    }

    public void navigateToMenu() {
        clickElement(menuNavLink);
        System.out.println("✓ Navigated to Menu");
    }

    public void navigateToOrders() {
        clickElement(ordersNavLink);
        System.out.println("✓ Navigated to Orders");
    }

    public boolean isSnackDisplayed(String snackName) {
        for (WebElement card : snackCards) {
            try {
                WebElement nameElement = card.findElement(By.className("snack-name"));
                if (nameElement.getText().equalsIgnoreCase(snackName)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }
}
