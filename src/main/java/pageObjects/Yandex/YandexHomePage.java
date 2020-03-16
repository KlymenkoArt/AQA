package pageObjects.Yandex;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;

public class YandexHomePage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;

    @FindBy(xpath = "//a[@data-statlog='tabs.more']")
    private WebElement linkMore;
    @FindBy(className = "geolink__reg")
    private WebElement geoLink;

    public YandexHomePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void getPage() {
        webDriver.get("http://www.yandex.by/");
    }

    public YandexLocation clickOnGeoLink() {
        geoLink.click();
        return new YandexLocation(webDriver);
    }

    public void clickToButtonMore() {
        clickWithWait(linkMore);
    }

    public List<String> getArrayListWithLinksOfMoreMenu() {
        String valuesOfMoreMenu = "//div[@class='home-tabs__more']//a";
        List<WebElement> numList = this.webDriver.findElements(By.xpath(valuesOfMoreMenu));
        List<String> listValues = new ArrayList<>();
        for (final WebElement element : numList) {
            listValues.add(element.getText());
        }
        return listValues;
    }
}
