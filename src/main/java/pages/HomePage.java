package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // Locators
    @FindBy(xpath = "//a[contains(text(),'Menu') or contains(@href,'menu')]")
    private WebElement menuLink;

    @FindBy(xpath = "//a[contains(text(),'Cart') or contains(@class,'cart')]")
    private WebElement cartIcon;

    @FindBy(xpath = "//button[contains(text(),'Logout') or contains(text(),'Sign Out')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@type='search' or @placeholder='Search']")
    private WebElement searchBox;

    @FindBy(xpath = "//span[contains(@class,'user') or contains(@class,'profile')]")
    private WebElement userProfileIcon;

    @FindBy(xpath = "//h1 | //h2[contains(text(),'Welcome')]")
    private WebElement welcomeMessage;

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void clickMenuLink() {
        clickElement(menuLink);
    }

    public void clickCartIcon() {
        clickElement(cartIcon);
    }

    public void clickLogout() {
        clickElement(logoutButton);
    }

    public void searchItem(String itemName) {
        enterText(searchBox, itemName);
    }

    public void clickUserProfile() {
        clickElement(userProfileIcon);
    }

    public String getWelcomeMessage() {
        return getElementText(welcomeMessage);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(logoutButton);
    }

    public boolean isCartIconDisplayed() {
        return isElementDisplayed(cartIcon);
    }

    public String getHomePageUrl() {
        return getCurrentUrl();
    }
}
