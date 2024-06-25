import com.github.javafaker.Faker;
import com.makersacademy.toon_together.Application;
import com.makersacademy.toon_together.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LogInTest {
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
    public void successfulLoginRedirectsToHomepage() {
        String details = faker.name().firstName();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(details);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
        driver.findElement(By.id("username")).sendKeys(details);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        WebElement header = driver.findElement(By.tagName("p"));
        String titleAsStr = header.getText();
        Assert.assertEquals("Create a playlist", titleAsStr);
    }
}




