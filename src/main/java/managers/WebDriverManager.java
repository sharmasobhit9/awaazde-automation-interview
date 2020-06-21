package managers;

import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;*/

public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FF_DRIVER_PROPERTY = "webdriver.gecko.driver";

    public WebDriverManager() {
        driverType = FileReader.getInstance().getConfigReader().getBrowser();
        environmentType = FileReader.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() throws MalformedURLException {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() throws MalformedURLException {
        switch (environmentType) {
            case STAGE : driver = createSTAGEDriver();
                break;
            case PP : driver = createPPDriver();
                break;
            case LIVE : driver = createLiveDriver();
        }
        return driver;
    }

    private WebDriver createPPDriver() throws MalformedURLException {
        switch (driverType) {
            case FIREFOX : System.setProperty(FF_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getdriverPathFF());
                System.setProperty("webdriver.firefox.logfile", ".//log//ppFFLog.txt");
                driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//PPchromeLog.txt");
                driver = new ChromeDriver();
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;
            case GridChrome :
                ChromeOptions cap = new ChromeOptions();
                cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/wd/hub"),cap);
                break;
            case GridFF :
                DesiredCapabilities capFF = DesiredCapabilities.firefox();
                //FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/grid/hub"), capFF);
                break;
            case HeadlessChrome :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//PPchromeLogHeadless.txt");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-sized1200,600");
                chromeOptions.addArguments("--proxy-server='direct://'");
                chromeOptions.addArguments("--proxy-bypass-list=*");
                driver = new ChromeDriver(chromeOptions);
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;
        }
        driver.manage().timeouts().implicitlyWait(FileReader.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
    private WebDriver createSTAGEDriver() throws MalformedURLException {
        switch (driverType) {
            case FIREFOX : System.setProperty(FF_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getdriverPathFF());
                System.setProperty("webdriver.firefox.logfile", ".//log//StageFFLog.txt");
                driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//StagechromeLog.txt");
                driver = new ChromeDriver();
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;

            case GridChrome :
                ChromeOptions cap = new ChromeOptions();
                cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/wd/hub"),cap);
                break;

            case GridFF :
                DesiredCapabilities capFF = DesiredCapabilities.firefox();
                //FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/wd/hub"), capFF);
                break;

            case HeadlessChrome :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//StagechromeLogHeadless.txt");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-sized1200,600");
                chromeOptions.addArguments("--proxy-server='direct://'");
                chromeOptions.addArguments("--proxy-bypass-list=*");
                driver = new ChromeDriver(chromeOptions);
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;
        }
        driver.manage().timeouts().implicitlyWait(FileReader.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createLiveDriver() throws MalformedURLException {
        switch (driverType) {
            case FIREFOX : System.setProperty(FF_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getdriverPathFF());
                System.setProperty("webdriver.firefox.logfile", ".//log//LiveFFLog.txt");
                driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//LivechromeLog.txt");
                driver = new ChromeDriver();
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;
            case GridChrome :
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/wd/hub"),options);
                break;
            case GridFF :
                DesiredCapabilities capFF = DesiredCapabilities.firefox();
                //FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://seleniumgrid:4444/wd/hub"), capFF);
                break;
            case HeadlessChrome :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReader.getInstance().getConfigReader().getDriverPath());
                System.setProperty("webdriver.chrome.logfile", ".//log//LIVEchromeLogHeadless.txt");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-sized1200,600");
                chromeOptions.addArguments("--proxy-server='direct://'");
                chromeOptions.addArguments("--proxy-bypass-list=*");
                driver = new ChromeDriver(chromeOptions);
                if(FileReader.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
                break;
        }
        driver.manage().timeouts().implicitlyWait(FileReader.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}