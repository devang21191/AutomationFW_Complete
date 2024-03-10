package web.nopcommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumDrivers.WebElementActions;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='gender-male']")
    WebElement maleGender;
    @FindBy(xpath = "//input[@id='gender-female']")
    WebElement femaleGender;
    @FindBy(xpath = "//input[@id='FirstName']")
    WebElement firstNameInput;
    @FindBy(xpath = "//input[@id='FirstName']/following::span[text()='*'][1]")
    WebElement firstNameInputAsterisk ;
    @FindBy(xpath = "//span[@id='FirstName-error']")
    WebElement firstNameInputError ;
    @FindBy(xpath = "//input[@id='LastName']")
    WebElement lastNameInput;
    @FindBy(xpath = "//input[@id='LastName']/following::span[text()='*'][1]")
    WebElement lastNameInputAsterisk;
    @FindBy(xpath = "//span[@id='LastName-error']")
    WebElement lastNameInputError;
    @FindBy(xpath = "//select[@name='DateOfBirthDay']")
    WebElement dayOfBirthDDL;
    @FindBy(xpath = "//select[@name='DateOfBirthMonth']")
    WebElement monthOfBirthDDL;
    @FindBy(xpath = "//select[@name='DateOfBirthYear']")
    WebElement yearOfBirthDDL;
    @FindBy(xpath = "//input[@id='Email']")
    WebElement emailInput;
    @FindBy(xpath = "//input[@id='Email']/following::span[text()='*'][1]")
    WebElement emailInputAsterisk;
    @FindBy(xpath = "//span[@id='Email-error']")
    WebElement emailInputError;
    @FindBy(xpath = "//input[@id='Company']")
    WebElement companyNameInput;
    @FindBy(xpath = "//input[@id='Newsletter']")
    WebElement newsletterCheckBox;
    @FindBy(xpath = "//input[@id='Password']")
    WebElement passwordInput;
    @FindBy(xpath = "//input[@id='Password']/following::span[text()='*'][1]")
    WebElement passwordInputAsterisk;
    @FindBy(xpath = "//span[@id='Password-error']")
    WebElement passwordInputError;
    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    WebElement confirmPasswordInput;
    @FindBy(xpath = "//input[@id='ConfirmPassword']/following::span[text()='*'][1]")
    WebElement confirmPasswordInputAsterisk;
    @FindBy(xpath = "//span[@id='ConfirmPassword-error']")
    WebElement confirmPasswordInputError;
    @FindBy(xpath = "//button[@id='register-button']")
    WebElement registerSubmitButton;


    /**
     * Register Page Constructor
     * @param driver
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * To click on Submit Register Button from Register Page
     */
    public void clickSubmitRegisterButton() {
        WebElementActions.clickOnElement(registerSubmitButton, "Click on Register Button from Register Page");
    }

    /**
     * To Check Mandatory * Symbol and Mandatory Error Messages for all the Mandatory Fields
     */
    public boolean checkFirstNameInputAsterisk() { return WebElementActions.isElementPresent(firstNameInputAsterisk, "Firstname * Symbol"); }
    public boolean checkFirstNameInputError() { return WebElementActions.matchTextOfElement(firstNameInputError, "First name is required."); }

    public boolean checkLastNameInputAsterisk() { return WebElementActions.isElementPresent(lastNameInputAsterisk, "Lastname * Symbol"); }
    public boolean checkLastNameInputError() { return WebElementActions.matchTextOfElement(lastNameInputError, "Last name is required."); }

    public boolean checkEmailInputAsterisk() { return WebElementActions.isElementPresent(emailInputAsterisk, "Email * Symbol"); }
    public boolean checkEmailInputError() { return WebElementActions.matchTextOfElement(emailInputError, "Email is required."); }

    public boolean checkPasswordInputAsterisk() { return WebElementActions.isElementPresent(passwordInputAsterisk, "Password * Symbol"); }
    public boolean checkPasswordInputError() { return WebElementActions.matchTextOfElement(passwordInputError, "Password is required."); }

    public boolean checkConfirmPasswordInputAsterisk() { return WebElementActions.isElementPresent(confirmPasswordInputAsterisk, "Confirm Password * Symbol"); }
    public boolean checkConfirmPasswordInputError() { return WebElementActions.matchTextOfElement(confirmPasswordInputError, "Password is required."); }


    /**
     * To Check if all the mandatory fields are showing * symbols
     * @return
     */
    public boolean checkAllMandatoryFields() {
        boolean present = true;
        if (!checkFirstNameInputAsterisk())
            present = false;
        if (!checkLastNameInputAsterisk())
            present = false;
        if (!checkEmailInputAsterisk())
            present = false;
        if (!checkPasswordInputAsterisk())
            present = false;
        if (!checkConfirmPasswordInputAsterisk())
            present = false;
        return present;
    }


    /**
     * To Check if all the mandatory fields are showing correct Error Messages
     * @return
     */
    public boolean checkAllMandatoryFieldsErrors() {
        boolean present = true;
        if (!checkFirstNameInputError())
            present = false;
        if (!checkLastNameInputError())
            present = false;
        if (!checkEmailInputError())
            present = false;
        if (!checkPasswordInputError())
            present = false;
        if (!checkConfirmPasswordInputError())
            present = false;
        return present;
    }

    /**
     * To add values to all input fields n Register Page
     * @param gender
     * @param firstName
     * @param lastName
     * @param dayDOB
     * @param monthDOB
     * @param yearDOB
     * @param email
     * @param company
     * @param newsletter
     * @param password
     * @throws Exception
     */
    public void addValuesToRegisterForm(String gender, String firstName, String lastName, String dayDOB, String monthDOB, String yearDOB, String email, String company, boolean newsletter, String password) throws Exception{
        switch (gender) {
            case "male", "Male", "MALE" ->
                    WebElementActions.selectCheckBoxRadio(maleGender, true, "Male Gender Radio");
            case "female", "Female", "FEMALE" ->
                    WebElementActions.selectCheckBoxRadio(femaleGender, true, "Female Gender Radio");
        }
        WebElementActions.enterInputToTextField(firstNameInput, firstName, "Enter input to First Name field");
        WebElementActions.enterInputToTextField(lastNameInput, lastName, "Enter input to Last Name field");
        WebElementActions.selectDropDownValue(dayOfBirthDDL, dayDOB, "Select a Day of the DOB");
        WebElementActions.selectDropDownValue(monthOfBirthDDL, monthDOB, "Select a Month of the DOB");
        WebElementActions.selectDropDownValue(yearOfBirthDDL, yearDOB, "Select a Year of the DOB");
        WebElementActions.enterInputToTextField(emailInput, email, "Enter input to Email field");
        WebElementActions.enterInputToTextField(companyNameInput, company, "Enter input to Company Name field");
        WebElementActions.selectCheckBoxRadio(newsletterCheckBox, newsletter, "Newsletter Checkbox");
        WebElementActions.enterInputToTextField(passwordInput, password, "Enter input to Password field");
        WebElementActions.enterInputToTextField(confirmPasswordInput, password, "Enter input to Confirm Password field");
    }


}
