package pageObjects.Yandex;

import automationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;

public class MusicYandexPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;
    Actions actions;
    @FindBy(xpath = "//*[@class='head__search']//input")
    private WebElement searchField;
    @FindBy(xpath = "//a[@href='/artist/975699']")
    private WebElement linkToArtistKorzh;
    @FindBy(xpath = "(//*[contains(@class, 'd-track typo-track')])[1]")
    WebElement popularSongsList;
    @FindBy(xpath = "//*[@class='sidebar__controls']//*[@class='button-inner deco-button-stylable']")
    WebElement playButton;

    public MusicYandexPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public MusicYandexPage enterTextInSearchField(int numberPage) {
        final List<String> tabs = this.getActiveTabs();
        this.switchToTab(tabs.get(numberPage));
        clickWithWait(searchField);
        searchField.click();
        this.sendKeysWithWait(searchField, "Корж");
        wait.waitForAjaxToFinish();
        return this;
    }

    public MusicYandexPage selectArtistOnResultList(){
        this.actions.moveToElement(linkToArtistKorzh).build().perform();
        clickWithJS(linkToArtistKorzh);
        wait.waitForAjaxToFinish();
        return this;
    }

    public String getNameOfArtist() {
        return this.webDriver.findElement(By.xpath("//*[@class='page-artist__title typo-h1 typo-h1_big']")).getText();
    }

    public List<String> getPopularAlbumOfArtist() {
        scrollWithJS("900");
        List<String> listOfNames = new ArrayList<>();
        for (final WebElement element : this.webDriver
                .findElements(By.xpath("//*[@data-card='top_albums']" +
                        "//div[@class='album__artist deco-typo-secondary typo-add']"))) {
            listOfNames.add(element.getAttribute("title"));
        }
        return listOfNames;
    }

    public MusicYandexPage clickToPlayOrStopPopularMusicOfArtist() {
        if (!isPopularSongsListDisplayed()) {
            scrollWithJS("800");
        }
        actions.moveToElement(popularSongsList).build().perform();
        clickWithJS(popularSongsList);
        wait.waitForAjaxToFinish();
        clickWithJS(playButton);
        return this;
    }

    private boolean isPopularSongsListDisplayed() {
        try {
            this.webDriver
                    .findElement(By.xpath("(//*[contains(@class,'d-track_with-cover')])[1]")).isDisplayed();
            return true;
        } catch (final NoSuchElementException e) {
            return false;
        }
    }

    public String getStatusOfPlayer() {
        wait.sleep(10);
        return this.webDriver
                .findElement(By.xpath("//*[contains(@class,'deco-player-controls')]" +
                        "//*[contains(@class, 'player-controls__btn_play')]"))
                .getAttribute("title");
    }
}
