import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CartTest extends BaseTest{

    @BeforeMethod
    public void addBooksToCart(){

    }

    @Test
    public void addToCartFromSearch(){
        HeaderPage headerPage = new HeaderPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver, "բարև");
        ProductItemComponent item = searchResultsPage.getProduct(0);
        item.clickBuyButton();
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        cartPage.getItemsCount();

        cartPage.getProductPrice(0);
        cartPage.getProductCount(0);
        cartPage.clickProductCountIncreaseButton(0);
        cartPage.getTotalPrice();
    }


    @Test
    public void priceTest() {

    }
}
