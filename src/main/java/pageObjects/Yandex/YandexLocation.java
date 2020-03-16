package pageObjects.Yandex;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;


public class YandexLocation extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    @FindBy(id = "city__front-input")
    private WebElement searchField;

    public YandexLocation(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clearSearchField() {
        clickToIsDisplayedElement(searchField);
        searchField.clear();
    }

    public void enterCityOnSearchField(final String city, String country) {
        searchField.sendKeys(city);
        this.webDriver.findElement(By.xpath(String.format("(//div[text()='%s'])", country))).click();
    }

    public void setLocation(final String city, String country) {
        this.clearSearchField();
        this.enterCityOnSearchField(city, country);
    }
}
