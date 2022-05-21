package pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePage extends BasePage{

    private List<ProductItemComponent> items;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
