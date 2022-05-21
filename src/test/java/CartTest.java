import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.ProductItemComponent;
import pages.SearchResultsPage;

public class CartTest extends BaseTest{

    @BeforeMethod
    public void addBooksToCart(){

    }

    @Test
    public void addToCartFromSearch(){
        HeaderPage headerPage = new HeaderPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "բարև");
        ProductItemComponent item = searchResultsPage.getProduct(0);
    }


    @Test
    public void priceTest() {

    }
}
