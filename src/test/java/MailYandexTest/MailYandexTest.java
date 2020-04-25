package MailYandexTest;

import automationFramework.BaseTest;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.MailYandex.MailYandexPage;
import pageObjects.MailYandex.PassportYandexPage;
import pageObjects.Yandex.YandexHomePage;
import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

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
    public void authorizationOfMailYandex(Method method) {
        ExtentTestManager.startTest(method.getName(), "Сheck authorization on page mail Yandex.");

        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791927", 0)
                .enterPassword("fhxbr1996");
        MailYandexPage mailYandexPage = passportYandexPage.loginToMail();
        Assert.assertEquals(mailYandexPage.getUserName(), "380939791927", "Incorrect user");
        mailYandexPage.logout();
    }

    @Test
    @Story(value = "Check logout of mail Yandex")
    public void assertLogout(Method method) {
        this.authorizationOfMailYandex(method);
        ExtentTestManager.startTest(method.getName(), "Сheck authorization on page mail Yandex, and logout.");

        Assert.assertEquals(yandexHomePage.isLogOut(), "Войти в почту", "Logout is failed");
    }

    @Test
    @Story(value = "Check entered incorrect password")
    public void enterIncorrectPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "Check entered incorrect password");

        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791927", 0)
                .enterPassword("fhxbr19966")
                .loginToMail();
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Неверный пароль", "Entered correct password");
    }

    @Test
    @Story(value = "Check entered incorrect login")
    public void enterIncorrectLogin(Method method) {
        ExtentTestManager.startTest(method.getName(), "Check entered incorrect login");

        PassportYandexPage passportYandexPage = yandexHomePage.clickSignIn();
        closeTab();
        passportYandexPage.enterLogin("380939791928", 0);
        Assert.assertEquals(passportYandexPage.getErrorMessage(), "Такого аккаунта нет", "Entered correct login");
    }

    @Test(dataProvider = "links")
    @Story(value = "Сheck page transition")
    public void assertUrlOfPages(String id, String url, Method method) {
        ExtentTestManager.startTest(method.getName(), "Check entered incorrect login");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(yandexHomePage.getUrlOfPage(id).contains(url), "Url is incorrect");
        closeTab();
        yandexHomePage.getFocusOnHomePage();
        softAssert.assertAll();
    }

    @Test
    @Story(value = "Сheck language change")
    public void enterLanguage(Method method) {
        ExtentTestManager.startTest(method.getName(), "Check entered incorrect login");

        yandexHomePage.selectLanguage("Bel");
        Assert.assertTrue(yandexHomePage.isLanguageSelected("be"), "Language is not Bel");
    }

    @Test
    public void scrollToElementOnYandexHomePage() {
        yandexHomePage.scrollUntilElementDisplayed();
        Assert.assertTrue(yandexHomePage.checkLessonLink(), "");
    }

    @Test
    public void sendKeysWithJSTest() {
        yandexHomePage.enterValueInSearchField();
        Assert.assertEquals(yandexHomePage.getTextOnSearchField(), "Черкассы");
    }
}
