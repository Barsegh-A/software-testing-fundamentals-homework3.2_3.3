package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.SearchResultsPageConstants.*;

public class SearchResultsPage extends BasePage{

    private By pageTitle = By.className(PAGE_TITLE);
    private By noResultMessage = By.className(NO_RESULT_MESSAGE);
    private By searchResultItem = By.className(SEARCH_RESULT_ITEM);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isNoResultMessageDisplayed() {
        return driver.findElement(noResultMessage).isDisplayed();
    }

    public ProductPage clickSearchResultItem(int index){
        driver.findElements(searchResultItem).get(index).click();
        return new ProductPage(driver);
    }

}
