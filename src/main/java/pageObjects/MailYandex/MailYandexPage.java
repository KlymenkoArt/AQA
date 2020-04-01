package pageObjects.MailYandex;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String getUserName() {
        wait.isDisplayedElement(nameUser);
        return nameUser.getText();
    }

    public void logout() {
        openDropDownListButton.click();
        signOut.click();
    }
}
