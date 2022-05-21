import org.testng.annotations.Test;
import pages.CartPage;
import pages.HeaderPage;

import static constants.messages.ErrorMessages.WRONG_EMPTY_CART_ERROR_MESSAGE;
import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest{
    @Test
    public void emptyCartMessage(){
        HeaderPage headerPage = new HeaderPage(driver);
        CartPage cartPage = headerPage.clickCartButton();
        cartPage.waitUntilCartPageAppears();
        assertEquals(cartPage.getEmptyCartMessage(), "Ձեր զամբյուղում ոչինչ առկա չէ", WRONG_EMPTY_CART_ERROR_MESSAGE);
    }
}
