import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static constants.messages.ErrorMessages.*;
import static org.testng.Assert.assertEquals;

public class AddToCartTest extends BaseTest{
    @Test
    public void emptyCartMessage(){
        HeaderPage headerPage = new HeaderPage(driver);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilEmptyCartMessageAppears();
        assertEquals(cartPage.getEmptyCartMessage(), "Ձեր զամբյուղում ոչինչ առկա չէ", WRONG_EMPTY_CART_ERROR_MESSAGE);
    }

    @Test
    //TODO fix or remove move target out of bounds
    public void addToCartFromHome(){
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 0, CART_ITEMS_COUNT_ERROR_MESSAGE);
        ProductItemComponent item = homePage.getProduct(0);
        item.clickBuyButton();
        String name = item.getName();
        int price = item.getPrice();
        softAssert.assertTrue(headerPage.getMessage().contains(name), WRONG_PRODUCT_NAME_ON_MESSAGE_ERROR_MESSAGE);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 1, CART_ITEMS_COUNT_ERROR_MESSAGE);
        CartPage cartPage = headerPage.clickCartButton();
        String nameOnCart = cartPage.getProductName(0);
        int priceOnCart = cartPage.getProductPrice(0);
        softAssert.assertEquals(name, nameOnCart, WRONG_PRODUCT_NAME_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(price, priceOnCart, WRONG_PRODUCT_PRICE_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(1, cartPage.getProductCount(0), WRONG_PRODUCT_COUNT_ON_CART_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void addToCartFromSearch(){
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "բարև");
        softAssert.assertEquals(headerPage.getCartItemsCount(), 0, CART_ITEMS_COUNT_ERROR_MESSAGE);
        ProductItemComponent item = searchResultsPage.getProduct(0);
        item.clickBuyButton();
        String name = item.getName();
        int price = item.getPrice();
        softAssert.assertTrue(headerPage.getMessage().contains(name), WRONG_PRODUCT_NAME_ON_MESSAGE_ERROR_MESSAGE);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 1, CART_ITEMS_COUNT_ERROR_MESSAGE);
        CartPage cartPage = headerPage.clickCartButton();
        String nameOnCart = cartPage.getProductName(0);
        int priceOnCart = cartPage.getProductPrice(0);
        softAssert.assertEquals(name, nameOnCart, WRONG_PRODUCT_NAME_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(price, priceOnCart, WRONG_PRODUCT_PRICE_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(1, cartPage.getProductCount(0), WRONG_PRODUCT_COUNT_ON_CART_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void addToCartFromProductPage(){
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        ProductPage productPage = new ProductPage(driver, "61091");
        softAssert.assertEquals(headerPage.getCartItemsCount(), 0, CART_ITEMS_COUNT_ERROR_MESSAGE);
        productPage.clickIncreaseQuantityButton();
        productPage.clickBuyButton();
        productPage.waitUntilAddingToCartDisappears();
        String name = productPage.getProductName();
        int price = productPage.getProductPrice();
        softAssert.assertTrue(headerPage.getMessage().contains(name), WRONG_PRODUCT_NAME_ON_MESSAGE_ERROR_MESSAGE);
        softAssert.assertEquals(headerPage.getCartItemsCount(), 2, CART_ITEMS_COUNT_ERROR_MESSAGE);
        CartPage cartPage = headerPage.clickCartButton();
        String nameOnCart = cartPage.getProductName(0);
        int priceOnCart = cartPage.getProductPrice(0);
        softAssert.assertEquals(name, nameOnCart, WRONG_PRODUCT_NAME_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(price, priceOnCart, WRONG_PRODUCT_PRICE_ON_CART_ERROR_MESSAGE);
        softAssert.assertEquals(2, cartPage.getProductCount(0), WRONG_PRODUCT_COUNT_ON_CART_ERROR_MESSAGE);
        softAssert.assertAll();
    }
}
