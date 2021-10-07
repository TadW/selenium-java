package config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConfig {


    protected ChromeDriver driver;

    @Before
        public void before()  {

            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();

        }

        @After
        public void after(){
        driver.close();
        driver.quit();

        }
}
