package automationFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    WebDriver webDriver;

    public Wait(WebDriver webDriver) {
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

    public void isDisplayedElement(WebElement webElement) {
        useWait(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForAjaxToFinish() {
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        sleep(2);
        wait.until(new ExpectedCondition<Boolean>() {
                       public Boolean apply(WebDriver wdriver) {
                           return ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0").equals(true);
                       }
                   }
        );
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
