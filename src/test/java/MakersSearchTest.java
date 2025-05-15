// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class MakersSearchTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void shouldFindSearchResultsForJava() {
        MakersSearchPage searchPage = new MakersSearchPage(driver);
        searchPage.navigate();
        searchPage.searchFor("java");
        searchPage.waitForResultsText("Results for \"java\"");
        assertEquals("Results for \"java\"", searchPage.getSearchResultsHeading());
    }

    @Test
    public void shouldNotFindSearchResultsForBadger() {
        MakersSearchPage searchPage = new MakersSearchPage(driver);
        searchPage.navigate();
        searchPage.searchFor("badger");
        searchPage.waitForResultsText("No results for \"badger\"");
        assertEquals("No results for \"badger\"", searchPage.getSearchResultsHeading());

    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
