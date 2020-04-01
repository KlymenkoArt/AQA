package MailYandexTest;

import automationFramework.BaseTest;
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
    public void assertLogout() {
        this.authorizationOfMailYandex();
        Assert.assertEquals(yandexHomePage.isLogOut(), "Войти в почту", "Logout is failed");
    }

    @Test
    public void enterIncorrectPassword() {
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791927", 0);
        passportYandexPage.enterPassword("fhxbr19966");
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Неверный пароль", "Entered correct password");
    }

    @Test
    public void enterIncorrectLogin() {
        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791928", 0);
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Такого аккаунта нет", "Entered correct login");
    }

    @Test(dataProvider = "links")
    public void assertUrlOfPages(String id, String url) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexHomePage.getUrlOfPage(id).contains(url), "Url is incorrect");
        closeTab();
        yandexHomePage.getFocusOnHomePage();
        softAssert.assertAll();
    }

    @Test
    public void enterLanguage() {
        yandexHomePage.selectLanguage("Bel");
        Assert.assertTrue(yandexHomePage.isLanguageSelected("be"), "Language is not Bel");
    }
}
