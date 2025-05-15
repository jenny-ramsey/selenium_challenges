import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class MakersTest {
    private static ChromeDriver driver;


    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void shouldPrintPageTitle() {
        driver.get("https://makers.tech");
        System.out.println(driver.getTitle());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}

