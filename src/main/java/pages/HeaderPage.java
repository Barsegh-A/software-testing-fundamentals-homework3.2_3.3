package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.HeaderPageConstants.*;

public class HeaderPage extends BasePage{

    private By searchField = By.id(SEARCH_FIELD);
    private By searchButton = By.xpath(SEARCH_BUTTON);
    private By cartButton = By.className(CART_BUTTON);
    private By cartItemsCount = By.className(CART_ITEMS_COUNT);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void setSearchKeyword(String keyword) {
        driver.findElement(searchField).sendKeys(keyword);
    }

    public SearchResultsPage clickSearchButton(){
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }

    public void clickCartButton(){
        driver.findElement(cartButton).click();
    }

    public int getCartItemsCount(){
        String count = driver.findElement(cartItemsCount).getText();
        if(count.isEmpty()){
            return 0;
        }
        return Integer.parseInt(count);
    }
}
