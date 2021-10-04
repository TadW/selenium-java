package config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverConfig {


    protected ChromeDriver driver;

    @Before
        public void before() throws InterruptedException {

            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.carpmix.pl/");
            driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

            WebElement login = driver.findElement(By.cssSelector("[title=\"Zaloguj się do swojego konta klienta\"]"));
            login.click();
            Thread.sleep(2000);

        }

        @After
        public void after(){
        driver.close();

        }
}
