package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static constants.locators.ProductPageConstants.*;
import static constants.urls.URL.PRODUCT_URL;
import static utils.TestUtils.getNumbers;

public class ProductPage extends BasePage{

    private By quantityField = By.id(QUANTITY_FIELD);
    private By decreaseQuantityButton = By.className(DECREASE_BUTTON);
    private By increaseQuantityButton = By.className(INCREASE_BUTTON);
    private By buyButton = By.className(BUY_BUTTON);
    private By addToCartButton = By.id(ADD_TO_CART_BUTTON);
    private By addingToCartButton = By.className(ADDING_TO_CART_BUTTON);
    private By insufficientQuantityMessage = By.className(INSUFFICIENT_QUANTITY_MESSAGE);
    private By productPrice = By.className(PRODUCT_PRICE);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage(WebDriver driver, String id) {
        super(driver);
        String url = String.format(PRODUCT_URL, id);
        driver.get(url);
    }

    public void setQuantity(int n) {
        clearAndSendKeys(quantityField, String.valueOf(n));
    }

    public int getQuantity() {
        return Integer.parseInt(getValue(quantityField));
    }

    public int getProductPrice(){
        String text = getText(productPrice);
        return getNumbers(text);
    }

    public void clickDecreaseQuantityButton() {
        click(decreaseQuantityButton);
    }

    public void clickIncreaseQuantityButton() {
        click(increaseQuantityButton);
    }

    public void clickBuyButton() {
        click(buyButton);
    }

    public boolean isInsufficientQuantityMessageDisplayed() {
        return isElementDisplayed(insufficientQuantityMessage);
    }

    public void waitUntilAddingToCartDisappears() {
        waitUntilElementDisappears(addingToCartButton);
    }
}
