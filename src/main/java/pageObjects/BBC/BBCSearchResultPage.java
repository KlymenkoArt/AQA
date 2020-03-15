package pageObjects.BBC;

import automationFramework.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class BBCSearchResultPage extends BasePage {

    private WebDriver webDriver;
    private Wait wait;
    @FindBy(xpath = "(//*[contains(@class, 'PromoHeadline')]/a)[3]")
    private WebElement firstLinkOfResultPage;

    @FindBy(xpath = "//input[@id='search-input']")
    private WebElement resultStats;

    @FindBy(xpath = "((//*[contains(@class,'PromoHeadline')]/a)[3]/span)")
    private WebElement textOfAFirstLink;

    @FindBy(xpath = "//a[contains(text(),'Adrian Goldberg')][1]")
    private WebElement textOfPageAfterFollowingALink;


    public BBCSearchResultPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new Wait(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextFromResultSearchField() {
        return resultStats.getAttribute("value");
    }

    public void followALink() {
        this.clickWithWait(firstLinkOfResultPage);
    }

    public String getTexOfAFirstLink() {
        return textOfAFirstLink.getText();
    }
    public String getTextOfAResultPage() {
        return  textOfPageAfterFollowingALink.getText();
    }
}
