package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.ItemPageConstants.ITEM;
import static constants.locators.ItemPageConstants.ITEM_PRICE;

public class ItemPage extends BasePage{

    private By item = By.className(ITEM);
    private By itemPrice = By.className(ITEM_PRICE);

    public ItemPage(WebDriver driver) {
        super(driver);
    }


}
