package tests;

import config.WebDriverConfig;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class TestWebsiteExample extends WebDriverConfig {


    @Test
    public void loginTest() throws InterruptedException {

        driver.get("https://www.carpmix.pl/");
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

        WebElement login = driver.findElement(By.cssSelector("[title=\"Zaloguj się do swojego konta klienta\"]"));
        login.click();
        Thread.sleep(2000);

        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("passwd")).sendKeys("haslo");
        driver.findElement(By.id("SubmitLogin")).click();

        Assert.assertTrue(driver.findElement(By.id("center_column")).isDisplayed());

    }

    @Test
    public void registrationTest() throws InterruptedException {

        driver.get("https://www.carpmix.pl/logowanie?back=my-account");
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);


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

        driver.get("https://www.carpmix.pl/");
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

        driver.findElement(By.id("search_query_top")).sendKeys("Delphin Atoma FD-R Head 50x40");
        driver.findElement(By.cssSelector("#search_block_top .btn.button-search")).click();
        driver.findElement(By.xpath("//img[contains(@src,'delphin-atoma-fd-head-50x40.jpg')]")).click();

        // Test search
        // Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1")).isDisplayed());

        WebElement addbasket = driver.findElement(By.xpath("//span[contains(text(),'Dodaj do koszyka')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addbasket);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //add item to basket
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();


        String expected = "PODSUMOWANIE ZAKUPÓW"+"\n"+"Twój koszyk zawiera: 1 produkt";
        String actual = driver.findElement(By.xpath("//*[@id=\"cart_title\"]")).getText();
        Assert.assertEquals(expected, actual);


        // check item in the basket - print on the console
        WebElement basket = driver.findElement(By.xpath("//*[@id=\"cart_title\"]"));
        String baskettext = basket.getText();
        System.out.println(baskettext);


    }
    @Test
    public void checkBasketPrice() {

        driver.get("https://www.carpmix.pl/");
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

        //add first item to basket
        driver.findElement(By.id("search_query_top")).sendKeys("Delphin Atoma FD-R Head 50x40");
        driver.findElement(By.cssSelector("#search_block_top .btn.button-search")).click();
        driver.findElement(By.xpath("//img[contains(@src,'delphin-atoma-fd-head-50x40.jpg')]")).click();

        WebElement addbasket = driver.findElement(By.xpath("//span[contains(text(),'Dodaj do koszyka')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addbasket);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();

        //add second item to basket
        driver.findElement(By.id("search_query_top")).sendKeys("Delphin Magma M3 Medium Feeder 360 cm/120 g");
        driver.findElement(By.cssSelector("#search_block_top .btn.button-search")).click();
        driver.findElement(By.xpath("//img[contains(@src,'delphin-magma-m3-medium-feeder-360-cm120-g.jpg')]")).click();

        WebElement addbasket2 = driver.findElement(By.xpath("//span[contains(text(),'Dodaj do koszyka')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addbasket2);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();

        String expected = "367,30 zł";
        String actual = driver.findElement(By.xpath("//*[@id=\"total_price\"]")).getText();
        Assert.assertEquals(expected, actual);

        WebElement price = driver.findElement(By.xpath("//*[@id=\"total_price\"]"));
        String pricesTotal = price.getText();
        System.out.println(pricesTotal);

        WebElement basket = driver.findElement(By.xpath("//*[@id=\"cart_title\"]"));
        String baskettext = basket.getText();
        System.out.println(baskettext);

    }

    @Test
    public void removedItemBasket() throws InterruptedException {

        driver.get("https://www.carpmix.pl/");
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

        driver.findElement(By.id("search_query_top")).sendKeys("Delphin Atoma FD-R Head 50x40");
        driver.findElement(By.cssSelector("#search_block_top .btn.button-search")).click();
        driver.findElement(By.xpath("//img[contains(@src,'delphin-atoma-fd-head-50x40.jpg')]")).click();

        WebElement addbasket = driver.findElement(By.xpath("//span[contains(text(),'Dodaj do koszyka')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addbasket);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();


        //removed item from basket
        driver.findElement(By.xpath("//a[@id='2787_0_0_0']//i[@class='icon-trash']")).click();

        Thread.sleep(5000);

        String expected ="Twój koszyk jest pusty.";
        String actual = driver.findElement(By.xpath("//*[@id=\"emptyCartWarning\"]")).getText();
        Assert.assertEquals(expected, actual);

        WebElement basket = driver.findElement(By.xpath("//*[@id=\"emptyCartWarning\"]"));
        String baskettext = basket.getText();
        System.out.println(baskettext);


    }
}
