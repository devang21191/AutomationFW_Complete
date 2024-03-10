package web.nopcommerce.testScripts;

import dataDrivers.ReadPropertyFile;
import org.testng.Assert;
import org.testng.annotations.*;
import web.nopcommerce.pages.HomePage;
import web.nopcommerce.pages.RegisterPage;
import org.openqa.selenium.WebDriver;

public class Register_Test {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = BaseTest.launchBrowser("chrome", ReadPropertyFile.readData("url"));
    }

    @Test(priority = 0)
    public void goToRegisterPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/", "User is NOT redirected to Home Page");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store", "Home Page title is NOT Correct");

        homePage.clickRegisterLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/register?returnUrl=%2F", "User is NOT redirected to Register Page");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Register", "Register Page title is NOT Correct");
    }

    @Test(priority = 1)
    public void validateMandatoryFields() {
        RegisterPage registerPage = new RegisterPage(driver);
        Assert.assertTrue(registerPage.checkAllMandatoryFields(), "All Mandatory Fields are NOT present");

        registerPage.clickSubmitRegisterButton();
        Assert.assertTrue(registerPage.checkAllMandatoryFieldsErrors(), "All Mandatory Fields Errors are NOT present");
    }

    @Test(priority = 2)
    public void registerUser() throws Exception {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.addValuesToRegisterForm(ReadPropertyFile.readData("gender"), ReadPropertyFile.readData("firstname"), ReadPropertyFile.readData("lastname"), ReadPropertyFile.readData("dayDOB"), ReadPropertyFile.readData("monthDOB"), ReadPropertyFile.readData("yearDOB"), ReadPropertyFile.readData("email"), ReadPropertyFile.readData("company"), Boolean.parseBoolean(ReadPropertyFile.readData("newsletter")), ReadPropertyFile.readData("password"));
        registerPage.clickSubmitRegisterButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/registerresult/1?returnUrl=/");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
