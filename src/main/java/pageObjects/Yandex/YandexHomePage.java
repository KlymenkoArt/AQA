package pageObjects.Yandex;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;
import pageObjects.MailYandex.PassportYandexPage;

import java.util.ArrayList;
import java.util.List;

public class YandexHomePage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    @FindBy(xpath = "//a[@data-statlog='tabs.more']")
    private WebElement linkMore;
    @FindBy(className = "geolink__reg")
    private WebElement geoLink;
    @FindBy(xpath = "//*[contains(@class, 'login')]//*[@class='button__text']")
    private WebElement signIn;
    @FindBy(xpath = "//*[contains(@class, 'b-langs')]//a")
    private WebElement languageDropDown;

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

    public PassportYandexPage clickSignIn() {
        clickWithWait(signIn.findElement(By.xpath("./..")));
        return new PassportYandexPage(webDriver);
    }

    public String isLogOut() {
        wait.isDisplayedElement(signIn);
        return signIn.getText();
    }

    public String getUrlOfPage(String pageId) {
        return clickToLinkAndGetURL(this.webDriver.findElement(By.xpath(String.format("//*[@data-id = '%s']", pageId))));
    }

    public void getFocusOnHomePage() {
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(0));
    }

    public void selectLanguage(String lang) {
        languageDropDown.click();
        this.clickWithWait(this.webDriver.findElement(By.xpath(String.format("//*[@aria-label='%s']", lang))));
    }

    public boolean isLanguageSelected(String lang) {
        return this.webDriver.findElement(By.xpath(String.format("//html[@lang='%s']", lang)))
                .isDisplayed();
    }
}
