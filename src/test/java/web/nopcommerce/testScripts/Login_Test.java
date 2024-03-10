package web.nopcommerce.testScripts;

import dataDrivers.ReadPropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import web.nopcommerce.pages.HomePage;
import web.nopcommerce.pages.LoginPage;

public class Login_Test extends BaseTest{
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = launchBrowser("chrome", ReadPropertyFile.readData("url"));
    }

    @Test(priority = 0)
    public void goToLoginPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/", "User is NOT redirected to Home Page");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store", "Home Page title is NOT Correct");

        homePage.clickLoginLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/login?returnUrl=%2F", "User is NOT redirected to Login Page");
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login", "Login Page title is NOT Correct");
    }

    @Test(priority = 1)
    public void validateMandatoryFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.checkEmailInputError(), "Empty email Error is NOT present");

        loginPage.addEmailInput("test@");
        Assert.assertTrue(loginPage.checkWrongEmailError(), "Wrong email Error is NOT present");

        loginPage.addEmailInput("wrongemail@test.tt");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.checkLoginError(), "Login unsuccessful Error is NOT present");
        Assert.assertTrue(loginPage.checkNoCustomerError(), "No Customer Found Error is NOT present");

        loginPage.addEmailInput(ReadPropertyFile.readData("email"));
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.checkLoginError(), "Login unsuccessful Error is NOT present");
        Assert.assertTrue(loginPage.checkIncorrectCredentialsError(), "Incorrect Credentials Error is NOT present");
    }

    @Test(priority = 2)
    public void loginUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.addEmailInput(ReadPropertyFile.readData("email"));
        loginPage.addPasswordInput(ReadPropertyFile.readData("password"));
        loginPage.clickLoginButton();
    }




 /*   @DataProvider(name = "Authentication")
    public Object[][] Authentication() throws Exception {
        Object[][] testObjectArray = ReadDataFromExcelFile_xlsx.getTableArray("D:\\SampleTestData_2.xlsx", "userData");
        return testObjectArray;
    }

    @Test(dataProvider = "Authentication")
    public void insurance_login(String username, String password) throws Exception {
        driver.get("https://demo.guru99.com/insurance/v1/index.php");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='submit']")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='password']/following-sibling::span/b"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertTrue(errorMessage.isEnabled());
        Assert.assertEquals(errorMessage.getText(), "Enter your Email address and password correct", "Error Message is not correct");
    }*/

    @AfterClass(alwaysRun = true)
    public void tearDown() {
//        driver.quit();
    }
}
