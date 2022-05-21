import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.stream.IntStream;

import static constants.messages.ErrorMessages.WRONG_EMPTY_CART_ERROR_MESSAGE;
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
        softAssert.assertEquals(cartPage.getItemsCount(), 2, "Wrong number of products");
        cartPage.clickProductCountIncreaseButton(1);
        int secondItemCount = cartPage.getProductCount(1);
        softAssert.assertEquals(secondItemCount, 2, "Product Count Increase button does not work");
        cartPage.clickProductCountDecreaseButton(1);
        secondItemCount = cartPage.getProductCount(1);
        softAssert.assertEquals(secondItemCount, 1, "Product Count Decrease button does not work");
        softAssert.assertAll();
    }

    @Test
    public void totalPrice() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        addItemsToCart(3);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), "Total price is not calculated correctly initially");
        cartPage.clickProductCountIncreaseButton(2);
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), "Total price is not calculated correctly after product count increase");
        cartPage.clickProductCountDecreaseButton(2);
        cartPage.waitUntilCartPageAppears();
        softAssert.assertEquals(cartPage.getTotalPrice(), calculateTotalPrice(cartPage), "Total price is not calculated correctly after product count decrease");
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
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems, "Items count changed after dismiss");
        cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(0);
        cartItemRemovePopupComponent.clickCloseButton();
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems, "Items count changed after close");
        cartItemRemovePopupComponent = cartPage.clickProductDeleteButton(1);
        cartItemRemovePopupComponent.clickAcceptButton();
        cartPage.waitUntilCartItemsAppear();
        softAssert.assertEquals(cartPage.getItemsCount(), initialItems - 1, "Items count did not change after delete");
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
