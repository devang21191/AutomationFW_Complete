package web.nopcommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumDrivers.WebElementActions;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement loginLink;

    /**
     * HomePage Constructor
     * @param driver
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * To click on Register Link from HomePage
     */
    public void clickRegisterLink() {
        WebElementActions.clickOnElement(registerLink, "Click on Register link from Home Page");
    }

    /**
     * To click on Login Link from HomePage
     */
    public void clickLoginLink() {
        WebElementActions.clickOnElement(loginLink, "Click on Login link from Home Page");
    }
}
