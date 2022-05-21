package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.CartPageConstants.*;

public class CartPage extends BasePage{

    private By emptyCart = By.className(EMPTY_CART);
    private By cart = By.id(CART);
    private By productName = By.className(PRODUCT_NAME);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCart);
    }

    public void waitUntilCartPageAppears() {
        waitUntilElementAppears(emptyCart);
    }

    public String getProductName(int index) {
        return getText(productName, index);
    }
}
