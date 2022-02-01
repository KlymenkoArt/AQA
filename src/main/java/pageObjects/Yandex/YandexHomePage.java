package pageObjects.Yandex;

import automationFramework.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;
import pageObjects.MailYandex.PassportYandexPage;
import pageObjects.YandexMarket.YandexMarketHomePage;

import java.util.ArrayList;
import java.util.List;

public class YandexHomePage extends BasePage {
    private WebDriver webDriver;
    private Wait wait;
    @FindBy(xpath = "//a[@data-statlog='tabs.more']")
    private WebElement linkMore;
    @FindBy(className = "geolink__reg")
    private WebElement geoLink;
    @FindBy(xpath = "(//a[contains(@class,'home-link desk-notif-card__login-new-item')]//div)[2]")
    private WebElement signIn;
    @FindBy(xpath = "//*[contains(@class, 'b-langs')]//a")
    private WebElement languageDropDown;
    private By lessonLink = By.xpath("//*[text() = 'Яндекс.Уроки']");
    private WebElement lesson;
    @FindBy(xpath = "//*[@id='text']")
    private WebElement yandexSearchField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@class='input__control mini-suggest__input']")
    private WebElement resultField;
    @FindBy(xpath = "//*[@data-id = 'market']")
    private WebElement marketLink;
    @FindBy(xpath = "//*[@data-id = 'music']")
    private WebElement musicLink;

    private JavascriptExecutor js;

    public YandexHomePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Open base page: http://www.yandex.ru/")
    public void getPage() {
        webDriver.get("http://www.yandex.ru/");
    }

    @Step("Click on link to change location")
    public YandexLocation clickOnGeoLink() {
        geoLink.click();
        return new YandexLocation(webDriver);
    }

    @Step("Click to button 'More'")
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

    @Step("Click to button SignIn")
    public PassportYandexPage clickSignIn() {
        clickWithWait(signIn);
        return new PassportYandexPage(webDriver);
    }

    @Step("Logout is completed?")
    public String isLogOut() {
        wait.isDisplayedElement(signIn);
        return signIn.getText();
    }

    @Step("URL is correct")
    public String getUrlOfPage(String pageId) {
        return clickToLinkAndGetURL(this.webDriver.findElement(By.xpath(String.format("//*[@data-id = '%s']", pageId))));
    }

    public void getFocusOnHomePage() {
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(0));
    }

    @Step("Open DropDown with languages")
    public void selectLanguage(String lang) {
        languageDropDown.click();
        this.clickWithWait(this.webDriver.findElement(By.xpath(String.format("//*[@aria-label='%s']", lang))));
    }

    @Step("Language is correct?")
    public boolean isLanguageSelected(String lang) {
        return this.webDriver.findElement(By.xpath(String.format("//html[@lang='%s']", lang)))
                .isDisplayed();
    }

    public void scrollUntilElementDisplayed() {
        boolean flag = true;
        while (flag) {
            try {
                lesson = this.webDriver.findElement(lessonLink);
                flag = false;
            } catch (NoSuchElementException e) {
                this.actionScroll();
            }
        }
    }

    public boolean checkLessonLink() {
        return lesson.isDisplayed();
    }

    public void enterValueInSearchField() {
        sendKeysWithJS("Черкассы", yandexSearchField);
        clickWithJS(searchButton);
    }

    public String getTextOnSearchField() {
        return resultField.getAttribute("value");
    }

    public MusicYandexPage clickToMusicLink() {
        clickWithWait(musicLink);
        return new MusicYandexPage(webDriver);
    }
}
