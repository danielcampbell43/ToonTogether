import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PlaylistPageTest {
    WebDriver driver;
    Faker faker;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        driver.get("http://localhost:8080/playlists");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCreatedByAndTimeStamp() {
        List<WebElement> playlists = driver.findElements(By.cssSelector(".playlists"));
        for (WebElement playlist : playlists) {
            WebElement ownerName = playlist.findElement(By.cssSelector(".owner-name"));
            String ownerText = ownerName.getText();
            assertTrue("Owner text does not start with 'Created by'", ownerText.startsWith("Created by"));

            WebElement timestamp = playlist.findElement(By.cssSelector(".timestamp"));
            String timestampText = timestamp.getText();
            assertTrue("Timestamp format is incorrect", timestampText.matches(".* at \\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}:\\d{2}"));
        }
    }
}

