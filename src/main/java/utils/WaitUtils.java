package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 120;
    private static final int SHORT_TIMEOUT = 5;
    private static WebDriverWait wait;

    private WaitUtils() {
    }

    private static WebDriverWait getInstance(WebDriver driver, int timeout) {
        return (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class);
    }

    public static WaitUtils getWaitUtils(WebDriver driver) {
        wait = getInstance(driver, TIMEOUT);
        return new WaitUtils();
    }

    public static WaitUtils getLongWaitUtils(WebDriver driver) {
        wait = getInstance(driver, LONG_TIMEOUT);
        return new WaitUtils();
    }

    public static WaitUtils getShortWaitUtils(WebDriver driver) {
        wait = getInstance(driver, SHORT_TIMEOUT);
        return new WaitUtils();
    }

    public WebElement waitUntilElementIsVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitUntilElementIsVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsInvisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitUntilElementIsInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement waitUntilElementIsClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilAttributeIs(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }
}
