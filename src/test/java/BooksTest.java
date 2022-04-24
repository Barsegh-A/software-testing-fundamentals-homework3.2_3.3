import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HeaderPage;
import pages.ProductPage;
import pages.SearchResultsPage;

import static constants.messages.ErrorMessages.*;
import static org.testng.Assert.assertTrue;

public class BooksTest extends BaseTest{

    @Test
    public void invalidSearch(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("asdf");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        assertTrue(searchResultsPage.isNoResultMessageDisplayed(), NO_SEARCH_RESULT_ERROR_MESSAGE);
    }

    @Test
    public void search(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("Դորիան");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        ProductPage productPage = searchResultsPage.clickSearchResultItem(1);
        productPage.setQuantity("100");
        productPage.clickBuyButton();
        assertTrue(productPage.isInsufficientQuantityMessageDisplayed(), INSUFFICIENT_QUANTITY_MESSAGE_ERROR_MESSAGE);
    }

    @Test
    public void cartItemsCount(){
        SoftAssert softAssert = new SoftAssert();
        ProductPage productPage = new ProductPage(driver, "61091");
        HeaderPage headerPage = new HeaderPage(driver);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 0, CART_ITEMS_COUNT_ERROR_MESSAGE);
        productPage.clickBuyButton();
        productPage.waitUntilAddingToCartDisappears();
        softAssert.assertEquals(headerPage.getCartItemsCount(), 2, CART_ITEMS_COUNT_ERROR_MESSAGE);
        softAssert.assertAll();
    }

}
