import com.github.javafaker.Faker;
import com.makersacademy.toon_together.Application;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class UserProfileTest {
    WebDriver driver;
    Faker faker;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
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
    }
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void usersMostRecentPlaylistAppearsOnProfilePage() {
        String playlistName = faker.pokemon().name();
        driver.findElement(By.id("playlist-name")).sendKeys(playlistName);
        driver.findElement(By.id("playlist-name-submit")).click();
        driver.findElement(By.xpath("//*[text()='My Profile']")).click();
        List<WebElement> listItems = driver.findElements(By.className("playlists"));
        WebElement lastListItem = listItems.get(listItems.size() - 1);
        String lastPlaylistContent = lastListItem.getText();
        Assert.assertEquals(lastPlaylistContent,playlistName);
    }
}
