package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.SearchResultsPageConstants.*;
import static constants.urls.URL.SEARCH_URL;

public class SearchResultsPage extends BasePage{

    private By pageTitle = By.className(PAGE_TITLE);
    private By noResultMessage = By.className(NO_RESULT_MESSAGE);
    private By searchResultItem = By.className(SEARCH_RESULT_ITEM);
    private By searchResultItemPrice = By.className(SEARCH_RESULT_ITEM_PRICE);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage(WebDriver driver, String q) {
        super(driver);
        String url = String.format(SEARCH_URL, q);
        driver.get(url);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isNoResultMessageDisplayed() {
        return isElementDisplayed(noResultMessage);
    }

    public ProductPage clickSearchResultItem(int index){
        click(searchResultItem, index);
        return new ProductPage(driver);
    }

    public int getSearchResultItemPrice(int index){
        String text = getText(searchResultItemPrice, index);
        int price = Integer.parseInt(text.replaceAll("[^0-9]", ""));
        return price;
    }

    public int getSearchResultsCount() {
        return getElements(searchResultItem).size();
    }

}
