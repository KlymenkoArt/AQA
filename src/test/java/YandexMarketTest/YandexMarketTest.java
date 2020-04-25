package YandexMarketTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.YandexMarket.YandexMarketHomePage;

import java.util.List;

public class YandexMarketTest extends BaseTest {
    private YandexMarketHomePage yandexMarketHomePage;

    @BeforeMethod
    public void setupData() {
        yandexMarketHomePage = new YandexMarketHomePage(webDriver);
        yandexMarketHomePage.getPage();
    }

    @Test
    public void showContent() {
        yandexMarketHomePage.selectCheckBoxXiaomi();
        Assert.assertTrue(yandexMarketHomePage.isCheckBoxSelected());
    }

    @Test
    public void changeContentDisplayed() {
        yandexMarketHomePage.changeProductsDisplayed("12");
        Assert.assertEquals(yandexMarketHomePage.getQuantityElementsOfPage(false), 12,
                "The received number of elements does not match the expected result");
        yandexMarketHomePage.changeProductsDisplayed("48");
        Assert.assertEquals(yandexMarketHomePage.getQuantityElementsOfPage(false), 48,
                "The received number of elements does not match the expected result");
    }

    @Test
    public void chooseShippingMethod() {
        yandexMarketHomePage.selectRadioButton(true);
        Assert.assertTrue(yandexMarketHomePage.isRadioButtonSelected());
    }

    @Test
    public void enterTextOnSearchField() {
        yandexMarketHomePage.enterValueInSearchField("Xiaomi")
                .clickToSearch();
        Assert.assertEquals(yandexMarketHomePage.getValueFromSearchField(), "Xiaomi");
    }

    @Test
    public void sortedPrices() {
        yandexMarketHomePage.changeProductsDisplayed("12")
                .sortByPrice()
                .getQuantityElementsOfPage(false);
        Assert.assertTrue(yandexMarketHomePage.isSortedAsc(yandexMarketHomePage.getPricesOfProducts()),
                " List is not sorted correctly");

        yandexMarketHomePage.sortByPrice()
                .getQuantityElementsOfPage(false);
        Assert.assertTrue(yandexMarketHomePage.isSortedDesc(yandexMarketHomePage.getPricesOfProducts()),
                " List is not sorted correctly");
    }

    @Test
    public void compareProducts() {
        yandexMarketHomePage.enterValueInSearchField("Note 8")
                .clickToSearch()
                .addFirstTwoElementsToCompare();
        final List<String> elementsForCompare = yandexMarketHomePage.getNameOfProducts();
        yandexMarketHomePage.goToComparePage();
        Assert.assertEquals(elementsForCompare, yandexMarketHomePage.getElementsFromComparePage(),
                "Elements on compare page not equal to elements before");
    }

    @Test
    public void deleteOfCompare() {
        this.compareProducts();
        yandexMarketHomePage.deleteElementsFromCompare();
        Assert.assertTrue(yandexMarketHomePage.getTextAfterClearCompareList()
                .contains("Товаров нет. Чтобы увидеть ранее добавленные вами товары, авторизуйтесь."));
    }

    @Test
    public void sortingCheck() {
        yandexMarketHomePage.selectCategoryOfActionCameras()
                .changeProductsDisplayed("12")
                .sortByPrice()
                .sortByPrice()
                .getQuantityElementsOfPage(true);
        Assert.assertTrue(yandexMarketHomePage.isSortedDesc(yandexMarketHomePage.getPricesOfProducts()));
    }

    @Test
    public void checkSortingOfAppliances() {
        yandexMarketHomePage.selectCategoryOfRefrigerators()
                .changeProductsDisplayed("12")
                .enableFilterWidth()
                .getCharacteristicOfRefrigerator()
                .forEach(width -> Assert.assertTrue(width <= 50));
    }
}
