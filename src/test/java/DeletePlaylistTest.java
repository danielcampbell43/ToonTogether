import com.github.javafaker.Faker;
import com.makersacademy.toon_together.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DeletePlaylistTest {
    WebDriver driver;
    Faker faker;
    public String playlistName;


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
        playlistName = faker.pokemon().name();
        driver.findElement(By.id("playlist-name")).sendKeys(playlistName);
        driver.findElement(By.id("playlist-name-submit")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulDeletePlaylist() {
        // Navigate to the playlists page
        driver.get("http://localhost:8080/playlists");
        try {
            WebElement deleteButton = driver.findElement(By.xpath(".//td/form/button[text()='Delete']"));
            deleteButton.click();
        } catch (NoSuchElementException e) {
            fail("Delete button not found in playlist row");
        }
        Alert alert = driver.switchTo().alert(); // Handle the confirmation dialog (if any)
        alert.accept();
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for the page to reload after deletion
        wait.until(ExpectedConditions.urlContains("/playlists"));
        assertFalse("Playlist should not exist after deletion", // check that the playlist no longer exists in the list
                isPlaylistPresent(playlistName));
    }

    private WebElement findPlaylistRow(String playlistName) {
        List<WebElement> playlistRows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
        for (WebElement row : playlistRows) {
            WebElement playlistNameElement = row.findElement(By.xpath(".//td[@class='td-no-wrap']"));
            if (playlistNameElement.getText().equals(playlistName)) {
                return row;
            }
        }
        return null;
    }

    private boolean isPlaylistPresent(String playlistName) {
        List<WebElement> playlistRows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
        for (WebElement row : playlistRows) {
            WebElement playlistNameElement = row.findElement(By.xpath(".//td[@class='td-no-wrap']"));
            if (playlistNameElement.getText().equals(playlistName)) {
                return true;
            }
        }
        return false;
    }
}