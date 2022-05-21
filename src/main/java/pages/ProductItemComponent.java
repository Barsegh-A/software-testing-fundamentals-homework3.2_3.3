package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static constants.locators.ProductItemComponentConstants.*;
import static utils.TestUtils.getNumbers;

public class ProductItemComponent extends BaseComponent{

    private By itemPrice = By.className(ITEM_PRICE);
    private By itemName = By.className(ITEM_NAME);
    private By itemAuthor = By.className(ITEM_AUTHOR);
    private By buyButton = By.className(BUY_BUTTON);

    private WebElement rootElement;

    public ProductItemComponent(WebDriver driver) {
        super(driver);
    }

    public ProductItemComponent(WebDriver driver, WebElement element) {
        this(driver);
        this.rootElement = element;
        this.searchContext = rootElement;
    }

    public String getName(){
        return getText(itemName);
    }

    public String getAuthor(){
        return getText(itemAuthor);
    }

    public int getPrice(){
        String text = getText(getElement(itemPrice));
        return getNumbers(text);
    }

    public void clickBuyButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(rootElement);
        actions.moveToElement(getElement(buyButton));
        actions.click()
                .build()
                .perform();
        waitUntilAddedAppears();
    }

    public void waitUntilAddedAppears() {
        waitUntilAttributeIs(buyButton, "title", "Ավելացնել զամբյուղի մեջ");
    }



}
