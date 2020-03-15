package automationFramework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeSuite
    protected void webDriver () {
        this.webDriver = InitialDriver.getWebDriver();
    }

    @AfterSuite
    public void shutDownWebDriver() {
        InitialDriver.closeWebDriver();
    }
}
