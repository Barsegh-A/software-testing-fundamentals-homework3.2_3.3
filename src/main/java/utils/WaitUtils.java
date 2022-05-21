package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int SHORT_TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 120;
    private static WebDriverWait wait;

    private WaitUtils() {
    }

    private static WebDriverWait getInstance(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WaitUtils getWaitUtils(WebDriver driver) {
        wait = getInstance(driver, SHORT_TIMEOUT);
        return new WaitUtils();
    }

    public static WaitUtils getLongWaitUtils(WebDriver driver) {
        wait = getInstance(driver, LONG_TIMEOUT);
        return new WaitUtils();
    }

    public WebElement waitUntilElementIsVisible(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement waitElementToBeClickable(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
