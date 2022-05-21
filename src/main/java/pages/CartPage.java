package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.CartPageConstants.CART;
import static constants.locators.CartPageConstants.EMPTY_CART;

public class CartPage extends BasePage{

    private By emptyCart = By.className(EMPTY_CART);
    private By cart = By.id(CART);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCart);
    }

    public void waitUntilCartPageAppears() {
        waitUntilElementAppears(emptyCart);
    }
}
