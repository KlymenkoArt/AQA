package pageObjects.MailYandex;

import automationFramework.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class MailYandexPage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;

    @FindBy(xpath = "//*[@class='mail-User-Name']")
    private WebElement nameUser;
    @FindBy(id = "recipient-1")
    private WebElement openDropDownListButton;
    @FindBy(xpath = "(//a[@class='b-mail-dropdown__item__content js-user-dropdown-item '])[3]")
    private WebElement signOut;

    public MailYandexPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Get name of user")
    public String getUserName() {
        wait.isDisplayedElement(nameUser);
        return nameUser.getText();
    }

    @Step("Logout of page")
    public void logout() {
        openDropDownListButton.click();
        signOut.click();
    }
}
