package pageObjects.MailYandex;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.List;

public class PassportYandexPage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    private Actions actions;

    @FindBy(name = "login")
    private WebElement loginField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;
    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passwordField;
    @FindBy(xpath = "//div[@class='passp-form-field__error']")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@class='passp-current-account__select-icon']")
    private WebElement dropDownButton;
    @FindBy(xpath = "//span[@class='passp-account-list__sign-in-icon']")
    private WebElement signInButton;

    public PassportYandexPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    private void enterTextInLoginField(String login) {
        actions.sendKeys(loginField, login).perform();
        actions.click(submit).perform();
    }

    public void enterLogin(String login, int numberPage) {
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(numberPage));
        if (isAccountExist()) {
            signInOnOtherAccount();
            enterTextInLoginField(login);
        } else {
            enterTextInLoginField(login);
        }
        wait.waitForAjaxToFinish();
    }

    private boolean isAccountExist() {
        try {
            this.webDriver.findElement(By.xpath("//*[@class='passp-current-account__avatar']"));
            return true;
        } catch (final NoSuchElementException e) {
            return false;
        }
    }

    public void enterPassword(String password) {
        wait.isDisplayedElement(passwordField);
        passwordField.sendKeys(password);
    }

    public MailYandexPage loginToMail() {
        submit.click();
        wait.waitForAjaxToFinish();
        return new MailYandexPage(webDriver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void signInOnOtherAccount() {
        dropDownButton.click();
        wait.waitForAjaxToFinish();
        signInButton.click();
    }
}
