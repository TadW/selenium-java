package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginFeature  {

    WebDriver driver = null;


    @Given("user is on login page")
    public void user_is_on_login_page() {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.carpmix.pl/");
        WebElement login = driver.findElement(By.cssSelector("[title=\"Zaloguj się do swojego konta klienta\"]"));
        login.click();


    }
    @When("user enters username and password")
    public void user_enters_username_and_password() {

        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("passwd")).sendKeys("haslo");


    }
    @When("click login button")
    public void click_login_button() {
        driver.findElement(By.id("SubmitLogin")).click();
    }
    @Then("user is login")
    public void user_is_login() {
        Assert.assertTrue(driver.findElement(By.id("center_column")).isDisplayed());

        driver.close();
        driver.quit();
    }



}
