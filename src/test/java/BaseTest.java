import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    public static WebDriver driver;
//    public static String url = "https://music.yandex.ru/";
    public static String url = "https://books.am";

    @BeforeSuite
    public static void initDriver() throws MalformedURLException {
        String browser = "chrome";
//        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSUT(){
        driver.get(url);
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("src/test/resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    @AfterSuite
    public static void tearDown(){
        driver.quit();
    }
}
