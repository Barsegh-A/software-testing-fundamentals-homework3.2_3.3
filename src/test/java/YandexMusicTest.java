import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class YandexMusicTest extends BaseTest{
//    @Test
    public void test(){
        driver.findElement(By.className("payment-plus__header-close")).click();
        driver.findElement(By.className("d-search__button")).click();
        driver.findElement(By.className("d-input__field")).sendKeys("garun e");
        driver.findElement(By.className("d-suggest__item-track")).click();
        driver.findElement(By.className("button-play__type_albumtrack")).click();
        driver.findElement(By.className("button2__label")).click();
    }

    @Test
    public void books(){
        driver.findElement(By.id("search")).sendKeys("Դորիան");
        driver.findElement(By.className("icon_search")).click();
        driver.findElement(By.xpath("//*[@id=\"search_mini_form\"]/button")).click();

        driver.findElements(By.className("product_inner")).get(1).click();
        driver.findElement(By.className("increase_btn")).click();
        driver.findElement(By.id("product-addtocart-button")).click();

        driver.findElement(By.className("shopcart_block")).click();
        driver.findElement(By.className("delete_btn")).click();
        driver.findElement(By.className("action-accept")).click();
        driver.findElement(By.id("qs-option-0")).click();

    }

    @Test
    public void booksSignIn(){
        driver.findElement(By.className("icon_user")).click();
//        driver.findElement(By.linkText("Գրանցվել")).click();
        driver.findElement(By.id("email")).sendKeys("test@test.com");
        driver.findElement(By.id("pass")).sendKeys("qwerty");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.className("message-error")).isDisplayed();

    }
}
