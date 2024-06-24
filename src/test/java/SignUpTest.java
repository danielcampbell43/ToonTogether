import com.github.javafaker.Faker;
import com.makersacademy.toon_together.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SignUpTest {

    WebDriver driver;
    Faker faker;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulSignUpRedirectsToSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/login")));
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }
}
