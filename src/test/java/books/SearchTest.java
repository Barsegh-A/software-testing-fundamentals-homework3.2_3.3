package books;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.stream.IntStream;

import static constants.messages.ErrorMessages.*;
import static org.testng.Assert.*;

public class SearchTest extends BaseTest {

    @Test
    public void invalidSearch() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("asdf");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        assertTrue(searchResultsPage.isNoResultMessageDisplayed(), NO_SEARCH_RESULT_ERROR_MESSAGE);
    }

    // Actually the search logic is a bit different on books.am. I just make the assumption that either the title or the author should contain the search term.
    @Test
    public void validSearch() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        String searchTerm = "Դոստոևսկի";
        headerPage.setSearchKeyword(searchTerm);
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        IntStream.range(0, searchResultsPage.getSearchResultsCount())
                .forEach((int i) -> {
                    ProductItemComponent item = searchResultsPage.getProduct(i);
                    boolean titleContainsSearchTerm = item.getName().toLowerCase().contains(searchTerm.toLowerCase());
                    boolean authorContainsSearchTerm = item.getAuthor().toLowerCase().contains(searchTerm.toLowerCase());
                    softAssert.assertTrue(titleContainsSearchTerm || authorContainsSearchTerm, WRONG_SEARCH_BEHAVIOUR_ERROR_MESSAGE + item.getName() + ", " + item.getAuthor());
                });
        softAssert.assertAll();
    }

    @Test
    public void searchFlow() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.setSearchKeyword("Դորիան");
        SearchResultsPage searchResultsPage = headerPage.clickSearchButton();
        ProductPage productPage = searchResultsPage.clickSearchResultItem(1);
        productPage.setQuantity(20);
        productPage.clickBuyButton();
        assertTrue(productPage.isInsufficientQuantityMessageDisplayed(), INSUFFICIENT_QUANTITY_MESSAGE_ERROR_MESSAGE);
    }

    @Test
    public void searchDefaultState() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        softAssert.assertFalse(headerPage.isSearchButtonEnabled(), WRONG_INITIAL_STATE_SEARCH_ERROR_MESSAGE);
        softAssert.assertEquals(headerPage.getSearchFieldPlaceholder(), "Որոնել", WRONG_PLACEHOLDER_SEARCH_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void searchSuggestionsPositive() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        String searchTerm = "հայա";
        headerPage.setSearchKeyword(searchTerm);
        softAssert.assertTrue(headerPage.isSearchSuggestionsViewPresent(), MISSING_SEARCH_SUGGESTION_ERROR_MESSAGE);
        IntStream.range(0, headerPage.getSearchSuggestionsCount())
                .forEach((int i) -> {
                    String searchSuggestion = headerPage.getSearchSuggestion(i);
                    softAssert.assertTrue(searchSuggestion.toLowerCase().contains(searchTerm), WRONG_SEARCH_SUGGESTION_ERROR_MESSAGE + searchSuggestion);
                });
        String searchSuggestion = headerPage.getSearchSuggestion(1);
        SearchResultsPage searchResultsPage = headerPage.clickSearchSuggestion(1);
        softAssert.assertTrue(searchResultsPage.getPageTitle().toLowerCase().contains(searchSuggestion.toLowerCase()), WRONG_SEARCH_PAGE_TITLE_SUGGESTION_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void searchSuggestionsNegative() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        headerPage.setSearchKeyword("հայհայա");
        assertFalse(headerPage.isSearchSuggestionsViewPresent(), EXISTING_SEARCH_SUGGESTION_ERROR_MESSAGE);
    }

    @Test
    public void emptyAdvancedSearch() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        AdvancedSearchPage advancedSearchPage = headerPage.clickAdvancedSearchButton();
        advancedSearchPage.clickEmptySearchButton();
        assertEquals(headerPage.getMessage(), "Enter a search term and try again.", EMPTY_ADVANCED_SEARCH_ERROR_MESSAGE);
    }

    @Test
    public void searchByAuthor() {
        SoftAssert softAssert = new SoftAssert();
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        AdvancedSearchPage advancedSearchPage = headerPage.clickAdvancedSearchButton();
        String searchTerm = "Օտյան";
        advancedSearchPage.setAuthorKeyword(searchTerm);
        SearchResultsPage searchResultsPage = advancedSearchPage.clickSearchButton();
        softAssert.assertEquals(searchResultsPage.getPageTitle().toLowerCase(), "Կատալոգի ընդլայնված որոնում".toLowerCase(), WRONG_ADVANCED_SEARCH_PAGE_TITLE_ERROR_MESSAGE);
        IntStream.range(0, searchResultsPage.getSearchResultsCount())
                .forEach((int i) -> {
                    ProductItemComponent item = searchResultsPage.getProduct(i);
                    boolean authorContainsSearchTerm = item.getAuthor().toLowerCase().contains(searchTerm.toLowerCase());
                    softAssert.assertTrue(authorContainsSearchTerm, WRONG_ADVANCED_SEARCH_BEHAVIOUR_ERROR_MESSAGE + item.getAuthor());
                });
        softAssert.assertAll();
    }

    @Test
    public void invalidSearchByAuthor() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.waitUntilSearchCategoryDropdownButtonIsClickable();
        AdvancedSearchPage advancedSearchPage = headerPage.clickAdvancedSearchButton();
        advancedSearchPage.setAuthorKeyword("Օտյա");
        SearchResultsPage searchResultsPage = advancedSearchPage.clickSearchButton();
        assertTrue(searchResultsPage.isNoResultMessageDisplayed(), NO_SEARCH_RESULT_ERROR_MESSAGE);
    }

}
