package YandexTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Yandex.YandexHomePage;
import pageObjects.Yandex.YandexLocation;

import java.util.List;

public class YandexTest extends BaseTest {

    private YandexHomePage yandexHomePage;

    @BeforeMethod
    public void setupData() {
        yandexHomePage = new YandexHomePage(webDriver);
        yandexHomePage.getPage();
    }

    @Test
    public void compareElements() {
        YandexLocation yandexLocation = yandexHomePage.clickOnGeoLink();
        yandexLocation.setLocation("Лондон", "Великобритания");
        yandexHomePage.clickToButtonMore();
        List<String> resultValueOfLondon = yandexHomePage.getArrayListWithLinksOfMoreMenu();
        yandexHomePage.clickOnGeoLink();
        yandexLocation.setLocation("Париж", "Франция");
        yandexHomePage.clickToButtonMore();
        Assert.assertEquals(resultValueOfLondon, yandexHomePage.getArrayListWithLinksOfMoreMenu(),
                "Contents of the list for London is different from the contents of the list for Paris");
    }
}
