package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static constants.locators.ProductPageConstants.*;

public class ProductPage extends BasePage{

    private By quantityField = By.id(QUANTITY);
    private By buyButton = By.id(BUY);
    private By insufficientQuantityMessage = By.className(INSUFFICIENT_QUANTITY_MESSAGE);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void setQuantity(String n) {
        WebElement element = driver.findElement(quantityField);
        element.clear();
        element.sendKeys(n);
    }

    public void clickBuyButton() {
        driver.findElement(buyButton).click();
    }

    public boolean isInsufficientQuantityMessageDisplayed() {
        return driver.findElement(insufficientQuantityMessage).isDisplayed();
    }
}
