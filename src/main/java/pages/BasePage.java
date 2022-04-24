package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected WebElement getElement(By by) {
        return driver.findElement(by);
    }

    protected List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    protected void click(By by) {
        waitUntilElementIsClickable(by).click();
    }

    protected void click(By by, int index) {
        WebElement element = getElements(by).get(index);
        element.click();
    }

    protected void sendKeys(By by, String keyword){
        getElement(by).sendKeys(keyword);
    }

    protected void clearAndSendKeys(By by, String keyword){
        WebElement element = getElement(by);
        element.clear();
        element.sendKeys(keyword);
    }

    protected String getText(By by) {
        return getElement(by).getText();
    }

    protected String getText(By by, int index) {
        return getElements(by).get(index).getText();
    }

    protected String getValue(By by) {
        return getElement(by).getAttribute("value");
    }

    protected boolean isElementDisplayed(By by){
        return getElement(by).isDisplayed();
    }

    protected void waitUntilElementDisappears(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement waitUntilElementIsClickable(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
    }
}
