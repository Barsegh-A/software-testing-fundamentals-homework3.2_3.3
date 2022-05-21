package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.HeaderPageConstants.*;

public class HeaderPage extends BasePage {

    private By searchField = By.id(SEARCH_FIELD);
    private By searchButton = By.xpath(SEARCH_BUTTON);

    private By searchSuggestions = By.id(SEARCH_SUGGESTIONS);
    private By cartItemsCount = By.className(CART_ITEMS_COUNT);

    private By cartButton = By.className(CART_BUTTON);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void setSearchKeyword(String keyword) {
        sendKeys(searchField, keyword);
    }

    public String getSearchFieldPlaceholder() {
        return getAttribute(searchField, "placeholder");
    }

    public SearchResultsPage clickSearchButton() {
        click(searchButton);
        return new SearchResultsPage(driver);
    }

    public CartPage clickCartButton() {
        click(cartButton);
        return new CartPage(driver);
    }

    public boolean isSearchButtonEnabled() {
        return isElementEnabled(searchButton);
    }

    public boolean isSearchSuggestionsViewPresent() {
        return isElementDisplayed(searchSuggestions);
    }

    public int getCartItemsCount() {
        String count = getText(cartItemsCount);
        if (count.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(count);
    }
}
