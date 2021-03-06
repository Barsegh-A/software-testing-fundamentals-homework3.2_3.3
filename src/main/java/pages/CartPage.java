package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.CartPageConstants.*;
import static utils.TestUtils.getNumbers;

public class CartPage extends BasePage{

    private By emptyCart = By.className(EMPTY_CART);
    private By cart = By.id(CART);
    private By cartCloseButton = By.id(CART_CLOSE_BUTTON);
    private By cartItem = By.cssSelector(CART_ITEM);
    private By productName = By.className(PRODUCT_NAME);
    private By productCount = By.cssSelector(PRODUCT_COUNT);
    private By productPrice = By.className(PRODUCT_PRICE);
    private By productDeleteButton = By.className(PRODUCT_DELETE_BUTTON);
    private By productCountDecreaseButton = By.className(PRODUCT_COUNT_DECREASE_BUTTON);
    private By productCountIncreaseButton = By.className(PRODUCT_COUNT_INCREASE_BUTTON);

    private By totalPrice = By.cssSelector(TOTAL_PRICE);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilCartPageAppears() {
        waitUntilElementAppears(cart);
    }

    public void waitUntilEmptyCartMessageAppears() {
        waitUntilElementAppears(emptyCart);
    }

    public void waitUntilCartItemsAppear() {
        waitUntilElementAppears(getElement(cartItem, 0));
    }


    public boolean isCartDisplayed() {
        return isElementDisplayed(cart);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCart);
    }

    public int getItemsCount(){
        return getElements(cartItem).size();
    }

    public String getProductName(int index) {
        return getText(productName, index);
    }

    public int getProductCount(int index) {
        return Integer.parseInt(getValue(productCount, index));
    }

    public int getProductPrice(int index) {
        return getNumbers(getText(productPrice, index));
    }

    public int getTotalPrice() {
        return getNumbers(getText(totalPrice));
    }

    public CartItemRemovePopupComponent clickProductDeleteButton(int index) {
        click(productDeleteButton, index);
        return new CartItemRemovePopupComponent(driver);
    }

    public void clickProductCountDecreaseButton(int index) {
        click(productCountDecreaseButton, index);
    }

    public void clickProductCountIncreaseButton(int index) {
        click(productCountIncreaseButton, index);
    }

    public void clickCloseButton() {
        click(cartCloseButton);
    }
}
