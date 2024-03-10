package seleniumDrivers;

import com.aventstack.extentreports.Status;
import web.nopcommerce.testScripts.BaseTest;
import utils.SuiteListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.Log4J;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class WebElementActions extends SuiteListener {

    /**
     * To pass input/value to the Text Field
     * @param element
     * @param input
     * @param message
     */
    public static void enterInputToTextField(WebElement element, String input, String message) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.clear();
                element.sendKeys(input);
                Log4J.info(message+": Passed");
                test.log(Status.PASS, message+": Passed");
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log4J.error(message+": Failed");
            test.log(Status.FAIL, message+": Failed");
        }
    }

    /**
     * To click on an Element
     * @param element
     * @param message
     */
    public static void clickOnElement(WebElement element, String message) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                Log4J.info(message+": Passed");
                test.log(Status.PASS, message+": Passed");
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log4J.error(message+": Failed");
            test.log(Status.FAIL, message+": Failed");
        }
    }

    /**
     * To select/un-select Checkbox
     * @param element
     * @param select
     * @param message
     */
    public static void selectCheckBoxRadio(WebElement element, boolean select, String message) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                if (select) {
                    if (element.isSelected()) {
                        Log4J.info(message+": is already selected");
                        test.log(Status.PASS, message+": is already selected");
                    } else {
                        element.click();
                        Assert.assertTrue(element.isSelected());
                        Log4J.info(message+": is selected");
                        test.log(Status.PASS, message+": is selected");
                    }
                } else {
                    if (element.isSelected()) {
                        element.click();
                        Assert.assertFalse(element.isSelected());
                        Log4J.info(message+": is un-selected");
                        test.log(Status.PASS, message+": is un-selected");
                    } else {
                        Log4J.info(message+": is already un-selected");
                        test.log(Status.PASS, message+": is already un-selected");
                    }
                }
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log4J.error(message+": failed to select/un-select");
            test.log(Status.FAIL, message+": failed to select/un-select");
        }
    }

    /**
     * To click on an Element using JavaScript
     * @param element
     * @param message
     */
    public static void clickByJavaScript(WebElement element, String message) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) BaseTest.getDriver();
            js.executeScript("arguments[0].click()", element);
            Log4J.info(message+": Passed");
            test.log(Status.PASS, message+": Passed");
        } catch (Exception e) {
            Log4J.error(message+": Failed");
            test.log(Status.FAIL, message+": Failed");
        }
    }

    /**
     * To select Value from Drop Down List
     * @param element
     * @param value
     * @param message
     */
    public static void selectDropDownValue(WebElement element, String value, String message) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(value);
            WebElement selectedOption = select.getFirstSelectedOption();
            Assert.assertEquals(selectedOption.getText(), value);
            Log4J.info(message+": Passed");
            test.log(Status.PASS, message+": Passed");
        }catch (Exception e) {
            Log4J.error(message+": Failed");
            test.log(Status.FAIL, message+": Failed");
        }
    }

    /**
     * Get Text of an Element
     * @param element
     * @return
     */
    public static String getTextOfElement(WebElement element) {
        try {
            element.isDisplayed();
            return element.getText();
        }catch (Exception e) {
            return "";
        }
    }

    /**
     * To match text of an element with a particular text.
     * @param element
     * @param text
     * @return
     */
    public static boolean matchTextOfElement(WebElement element, String text) {
        try {
            if (element.isDisplayed() && element.getText().contains(text)) {
                Log4J.info(text+": Text is present");
                test.log(Status.PASS, text+": Text is present");
                return true;
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log4J.error(text+": Text is NOT present");
            test.log(Status.FAIL, text+": Text is NOT present");
            return false;
        }
    }

    /**
     * To get Attribute Value of an Element
     * @param element
     * @param attribute
     * @param message
     * @return
     */
    public static String getAttributeOfElement(WebElement element, String attribute, String message) {
        try {
            element.isDisplayed();
            return element.getAttribute(attribute);
        }catch (Exception e) {
            return "";
        }
    }

    /**
     * To Pause an Execution
     * @param milliseconds
     * @throws InterruptedException
     */
    public static void wait(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    /**
     * To verify values from Drop Down List
     * @param element
     * @param values
     * @param message
     */
    public static void verifyPickListValues(WebElement element, String[] values, String message) {
        try {
            Select selection = new Select(element);
            List<WebElement> options = selection.getOptions();
            for (WebElement option : options) {
                for (int i = 0; i < values.length; i++) {
                    assertTrue(option.getText().equalsIgnoreCase(values[i]));
                    break;
                }
            }
            Log4J.info(message+": Passed");
            test.log(Status.PASS, message+": Passed");
        }catch (Exception e) {
            Log4J.error(message+": Failed");
            test.log(Status.FAIL, message+": Failed");
        }
    }

    /**
     * To move Mouse cursor to an Element
     * @param element
     * @param message
     */
    public static void moveToElement(WebElement element, String message) {
        Actions action = new Actions(BaseTest.getDriver());
        action.moveToElement(element).build().perform();
    }

    /**
     * To perform Drag and Drop
     * @param sourceElement
     * @param desElement
     * @param message
     */
    public static void dragAndDrop(WebElement sourceElement, WebElement desElement, String message) {
        Actions action = new Actions(BaseTest.getDriver());
        action.dragAndDrop(sourceElement, desElement).build().perform();

    }

    /**
     * To check if Element is present
     * @param element
     * @param message
     */
    public static boolean isElementPresent(WebElement element, String message) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                Log4J.info(message+": Element is present");
                test.log(Status.PASS, message+": Element is present");
                return true;
            }else {
                throw new Exception();
            }
        } catch (Exception e) {
            Log4J.error(message+": Element is NOT Present");
            test.log(Status.FAIL, message+": Element is NOT Present");
            return false;
        }
    }

}
