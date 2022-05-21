package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static constants.locators.ItemPageConstants.ITEM_PRICE;
import static utils.TestUtils.getNumbers;

public class ItemPage extends BasePage{

    private By itemPrice = By.className(ITEM_PRICE);

    private WebElement rootElement;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage(WebDriver driver, WebElement element) {
        this(driver);
        this.rootElement = element;
        this.searchContext = rootElement;
    }

    public int getPrice(){
        String text = getText(itemPrice);
        return getNumbers(text);
    }



}
