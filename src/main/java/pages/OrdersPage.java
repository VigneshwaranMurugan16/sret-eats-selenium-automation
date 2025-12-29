package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrdersPage extends BasePage {

    // Locators
    @FindBy(className = "order")
    private List<WebElement> orders;

    @FindBy(xpath = "//h2[contains(text(),'Your Orders')]")
    private WebElement ordersHeader;

    @FindBy(className = "order-item")
    private List<WebElement> orderItems;

    @FindBy(className = "total-cost")
    private List<WebElement> totalCostElements;

    @FindBy(className = "review-button")
    private WebElement reviewButton;

    // Constructor
    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public boolean isOrdersPageDisplayed() {
        return isElementDisplayed(ordersHeader);
    }

    public int getOrdersCount() {
        return orders.size();
    }

    public int getTotalOrderItemsCount() {
        return orderItems.size();
    }

    public boolean isOrderItemDisplayed(String itemName) {
        for (WebElement orderItem : orderItems) {
            try {
                WebElement nameElement = orderItem.findElement(By.className("item-name"));
                if (nameElement.getText().equalsIgnoreCase(itemName)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public String getFirstOrderTotalCost() {
        if (!totalCostElements.isEmpty()) {
            return getElementText(totalCostElements.get(0));
        }
        return "";
    }

    public boolean isReviewButtonDisplayed() {
        return isElementDisplayed(reviewButton);
    }

    public String getOrdersHeaderText() {
        return getElementText(ordersHeader);
    }
}
