package YandexMarketTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.YandexMarket.YandexMarketHomePage;

public class YandexMarketTest extends BaseTest {
    private YandexMarketHomePage yandexMarketHomePage;

    @BeforeMethod
    public void setupData() {
        yandexMarketHomePage = new YandexMarketHomePage(webDriver);
        yandexMarketHomePage.getPage();
    }

    @Test
    public void showContent () {
        yandexMarketHomePage.selectCheckBoxXiaomi();
        Assert.assertTrue(yandexMarketHomePage.isCheckBoxSelected());
    }

    @Test
    public void changeContentDisplayed(){
        yandexMarketHomePage.changeProductsDisplayed("12");
        Assert.assertEquals(yandexMarketHomePage.getQuantityElementsOfPage(), 12,
                "The received number of elements does not match the expected result");
        yandexMarketHomePage.changeProductsDisplayed("48");
        Assert.assertEquals(yandexMarketHomePage.getQuantityElementsOfPage(), 48,
                "The received number of elements does not match the expected result");
    }

    @Test
    public void chooseShippingMethod () {
        yandexMarketHomePage.selectRadioButton(true);
        Assert.assertTrue(yandexMarketHomePage.isRadioButtonSelected());
    }

    @Test
    public void enterTextOnSearchField() {
        yandexMarketHomePage.enterValueInSearchField("Xiaomi");
        yandexMarketHomePage.clickToSearch();
        Assert.assertEquals(yandexMarketHomePage.getValueFromSearchField(), "Xiaomi");
    }

    @Test
    public void sortedPrices () {
        yandexMarketHomePage.changeProductsDisplayed("12");
        yandexMarketHomePage.sortByPrice();
        Assert.assertTrue(yandexMarketHomePage.isSortedAsc(yandexMarketHomePage.getPricesOfProducts()),
                " List is not sorted correctly");

        yandexMarketHomePage.sortByPrice();
        Assert.assertTrue(yandexMarketHomePage.isSortedDesc(yandexMarketHomePage.getPricesOfProducts()),
                " List is not sorted correctly");
    }
}
