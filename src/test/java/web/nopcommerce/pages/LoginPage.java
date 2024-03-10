package web.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumDrivers.WebElementActions;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailInput;
    @FindBy(xpath = "//span[@id='Email-error']")
    WebElement emailInputError;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='RememberMe']")
    private WebElement rememberMeCheckBox;
    @FindBy(xpath = "//a[contains(text(),'Forgot password?')]")
    private WebElement forgotPasswordLink;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    private WebElement errorMessage;

    /**
     * Login Page Constructor
     * @param driver
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * To click on the Login Button
     */
    public void clickLoginButton() {
        WebElementActions.clickOnElement(loginButton, "Click on Login Button from Login Page");
    }

    /**
     * To check empty Email Field Error message
     * @return
     */
    public boolean checkEmailInputError() { return WebElementActions.matchTextOfElement(emailInputError, "Please enter your email"); }

    /**
     * To check Wrong Email Field Error message
     * @return
     */
    public boolean checkWrongEmailError() { return WebElementActions.matchTextOfElement(emailInputError, "Wrong email"); }

    /**
     * To check general Error message for Unsuccessful Login
     * @return
     */
    public boolean checkLoginError() { return WebElementActions.matchTextOfElement(errorMessage, "Login was unsuccessful. Please correct the errors and try again."); }

    /**
     * To check incorrect credentials Error message
     * @return
     */
    public boolean checkIncorrectCredentialsError() {
        List<WebElement> elements = errorMessage.findElements(By.xpath("./child::*"));
        for (WebElement element : elements) {
            if (WebElementActions.matchTextOfElement(element, "The credentials provided are incorrect"))
                return true;
        }
        return false;
    }

    /**
     * To check No Customer found Error message
     * @return
     */
    public boolean checkNoCustomerError() {
        List<WebElement> elements = errorMessage.findElements(By.xpath("./child::*"));
        for (WebElement element : elements) {
            if (WebElementActions.matchTextOfElement(element, "No customer account found"))
                return true;
        }
        return false;
    }

    /**
     * To add input to the Email field
     * @param email
     */
    public void addEmailInput(String email) {
        WebElementActions.enterInputToTextField(emailInput, email, "Enter input to Email field");
    }

    /**
     * To add input to the Password field
     * @param password
     */
    public void addPasswordInput(String password) {
        WebElementActions.enterInputToTextField(passwordInput, password, "Enter input to Password field");
    }


    /*public void recoverCredentails(String email) {
        WebElementActions.clickOnElement(forgotPasswordLink, "Click on Forgot Password link from Login Page");
        WebElementActions.enterInputIntoTextField(emailTxt, email, "Enter recovery email in login page");
        WebElementActions.clickOnElement(recorverBtn, "click on recover button in login page");
    }*/



}
