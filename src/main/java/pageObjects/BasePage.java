package pageObjects;

import automationFramework.InitialDriver;
import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
