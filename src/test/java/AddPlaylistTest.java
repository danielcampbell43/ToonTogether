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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AddPlaylistTest {
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
        driver.quit();
    }

    @Test
    public void successfulAddPlaylist() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String playlistName = faker.pokemon().name();
        driver.findElement(By.id("playlist-name")).sendKeys(playlistName);
        driver.findElement(By.id("playlist-name-submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr/td[contains(text(), '" + playlistName + "')]")));
        List<WebElement> listItems = driver.findElements(By.xpath("//table/tbody/tr"));
        boolean playlistFound = false;
        for (WebElement listItem : listItems) {
            WebElement playlistNameElement = listItem.findElement(By.xpath(".//td[1]"));
            if (playlistNameElement.getText().equals(playlistName)) {
                playlistFound = true;
                break;
            }
        }

        Assert.assertTrue("Playlist name not found in the playlist table", playlistFound);
    }
}