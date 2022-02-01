package pageObjects.TeamAndersen;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class TeamAndersenAuthPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;
    Actions actions;

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public TeamAndersenAuthPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public TeamAndersenAuthPage getPage() {
        webDriver.get("https://auth.andersenlab.com/login");
        return this;
    }

    public TeamAndersenAuthPage enterLogin() {
        sendKeysWithWait(loginField, "a.klymenko");
        return this;
    }

    public TeamAndersenAuthPage enterPassword() {
        sendKeysWithWait(passwordField, "zOPXM8701n0B");
        return this;
    }

    public TeamAndersenPage clickToSubmit() {
        clickWithWait(submitButton);
        return new TeamAndersenPage(webDriver);
    }
}
