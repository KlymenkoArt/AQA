package automationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class InitialDriver {
    private static String browser = "chrome";
    private static WebDriver webDriver;

    private static WebDriver initialize() {
        if (Objects.isNull(webDriver)) {
            if (browser.equalsIgnoreCase("chrome")) {
                webDriver = new ChromeDriver();
                System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
            } else if (browser.equalsIgnoreCase("ff")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                webDriver = new FirefoxDriver();
                System.setProperty("webdriver.gecko.driver", "./src/main/resources/driver/geckodriver.exe");
            } else if (browser.equalsIgnoreCase("ie")) {
                webDriver = new InternetExplorerDriver();
                System.setProperty("webdriver.ie.driver", ".src/main/resources/driver/IEDriverServer.exe");
            }
        }
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static WebDriver getWebDriver() {
        return Objects.isNull(webDriver) ? initialize() : webDriver;
    }

    public static void closeWebDriver() {
        if (Objects.nonNull(webDriver)) {
            webDriver.quit();
        }
    }
}
