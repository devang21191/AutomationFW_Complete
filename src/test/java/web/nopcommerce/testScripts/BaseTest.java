package web.nopcommerce.testScripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Log4J;
import utils.SuiteListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest extends SuiteListener {
    static WebDriver driver;
    private static ExtentReports extentReports;
    static ExtentSparkReporter sparkReporter;

    ExtentTest logger;

    /**
     * To Create an instance of Extent Report
     * @return
     * @throws IOException
     */
    public static ExtentReports createExtentReportInstance() throws IOException {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator+"TestReports"+File.separator+getReportName()+".html");

        //Load the Extent Report Properties form the External file
//        sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

        //OR use the .config() function to set the properties here (see following lines)
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setReportName("DemoNopeCommerce Test Automation Report");
        sparkReporter.config().setCss("css-string");
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setJs("js-string");
        sparkReporter.config().setProtocol(Protocol.HTTPS);
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Host Name", "DemoNopeCommerce App");
        extentReports.setSystemInfo("Environment", "Test Env");
        extentReports.setSystemInfo("Tester", "Automation Tester");

        return extentReports;
    }

    /**
     * To generate Name of the Report
     * @return
     */
    public static String getReportName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_z");
        Date date = new Date();
        return  "TestReport_"+dateFormat.format(date);
    }

    /**
     * To return Driver instance
     * @return
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * To Launch Browser and hit URL
     * @param browserName
     * @param url
     * @return
     */
    public static WebDriver launchBrowser(String browserName, String url) {
        switch (browserName) {
            case "chrome" -> {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
            }
            case "edge" -> {
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.get(url);
            }
        }
        Log4J.info("Launched URL: "+url);
        return driver;
    }

}
