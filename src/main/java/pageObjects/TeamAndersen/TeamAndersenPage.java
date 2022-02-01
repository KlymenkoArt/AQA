package pageObjects.TeamAndersen;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;

public class TeamAndersenPage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    Actions actions;

    @FindBy(xpath = "//a[@class='user-info__name_ru ng-star-inserted']")
    private WebElement name;
    @FindBy(xpath = "(//div[@class='location-box-address'])[2]")
    private WebElement location;
    @FindBy(xpath = "//img[@alt='avatar']")
    private WebElement avatar;
    @FindBy(xpath = "//div[@class='portal-menu-search ng-star-inserted']")
    private WebElement searchButton;
    @FindBy(id = "mat-input-0")
    private WebElement searchField;
    @FindBy(xpath = "(//div[@class='menu-avatar'])//div")
    private WebElement hamburgerMenu;
    @FindBy(linkText = "Wiki")
    private WebElement wikiLink;
    @FindBy(linkText = "Jira")
    private WebElement jiraLink;
    @FindBy(linkText = "Support")
    private WebElement supportLink;

    List<String> listValues;

    String searchListXpath = "//*[@id='mat-autocomplete-0']//div[@class='search-field-link-container']";

    public TeamAndersenPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getName() {
        wait.isDisplayedElement(name);
        return name.getText();
    }

    public String getLocation() {
        actions.moveToElement(location);
        return location.getText();
    }

    public PersonalInfo clickToAvatar() {
        clickWithWait(avatar);
        return new PersonalInfo(webDriver);
    }

    public void searchEmployee(String surname) {
        clickWithWait(searchButton);
        actions.sendKeys(searchField, surname).build().perform();
    }

    public List<String> getInitialsOfEmployee() {
        List<WebElement> numList = this.webDriver.findElements(By.xpath(searchListXpath));
        listValues = new ArrayList<>();
        for (final WebElement element : numList) {
            listValues.add(element.getText());
        }
        System.out.println(listValues);
        return listValues;
    }

    public WebElement getWebElement(String link) {
        WebElement webElement = null;
        switch (link) {
            case ("Wiki"):
                webElement = wikiLink;
                break;
            case ("Jira"):
                webElement = jiraLink;
                break;
            case ("Support"):
                webElement = supportLink;
            default:
                break;
        }
        return webElement;
    }

    public String getUrl(WebElement webElement) {
        clickWithWait(hamburgerMenu);
        return clickToLinkAndGetURL(webElement);
    }
}
