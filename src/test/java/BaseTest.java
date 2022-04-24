import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static constants.urls.URL.BASE_URL;

public abstract class BaseTest {
    public static WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public static void initDriver(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSUT(){
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                String timestamp = String.valueOf(new Date().getTime());
                Files.move(screenshot, new File("src/test/resources/screenshots/" + result.getName() + "_" + timestamp + ".png"));
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
