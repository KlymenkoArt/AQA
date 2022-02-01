package MusicYandexTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.MailYandex.PassportYandexPage;
import pageObjects.Yandex.MusicYandexPage;
import pageObjects.Yandex.YandexHomePage;

public class MusicYandexTest extends BaseTest {
    private YandexHomePage yandexHomePage;

    @BeforeTest
    public void AuthorizationOnYandex() {
        yandexHomePage = new YandexHomePage(webDriver);
        yandexHomePage.getPage();
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        passportYandexPage.enterLogin("380939791927", 0)
                .enterPassword("fhxbr1996")
                .loginToMail();
    }

    @BeforeMethod
    public void getHomePage() {
        yandexHomePage.getPage();
    }

    @Test
    public void checkArtist() {
        MusicYandexPage musicYandexPage = yandexHomePage.clickToMusicLink();
        closeTab();
        musicYandexPage.enterTextInSearchField(0)
                .selectArtistOnResultList();
        Assert.assertEquals(musicYandexPage.getNameOfArtist(), "Макс Корж", "Artist was choose incorrect");
        musicYandexPage.getPopularAlbumOfArtist()
                .forEach(artist -> Assert.assertEquals(artist, "Макс Корж"));
    }

    @Test
    public void checkPlayingMusic() {
        MusicYandexPage musicYandexPage = yandexHomePage.clickToMusicLink();
        closeTab();
        musicYandexPage.enterTextInSearchField(0)
                .selectArtistOnResultList()
                .clickToPlayOrStopPopularMusicOfArtist();
        Assert.assertEquals(musicYandexPage.getStatusOfPlayer(), "Пауза [P]");
        musicYandexPage.clickToPlayOrStopPopularMusicOfArtist();
        Assert.assertEquals(musicYandexPage.getStatusOfPlayer(), "Грати [P]");

    }
}
