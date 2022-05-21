import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HeaderPage;
import pages.ItemPage;
import pages.SearchResultsPage;

public class CartTest extends BaseTest{

    @BeforeMethod
    public void addBooksToCart(){

    }

    @Test
    public void addToCartFromSearch(){
        HeaderPage headerPage = new HeaderPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "բարև");
        ItemPage item = searchResultsPage.getProduct(0);
    }


    @Test
    public void priceTest() {

    }
}
