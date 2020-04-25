package pageObjects.YandexMarket;

import automationFramework.Wait;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Double.compare;

public class YandexMarketHomePage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;
    Actions actions;
    @FindBy(xpath = "//input[@id='7893318_7701962']")
    WebElement xiaomiCheckBox;
    @FindBy(xpath = "//button[contains(@class,'button button_theme_normal')]")
    WebElement showNumberOfElementsButton;
    @FindBy(xpath = "//input[@id = 'offer-shipping_-1']")
    WebElement deliveryMethodRadioButton;
    @FindBy(id = "header-search")
    WebElement searchField;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;
    @FindBy(xpath = "//*[@class='n-filter-panel-dropdown__main']//*[contains(@data-bem, 'aprice')]/a")
    WebElement sortByPriceButton;
    @FindBy(xpath = "(//div[contains(@class,'n-product-toolbar__item link')])[1]")
    WebElement compareFirstElement;
    @FindBy(xpath = "(//div[contains(@class,'n-product-toolbar__item link')])[2]")
    WebElement compareSecondElement;
    @FindBy(xpath = "//a[@data-bem='{\"button\":{}}']")
    WebElement compareButton;
    @FindBy(xpath = "//*[@class='n-compare-toolbar__action-clear link']")
    WebElement deleteListButton;
    @FindBy(xpath = "//span[text()='Бытовая техника']")
    WebElement appliancesLink;
    @FindBy(linkText = "Холодильники")
    WebElement refrigerator;
    @FindBy(xpath = "//*[@placeholder='121']")
    WebElement width;
    List<String> listValues;

    public YandexMarketHomePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void getPage() {
        webDriver.get("https://market.yandex.by/catalog--mobilnye-telefony-v-minske/54726/list?local-offers-first=0&onstock=1");
        this.wait.sleep(10);
    }

    public void selectCheckBoxXiaomi() {

        if (!this.isCheckBoxSelected()) {
            this.clickWithWait(xiaomiCheckBox.findElement(By.xpath("./..")));
            wait.waitForAjaxToFinish();
        }
    }

    public YandexMarketHomePage selectRadioButton(boolean isRadioBoxSelected) {
        if (!isRadioBoxSelected) {
            this.clickWithWait(this.webDriver.findElement(By.xpath("//input[@id = 'offer-shipping_pickup']")));
            this.wait.sleep(2);
            wait.waitForAjaxToFinish();
        }
        return this;
    }

    public boolean isCheckBoxSelected() {
        actions.moveToElement(xiaomiCheckBox);
        return xiaomiCheckBox.isSelected();
    }

    public boolean isRadioButtonSelected() {
        actions.moveToElement(deliveryMethodRadioButton);
        return deliveryMethodRadioButton.isSelected();
    }

    public YandexMarketHomePage changeProductsDisplayed(String countOfElement) {
        wait.sleep(6);
        actions.moveToElement(showNumberOfElementsButton);
        this.clickWithWait(showNumberOfElementsButton);
        this.clickWithWait(this.webDriver.findElement(By.xpath("//span[text()='Показывать по " + countOfElement + "']")));
        this.wait.useWait(ExpectedConditions.textToBePresentInElement(this.webDriver
                        .findElement(By.xpath("//button[contains(@class,'button button_theme_normal')]/span")),
                "Показывать по " + countOfElement));
        this.wait.waitForAjaxToFinish();
        return this;
    }

    public YandexMarketHomePage sortByPrice() {
        sortByPriceButton.click();
        this.wait.waitForAjaxToFinish();
        return this;
    }

    public int getQuantityElementsOfPage(boolean fullDescription) {
        String countElements;
        if (fullDescription) {
            countElements = "//div[@class='n-snippet-card2__main-price-wrapper']//div[@class='price']";
        } else {
            countElements = "//div[@class='n-snippet-cell2__main-price']//div[@class='price']";
        }
        List<WebElement> numList = this.webDriver.findElements(By.xpath(countElements));
        listValues = new ArrayList<>();
        for (final WebElement element : numList) {
            listValues.add(element.getText());
        }
        return listValues.size();
    }

    public List<Double> getPricesOfProducts() {
        List<Double> doubleList = new ArrayList<>();
        for (String listValue : listValues) {
            doubleList.add(Double.parseDouble(listValue.replace(" б.p.", "")
                    .replace(" ", "").replace(",", ".")));
        }
        return doubleList;
    }

    public boolean isSortedAsc(List<Double> doubleList) {
        return Ordering.<Double>natural().isOrdered(doubleList);
    }

    public boolean isSortedDesc(List<Double> doubleList) {
        Iterator<Double> it = doubleList.iterator();
        if (it.hasNext()) {
            Double prev = it.next();
            while (it.hasNext()) {
                Double next = it.next();
                if (compare(prev, next) < 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }

    public YandexMarketHomePage enterValueInSearchField(final String value) {
        wait.sleep(10);
        actions.moveToElement(searchField);
        sendKeysWithWait(searchField, value);
        return this;
    }

    public YandexMarketHomePage clickToSearch() {
        clickWithWait(searchButton);
        return this;
    }

    public String getValueFromSearchField() {
        this.webDriver.findElements(By.id("header-search"));
        return searchField.getAttribute("value");
    }

    public YandexMarketHomePage addFirstTwoElementsToCompare() {
        wait.sleep(3);
        clickWithJS(compareFirstElement);
        wait.waitForAjaxToFinish();
        clickWithJS(compareSecondElement);
        wait.waitForAjaxToFinish();
        return this;
    }

    public List<String> getNameOfProducts() {
        List<String> listNames = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            listNames.add(this.webDriver.findElement(By.xpath("(//*[contains(@class, 'header')]" +
                    "//*[contains(@class, 'link_type_cpc')])[" + i + "]"))
                    .getAttribute("title"));
        }
        return listNames;
    }

    public YandexMarketHomePage goToComparePage() {
        wait.isDisplayedElement(compareButton);
        clickWithJS(compareButton);
        return this;
    }

    public List<String> getElementsFromComparePage() {
        List<String> listNamesOnComparePage = new ArrayList<>();
        for (int i = 2; i > 0; i--) {
            listNamesOnComparePage.add(this.webDriver.findElement(By.xpath("(//*[@class='n-compare-head__image']" +
                    "//*[@class='image'])[" + i + "]"))
                    .getAttribute("alt"));
        }
        return listNamesOnComparePage;
    }

    public YandexMarketHomePage deleteElementsFromCompare() {
        clickWithJS(deleteListButton);
        return this;
    }

    public String getTextAfterClearCompareList() {
        return this.webDriver.findElement(By.xpath("//*[@class='n-compare-empty__content']")).getText();
    }

    public YandexMarketHomePage selectCategoryOfActionCameras() {
        clickWithWait(this.webDriver.findElement(By.xpath("//a[@href='/catalog--elektronika/54440']")));
        scrollWithJS("650");
        clickWithWait(this.webDriver.findElement(By.xpath("//a[contains(@href, '/catalog--ekshn-kamery-v-minske')]")));
        return this;
    }

    public YandexMarketHomePage selectCategoryOfRefrigerators() {
        wait.isDisplayedElement(appliancesLink);
        clickWithJS(appliancesLink);
        wait.isDisplayedElement(refrigerator);
        clickWithJS(refrigerator);
        return this;
    }

    public YandexMarketHomePage enableFilterWidth() {
        scrollWithJS("1500");
        actions.moveToElement(width);
        width.click();
        width.sendKeys("50");
        wait.waitForAjaxToFinish();
        return this;
    }

    public List<Double> getCharacteristicOfRefrigerator() {
        List<Double> doubleListWithWidth = new ArrayList<>();
        for (final WebElement element : this.webDriver.findElements(By.xpath("//*[contains(text(),'ШхВхГ:')]"))) {
            String characteristic = element.getText().replace("ШхВхГ: ", "");
            doubleListWithWidth.add(Double.parseDouble(characteristic.substring(0, characteristic.indexOf("х"))));
        }
        return doubleListWithWidth;
    }
}
