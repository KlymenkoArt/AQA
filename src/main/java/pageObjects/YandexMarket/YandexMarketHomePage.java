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
    @FindBy(xpath = "//button[@role='button']")
    WebElement searchButton;
    @FindBy(xpath = "//*[@class='n-filter-panel-dropdown__main']//*[contains(@data-bem, 'aprice')]/a")
    WebElement sortByPriceButton;

    List<String> listValues;

    public YandexMarketHomePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        this.actions = new Actions(this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void getPage() {
        webDriver.get("https://market.yandex.by/catalog--mobilnye-telefony-v-minske/54726/list?local-offers-first=0&onstock=1");
        this.wait.sleep(5);
    }

    public void selectCheckBoxXiaomi() {

        if (!this.isCheckBoxSelected()) {
            this.clickWithWait(xiaomiCheckBox.findElement(By.xpath("./..")));
            wait.waitForAjaxToFinish();
        }
    }

    public void selectRadioButton(boolean isRadioBoxSelected) {
        if (!isRadioBoxSelected) {
            this.clickWithWait(this.webDriver.findElement(By.xpath("//input[@id = 'offer-shipping_pickup']")));
            this.wait.sleep(2);
            wait.waitForAjaxToFinish();
        }
    }

    public boolean isCheckBoxSelected() {
        actions.moveToElement(xiaomiCheckBox);
        return xiaomiCheckBox.isSelected();
    }

    public boolean isRadioButtonSelected() {
        actions.moveToElement(deliveryMethodRadioButton);
        return deliveryMethodRadioButton.isSelected();
    }

    public void changeProductsDisplayed(String countOfElement) {
        wait.sleep(6);
        actions.moveToElement(showNumberOfElementsButton);
        this.clickWithWait(showNumberOfElementsButton);
        this.clickWithWait(this.webDriver.findElement(By.xpath("//span[text()='Показывать по " + countOfElement + "']")));
        this.wait.useWait(ExpectedConditions.textToBePresentInElement(this.webDriver
                        .findElement(By.xpath("//button[contains(@class,'button button_theme_normal')]/span")),
                "Показывать по " + countOfElement));
        this.wait.waitForAjaxToFinish();
    }

    public void sortByPrice() {
        sortByPriceButton.click();
        this.wait.waitForAjaxToFinish();
    }

    public int getQuantityElementsOfPage() {
        String countElements = "//div[@class='n-snippet-cell2__main-price']//div[@class='price']";
        List<WebElement> numList = this.webDriver.findElements(By.xpath(countElements));
        listValues = new ArrayList<>();
        for (final WebElement element : numList) {
            listValues.add(element.getText());
        }
        return listValues.size();
    }

    public List<Double> getPricesOfProducts() {
        this.getQuantityElementsOfPage();
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

    public void enterValueInSearchField(final String value) {
        actions.moveToElement(searchField);
        this.sendKeysWithWait(searchField, value);
    }

    public void clickToSearch() {
        searchButton.click();
    }

    public String getValueFromSearchField() {
        this.webDriver.findElements(By.id("header-search"));
        return searchField.getAttribute("value");
    }
}
