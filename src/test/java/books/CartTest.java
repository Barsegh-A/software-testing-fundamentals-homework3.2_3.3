package books;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.stream.IntStream;

import static constants.messages.ErrorMessages.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CartTest extends BaseTest {

    @Test
    public void cartItemsCountFunctionality() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        addItemsToCart(2);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getItemsCount(), 2, WRONG_PRODUCT_COUNT_ON_CART_ERROR_MESSAGE);
        cartPage.clickProductCountIncreaseButton(1);
        int secondItemCount = cartPage.getProductCount(1);
        softAssert.assertEquals(secondItemCount, 2, CART_ITEM_INCREASE_ERROR_MESSAGE);
        cartPage.clickProductCountDecreaseButton(1);
        secondItemCount = cartPage.getProductCount(1);
        softAssert.assertEquals(secondItemCount, 1, CART_ITEM_DECREASE_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void totalPrice() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        addItemsToCart(3);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), WRONG_INITIAL_TOTAL_PRICE_ERROR_MESSAGE);
        cartPage.clickProductCountIncreaseButton(2);
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), WRONG_INCREASE_TOTAL_PRICE_ERROR_MESSAGE);
        cartPage.clickProductCountDecreaseButton(2);
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), WRONG_DECREASE_TOTAL_PRICE_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void removeItem() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        int initialItems = 2;
        addItemsToCart(initialItems);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        CartItemRemovePopupComponent cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(0);
        cartItemRemovePopupComponent.clickDismissButton();
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems, WRONG_ITEMS_COUNT_DISMISS_ERROR_MESSAGE);
        cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(0);
        cartItemRemovePopupComponent.clickCloseButton();
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems, WRONG_ITEMS_COUNT_CLOSE_ERROR_MESSAGE);
        cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(1);
        cartItemRemovePopupComponent.clickAcceptButton();
        cartPage.waitUntilCartItemsAppear();
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems - 1, WRONG_ITEMS_COUNT_ACCEPT_ERROR_MESSAGE);
        cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(0);
        cartItemRemovePopupComponent.clickAcceptButton();
        assertEquals(cartPage.getEmptyCartMessage(), "Ձեր զամբյուղում ոչինչ առկա չէ", WRONG_EMPTY_CART_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void closeCart() {
        HeaderPage headerPage = new HeaderPage(driver);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilEmptyCartMessageAppears();
        cartPage.clickCloseButton();
        assertFalse(cartPage.isCartDisplayed());
    }


    // helper methods
    private void addItemsToCart(int n) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "Չարենց");
        IntStream.range(0, n)
                .forEach((int i) -> searchResultsPage.getProduct(i).clickBuyButton());
    }

    private int calculateTotalPrice(CartPage cartPage) {
        return IntStream.range(0, cartPage.getItemsCount())
                .map((int i) -> cartPage.getProductCount(i) * cartPage.getProductPrice(i))
                .reduce(0, Integer::sum);
    }
}
