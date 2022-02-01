package pageObjects.TeamAndersen;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class PersonalInfo extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    Actions actions;

    @FindBy(xpath = "//p[text()='Cherkasy']")
    private WebElement location;
    @FindBy(xpath = "(//div[@automation-id=\"hr-info-inline\"])//span")
    private WebElement hrInfo;
    @FindBy(xpath = "(//div[@class='position'])/p[@class='text']")
    private WebElement position;
    @FindBy(xpath = "(//div[@class='menu-avatar'])//div")
    private WebElement hamburgerMenu;
    @FindBy(linkText = "Sign Out")
    private WebElement signOutButton;


    public PersonalInfo(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getText(WebElement webElement) {
        wait.isDisplayedElement(webElement);
        return webElement.getText();
    }

    public WebElement getLocation() {
        return location;
    }

    public WebElement getHrInfo() {
        return hrInfo;
    }

    public WebElement getPosition() {
        return position;
    }

    public void signOut() {
        clickWithWait(hamburgerMenu);
        clickWithWait(signOutButton);
    }
}
