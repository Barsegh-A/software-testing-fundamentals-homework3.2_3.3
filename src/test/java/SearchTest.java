import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HeaderPage;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    public void searchDefaultState() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        softAssert.assertFalse(headerPage.isSearchButtonEnabled(), "Search button is enabled by default.");
        softAssert.assertEquals(headerPage.getSearchFieldPlaceholder(), "Որոնել", "Search field placeholder is wrong.");
        softAssert.assertAll();
    }

    @Test
    public void searchCategoryTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("կար");

        assertTrue(headerPage.isSearchSuggestionsViewPresent(), "Search suggestions are not displayed");
    }
}
