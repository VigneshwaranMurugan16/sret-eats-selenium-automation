package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Locators using @FindBy annotation
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit' or contains(text(),'Login') or contains(text(),'Sign In')]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Sign Up') or contains(text(),'Register')]")
    private WebElement signUpLink;

    @FindBy(xpath = "//div[contains(@class,'error') or contains(@class,'alert')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(text(),'Forgot Password')]")
    private WebElement forgotPasswordLink;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions/Methods
    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    // Combined login method
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickSignUpLink() {
        clickElement(signUpLink);
    }

    public void clickForgotPassword() {
        clickElement(forgotPasswordLink);
    }

    public String getErrorMessage() {
        waitForVisibility(errorMessage);
        return getElementText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public String getLoginPageTitle() {
        return getPageTitle();
    }
}
