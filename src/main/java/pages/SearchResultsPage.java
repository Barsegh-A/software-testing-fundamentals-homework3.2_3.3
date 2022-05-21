package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static constants.locators.SearchResultsPageConstants.*;
import static constants.urls.URL.SEARCH_URL;
import static utils.TestUtils.getNumbers;

public class SearchResultsPage extends BasePage{

    private By pageTitle = By.className(PAGE_TITLE);
    private By noResultMessage = By.className(NO_RESULT_MESSAGE);
    private By searchResultItem = By.className(SEARCH_RESULT_ITEM);
    private By searchResultItemPrice = By.className(SEARCH_RESULT_ITEM_PRICE);
    private By searchResultProductName = By.className(SEARCH_RESULT_PRODUCT_NAME);
    private By buyButton = By.className(BUY_BUTTON);


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
        return getNumbers(text);
    }

    public String getSearchResultProductName(int index){
        return getText(searchResultProductName, index);
    }

    public int getSearchResultsCount() {
        return getElements(searchResultItem).size();
    }

    public void clickBuyButton(int index) {
        Actions actions = new Actions(driver);
        WebElement element = getElements(searchResultItem).get(index);
        actions.moveToElement(element);
        actions.moveToElement(element.findElement(buyButton));
        actions.click().build().perform();
    }

    public void waitUntilBuyButtonIsClickable() {
        waitUntilElementIsClickable(buyButton);
    }

    public void waitUntilAddedAppears() {
        waitUntilAttributeIs(buyButton, "title", "Ավելացնել զամբյուղի մեջ");
    }
}
