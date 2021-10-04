package tests;

import config.WebDriverConfig;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestWebsiteExample extends WebDriverConfig{


    @Test
    public void loginTest()   {

        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("passwd")).sendKeys("haslo");
        driver.findElement(By.id("SubmitLogin")).click();

        Assert.assertTrue(driver.findElement(By.id("center_column")).isDisplayed());

    }

    @Test
    public void registrationTest() throws InterruptedException {

         driver.findElement(By.id("email_create")).sendKeys("test@email.com");
         driver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(2000);

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("x");
        driver.findElement(By.id("customer_lastname")).sendKeys("y");
        driver.findElement(By.id("confirm_email")).sendKeys("test@email.com");
        driver.findElement(By.id("passwd")).sendKeys("haslo");
        driver.findElement(By.id("confirm_passwd")).sendKeys("haslo");
        driver.findElement(By.id("agreement_1")).click();
        driver.findElement((By.id("agreement_2"))).click();


    }
    @Test
    public void searchCheck() {

        driver.findElement(By.id("search_query_top")).sendKeys("Delphin Atoma FD-R Head 50x40");

        driver.findElement(By.cssSelector("#search_block_top .btn.button-search")).click();
        driver.findElement(By.xpath("//img[contains(@src,'delphin-atoma-fd-head-50x40.jpg')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1")).isDisplayed());






    }
}
