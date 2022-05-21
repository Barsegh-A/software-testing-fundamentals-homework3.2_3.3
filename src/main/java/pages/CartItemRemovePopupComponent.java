package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.CartItemRemovePopupComponentConstants.*;

public class CartItemRemovePopupComponent extends BaseComponent{

    private By acceptButton = By.className(ACCEPT_BUTTON);
    private By dismissButton = By.className(DISMISS_BUTTON);
    private By closeButton = By.className(CLOSE_BUTTON);

    public CartItemRemovePopupComponent(WebDriver driver) {
        super(driver);
    }

    public void clickAcceptButton(){
        click(acceptButton);
    }

    public void clickDismissButton(){
        click(dismissButton);
    }

    public void clickCloseButton(){
        click(closeButton);
    }
}
