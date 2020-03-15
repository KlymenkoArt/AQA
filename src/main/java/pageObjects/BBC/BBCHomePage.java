package pageObjects.BBC;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class BBCHomePage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;
    @FindBy(xpath = "//input[@id='orb-search-q']")
    private WebElement searchField;
    @FindBy(id = "orb-search-button")
    private WebElement searchButton;

    public BBCHomePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void getPage() {
        webDriver.get("http://www.bbc.com/");
    }

    public boolean isSearchFieldDisabled() {
        return searchField.isEnabled();
    }

    public void clearSearchField() {
        searchField.clear();
    }

    public BBCSearchResultPage searchValue(final String testData) {
        searchField.sendKeys(testData);
        this.clickWithWait(searchButton);
        return new BBCSearchResultPage(webDriver);
    }
}
