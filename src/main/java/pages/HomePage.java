package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.HomePageConstants.NEW_PRODUCT_ITEM;

public class HomePage extends BasePage{

    private By newProductItem = By.className(NEW_PRODUCT_ITEM);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductItemComponent getProduct(int index) {
        return new ProductItemComponent(driver, getElement(newProductItem, index));
    }
}
