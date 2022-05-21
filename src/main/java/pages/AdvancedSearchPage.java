package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.AdvancedSearchPageConstants.AUTHOR_FIELD;
import static constants.locators.AdvancedSearchPageConstants.SEARCH_BUTTON;

public class AdvancedSearchPage extends BasePage{

    private By searchButton = By.className(SEARCH_BUTTON);
    private By authorField = By.id(AUTHOR_FIELD);

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    public void setAuthorKeyword(String keyword) {
        sendKeys(authorField, keyword);
    }
    public void clickEmptySearchButton() {
        click(searchButton);
    }

    public SearchResultsPage clickSearchButton() {
        click(searchButton);
        return new SearchResultsPage(driver);
    }

}
