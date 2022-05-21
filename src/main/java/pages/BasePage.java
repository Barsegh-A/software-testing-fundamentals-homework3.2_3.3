package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.WaitUtils.getShortWaitUtils;
import static utils.WaitUtils.getWaitUtils;

public abstract class BasePage {
    protected WebDriver driver;
    protected SearchContext searchContext;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.searchContext = driver;
    }

    protected WebElement getElement(By by) {
        return searchContext.findElement(by);
    }

    protected WebElement getElement(By by, int index) {
        return getElements(by).get(index);
    }

    protected List<WebElement> getElements(By by) {
        return searchContext.findElements(by);
    }

    protected void click(By by) {
        waitUntilElementIsClickable(by).click();
    }

    protected void click(By by, int index) {
        WebElement element = getElements(by).get(index);
        element.click();
    }

    protected void sendKeys(By by, String keyword) {
        getElement(by).sendKeys(keyword);
    }

    protected void clearAndSendKeys(By by, String keyword) {
        WebElement element = getElement(by);
        element.clear();
        element.sendKeys(keyword);
    }

    protected String getText(WebElement element) {
        waitUntilElementAppears(element);
        return element.getText();
    }

    protected String getText(By by) {
        waitUntilElementAppears(by);
        return getElement(by).getText();
    }

    protected String getText(By by, int index) {
        return getElements(by).get(index).getText();
    }

    protected String getAttribute(By by, String attribute) {
        return getElement(by).getAttribute(attribute);
    }

    protected String getAttribute(By by, int index, String attribute) {
        return getElements(by).get(index).getAttribute(attribute);
    }

    protected String getValue(By by) {
        return getAttribute(by, "value");
    }

    protected String getValue(By by, int index) {
        return getAttribute(by, index, "value");
    }

    protected boolean isElementDisplayed(By by) {
        try {
            WebElement element = getElement(by);
            getShortWaitUtils(driver).waitUntilElementIsVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementEnabled(By by) {
        return getElement(by).isEnabled();
    }

    protected WebElement waitUntilElementAppears(By by) {
        return getWaitUtils(driver).waitUntilElementIsVisible(by);
    }

    protected WebElement waitUntilElementAppears(WebElement element) {
        return getWaitUtils(driver).waitUntilElementIsVisible(element);
    }

    protected void waitUntilElementDisappears(By by) {
        getWaitUtils(driver).waitUntilElementIsInvisible(by);
    }

    protected WebElement waitUntilElementIsClickable(By by) {
        return waitUntilElementIsClickable(getElement(by));
    }

    protected WebElement waitUntilElementIsClickable(WebElement element) {
        return getWaitUtils(driver).waitUntilElementIsClickable(element);
    }

    protected void waitUntilAttributeIs(By by, String attribute, String value) {
        getWaitUtils(driver).waitUntilAttributeIs(getElement(by), attribute, value);
    }
}
