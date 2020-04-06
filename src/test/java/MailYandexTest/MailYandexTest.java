package MailYandexTest;

import automationFramework.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.MailYandex.MailYandexPage;
import pageObjects.MailYandex.PassportYandexPage;
import pageObjects.Yandex.YandexHomePage;

public class MailYandexTest extends BaseTest {
    private YandexHomePage yandexHomePage;

    @DataProvider(name = "links")
    private Object[][] link() {
        return new Object[][]{
                {"video", "https://yandex.by/portal/video"},
                {"images", "https://yandex.by/images"},
                {"news", "https://yandex.by/news"},
                {"maps", "https://yandex.by/maps"},
                {"market", "https://market.yandex.by"},
                {"translate", "https://translate.yandex.by"},
                {"music", "https://music.yandex.by"}};
    }

    @BeforeMethod
    public void setupData() {
        yandexHomePage = new YandexHomePage(webDriver);
        yandexHomePage.getPage();
    }

    @Test
    @Story(value = "Сheck authorization on page mail Yandex")
    public void authorizationOfMailYandex() {
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791927", 0);
        passportYandexPage.enterPassword("fhxbr1996");
        MailYandexPage mailYandexPage = passportYandexPage.loginToMail();
        Assert.assertEquals(mailYandexPage.getUserName(), "380939791927", "Incorrect user");
        mailYandexPage.logout();
    }

    @Test
    @Story(value = "Check logout of mail Yandex")
    public void assertLogout() {
        this.authorizationOfMailYandex();
        Assert.assertEquals(yandexHomePage.isLogOut(), "Войти в почту", "Logout is failed");
    }

    @Test
    @Story(value = "Check entered incorrect password")
    public void enterIncorrectPassword() {
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791927", 0);
        passportYandexPage.enterPassword("fhxbr19966");
        passportYandexPage.loginToMail();
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Неверный пароль", "Entered correct password");
    }

    @Test
    @Story(value = "Check entered incorrect login")
    public void enterIncorrectLogin() {
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791928", 0);
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Такого аккаунта нет", "Entered correct login");
    }

    @Test(dataProvider = "links")
    @Story(value = "Сheck page transition")
    public void assertUrlOfPages(String id, String url) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexHomePage.getUrlOfPage(id).contains(url), "Url is incorrect");
        closeTab();
        yandexHomePage.getFocusOnHomePage();
        softAssert.assertAll();
    }

    @Test
    @Story(value = "Сheck language change")
    public void enterLanguage() {
        yandexHomePage.selectLanguage("Bel");
        Assert.assertTrue(yandexHomePage.isLanguageSelected("be"), "Language is not Bel");
    }
}
