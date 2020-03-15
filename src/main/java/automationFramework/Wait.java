package automationFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    WebDriver webDriver;

    public Wait(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebDriverWait createWait() {
        return new WebDriverWait(this.webDriver, 10);
    }

    public void useWait(ExpectedCondition<?> expectedCondition) {
        this.createWait().pollingEvery(Duration.ofMillis(300))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .until(expectedCondition);
    }

    public void clickableElement(By by) {
        useWait(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickableElement(WebElement webElement) {
        useWait(ExpectedConditions.elementToBeClickable(webElement));
    }
}
