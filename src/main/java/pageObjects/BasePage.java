package pageObjects;

import automationFramework.InitialDriver;
import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    private WebDriver webDriver = InitialDriver.getWebDriver();
    private Wait wait = new Wait(webDriver);

    public void clickWithWait(By by) {
        wait.clickableElement(by);
        webDriver.findElement(by).click();
    }

    public void clickWithWait(WebElement webElement) {
        wait.clickableElement(webElement);
        webElement.click();
    }

    public void clickToIsDisplayedElement(WebElement webElement) {
        wait.isDisplayedElement(webElement);
        webElement.click();
    }

    public void sendKeysWithWait(WebElement input, String value) {
        wait.isDisplayedElement(input);
        input.click();
        input.clear();
        input.sendKeys(value);
    }

    public List<String> getActiveTabs() {
        return new ArrayList<>(this.webDriver.getWindowHandles());
    }

    public void switchToTab(final String tabName) {
        this.webDriver.switchTo().window(tabName);
    }

    public String clickToLinkAndGetURL(WebElement webElement) {
        webElement.click();
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(1));
        return this.webDriver.getCurrentUrl();
    }
}
