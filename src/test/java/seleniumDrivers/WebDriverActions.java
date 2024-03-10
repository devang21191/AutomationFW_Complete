package seleniumDrivers;

import web.nopcommerce.testScripts.BaseTest;
import utils.SuiteListener;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverActions extends SuiteListener {

    /**
     * To refresh the Browser
     */
    public static void refresh() {
        BaseTest.getDriver().navigate().refresh();
    }

    /**
     * To switch window by Page Title
     * @param title
     */
    public static void switchingWindowByTitle(String title) {
        Set<String> windows = BaseTest.getDriver().getWindowHandles();
        for (String window : windows) {
            BaseTest.getDriver().switchTo().window(window);
            if (BaseTest.getDriver().getTitle().contains(title))
                break;
        }
    }

    /**
     * To switch window by URL
     * @param url
     */
    public static void switchWindowByURL(String url) {
        Set<String> windows = BaseTest.getDriver().getWindowHandles();
        for (String window : windows) {
            BaseTest.getDriver().switchTo().window(window);
            if (BaseTest.getDriver().getCurrentUrl().contains(url))
                break;
        }
    }

    /**
     * To handle Web Alerts
     * @param value
     * @param status
     */
    public static void handleAlerts(String value, String status) {
        if (value.isEmpty() && status.equalsIgnoreCase("accept")) {
            BaseTest.getDriver().switchTo().alert().accept();
        }else if (value.isEmpty() && status.equalsIgnoreCase("dismiss")) {
            BaseTest.getDriver().switchTo().alert().dismiss();
        }else {
            BaseTest.getDriver().switchTo().alert().sendKeys(value);
            BaseTest.getDriver().switchTo().alert().accept();
        }
    }

    /**
     * To add Timeout on Page Load
     * @param seconds
     */
    public static void pageLoadTimeOut(int seconds) {
        BaseTest.getDriver().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    /**
     * To switch Frame by Element
     * @param element
     */
    public static void switchToFrameByElement(WebElement element) {
        BaseTest.getDriver().switchTo().frame(element);
    }

    /**
     * To switch to Default Page/Frame
     */
    public static void switchToDefaultContent() {
        BaseTest.getDriver().switchTo().defaultContent();
    }
}
