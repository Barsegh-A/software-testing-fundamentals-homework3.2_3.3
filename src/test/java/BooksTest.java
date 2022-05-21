import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HeaderPage;
import pages.ProductPage;
import pages.SearchResultsPage;

import static constants.messages.ErrorMessages.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BooksTest extends BaseTest {

    @Test
    public void invalidSearch() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("asdf");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        assertTrue(searchResultsPage.isNoResultMessageDisplayed(), NO_SEARCH_RESULT_ERROR_MESSAGE);
    }

    @Test
    public void validSearch() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("Կարամազով");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        assertEquals(searchResultsPage.getSearchResultsCount(), 2, WRONG_SEARCH_RESULT_ITEMS_COUNT_ERROR_MESSAGE);
    }

    @Test
    public void searchFlow() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("Դորիան");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        ProductPage productPage = searchResultsPage.clickSearchResultItem(1);
        productPage.setQuantity(20);
        productPage.clickBuyButton();
        assertTrue(productPage.isInsufficientQuantityMessageDisplayed(), INSUFFICIENT_QUANTITY_MESSAGE_ERROR_MESSAGE);
    }

    @Test
    public void cartItemsCount() {
        SoftAssert softAssert = new SoftAssert();
        ProductPage productPage = new ProductPage(driver, "61091");
        HeaderPage headerPage = new HeaderPage(driver);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 0, CART_ITEMS_COUNT_ERROR_MESSAGE);
        productPage.clickBuyButton();
        productPage.waitUntilAddingToCartDisappears();
        softAssert.assertEquals(headerPage.getCartItemsCount(), 1, CART_ITEMS_COUNT_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void priceTest(){
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "ապուշը");
        int priceInSearch = searchResultsPage.getSearchResultProductPrice(0);
        ProductPage productPage = searchResultsPage.clickSearchResultItem(0);
        int priceInProduct = productPage.getProductPrice();
        assertEquals(priceInSearch, priceInProduct, WRONG_PRICE_ERROR_MESSAGE);
    }

}
