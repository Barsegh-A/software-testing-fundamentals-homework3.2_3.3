package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.HeaderPageConstants.*;

public class HeaderPage extends BasePage{

    private By searchField = By.id(SEARCH_FIELD);
    private By searchButton = By.xpath(SEARCH_BUTTON);
    private By cartItemsCount = By.className(CART_ITEMS_COUNT);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void setSearchKeyword(String keyword) {
        sendKeys(searchField, keyword);
    }

    public SearchResultsPage clickSearchButton(){
        click(searchButton);
        return new SearchResultsPage(driver);
    }

    public int getCartItemsCount(){
        String count = getText(cartItemsCount);
        if(count.isEmpty()){
            return 0;
        }
        return Integer.parseInt(count);
    }
}
