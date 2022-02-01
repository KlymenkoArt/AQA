package BBCTest;

import automationFramework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BBC.BBCHomePage;
import pageObjects.BBC.BBCSearchResultPage;


public class BBCTest extends BaseTest {
    String textOfLink;

    @Test
    public void searchTest() {
        String searchText = "coronavirus";
        BBCHomePage bbcHomePage = new BBCHomePage(webDriver);
        bbcHomePage.getPage();
        Assert.assertTrue(bbcHomePage.isSearchFieldDisabled(),
                "Input should be enable for a enter value");
        bbcHomePage.clearSearchField();
        final BBCSearchResultPage bbcSearchResultPage = bbcHomePage
                .searchValue(searchText);
        Assert.assertEquals(bbcSearchResultPage.getTextFromResultSearchField(),
                searchText,
                "coronavirus");
    }

    @Test
    public void compareArray() {
        int[] intArr = new int[]{34, 54, 48};
        int[] intArrToCompare = new int[]{34, 54, 48};
        String[] stringArr = new String[]{"Hello, I`m testing message"};
        String[] stringArrayToCompare = new String[]{"Hello, I`m testing message"};
        int value = 145;
        String str = "Hello, I`m testing message";

        Assert.assertEqualsNoOrder(stringArr, stringArrayToCompare);
        Assert.assertEquals(intArr, intArrToCompare);
        Assert.assertEquals(value, 145);
        Assert.assertEquals(str, "Hello, I`m testing message");
        Assert.assertTrue(str.contains("message"));
    }
}
