package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.HeaderPageConstants.*;

public class HeaderPage extends BasePage {

    private By searchField = By.id(SEARCH_FIELD);
    private By searchButton = By.xpath(SEARCH_BUTTON);
    private By searchCategoryDropDownButton = By.className(SEARCH_CATEGORY_DROPDOWN_BUTTON);
    private By searchSuggestions = By.id(SEARCH_SUGGESTIONS);
    private By searchSuggestionItem = By.className(SEARCH_SUGGESTION_ITEM);
    private By advancedSearchButton = By.className(ADVANCED_SEARCH_BUTTON);
    private By cartItemsCount = By.className(CART_ITEMS_COUNT);

    private By cartButton = By.className(CART_BUTTON);
    private By message = By.cssSelector(MESSAGE);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilSearchCategoryDropdownButtonIsClickable() {
        waitUntilElementAppears(searchCategoryDropDownButton);
        waitUntilElementIsClickable(searchCategoryDropDownButton);
    }

    public void waitUntilSearchSuggestionsAppear() {
        waitUntilElementAppears(searchSuggestions);
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

    public AdvancedSearchPage clickAdvancedSearchButton() {
        click(advancedSearchButton);
        return new AdvancedSearchPage(driver);
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
        if(!isElementDisplayed(cartItemsCount)){
            return 0;
        }
        String text = getText(cartItemsCount);
        if(!text.isEmpty()){
            return Integer.parseInt(text);
        } else {
            return 0;
        }
    }

    public String getMessage(){
        return getText(message);
    }

    public SearchResultsPage clickSearchSuggestion(int index) {
        click(searchSuggestionItem, index);
        return new SearchResultsPage(driver);
    }

    public int getSearchSuggestionsCount() {
        return getElements(searchSuggestionItem).size();
    }

    public String getSearchSuggestion(int index) {
        return getText(searchSuggestionItem, index);
    }
}
