import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersControllerTest {
    WebDriver driver;
    Faker faker;

    @BeforeEach
    public void setUp() {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSignupPage() {
        driver.get("http://localhost:8080/users/new");

        driver.findElement(By.id("username")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/login", currentUrl);

    }
}