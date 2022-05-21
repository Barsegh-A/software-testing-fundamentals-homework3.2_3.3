package pages;

import org.openqa.selenium.WebDriver;

public abstract class BaseComponent extends BasePage{
    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
