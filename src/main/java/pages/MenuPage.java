package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPage extends BasePage {

    // Locators
    @FindBy(xpath = "//div[contains(@class,'food-item') or contains(@class,'menu-item')]")
    private List<WebElement> foodItems;

    @FindBy(xpath = "//select[@id='category' or contains(@name,'category')]")
    private WebElement categoryFilter;

    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//input[@type='search' or @placeholder='Search food']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[contains(text(),'Veg') or @id='veg-filter']")
    private WebElement vegFilterButton;

    @FindBy(xpath = "//button[contains(text(),'Non-Veg') or @id='non-veg-filter']")
    private WebElement nonVegFilterButton;

    @FindBy(xpath = "//select[contains(@name,'sort') or @id='sort']")
    private WebElement sortDropdown;

    // Constructor
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getFoodItemsCount() {
        return foodItems.size();
    }

    public void searchFood(String foodName) {
        enterText(searchBox, foodName);
    }

    public void clickVegFilter() {
        clickElement(vegFilterButton);
    }

    public void clickNonVegFilter() {
        clickElement(nonVegFilterButton);
    }

    public void addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            clickElement(addToCartButtons.get(0));
        }
    }

    public void addItemToCartByName(String itemName) {
        // Dynamic XPath to find specific item and click its Add to Cart button
        String xpath = "//div[contains(text(),'" + itemName + "')]/ancestor::div[contains(@class,'item')]//button[contains(text(),'Add to Cart')]";
        WebElement addButton = driver.findElement(By.xpath(xpath));
        clickElement(addButton);
    }

    public boolean isFoodItemDisplayed(String itemName) {
        try {
            WebElement item = driver.findElement(By.xpath("//div[contains(text(),'" + itemName + "')]"));
            return item.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getAllFoodItems() {
        return foodItems;
    }
}
