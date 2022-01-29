package pageObjects.TeamAndersen;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

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


    public TeamAndersenPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void getFocusOnHomePage() {
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(0));
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

}
