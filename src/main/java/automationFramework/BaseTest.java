package automationFramework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class BaseTest {
    protected WebDriver webDriver;

    public WebDriver getDriver() {
        return this.webDriver;
    }

    @BeforeSuite
    protected void webDriver() {
        this.webDriver = InitialDriver.getWebDriver();
    }

    @AfterSuite
    public void shutDownWebDriver() {
        InitialDriver.closeWebDriver();
    }

    public void closeTab() {
        this.webDriver.close();
    }
}
