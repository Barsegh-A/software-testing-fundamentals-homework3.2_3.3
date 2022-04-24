package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.locators.ProductPageConstants.*;
import static constants.urls.URL.PRODUCT_URL;

public class ProductPage extends BasePage{

    private By quantityField = By.id(QUANTITY);
    private By buyButton = By.id(BUY);
    private By addingToCartButton = By.className(ADDING_TO_CART);
    private By insufficientQuantityMessage = By.className(INSUFFICIENT_QUANTITY_MESSAGE);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage(WebDriver driver, String id) {
        super(driver);
        String url = String.format(PRODUCT_URL, id);
        driver.get(url);
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

    public void waitUntilAddingToCartDisappears() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(addingToCartButton));
    }
}
