import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TodoTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    // in TodoTest.java

    @Test
    void shouldLoadHomepage() {
        driver.get("https://todomvc.com");
        WebElement githubButton = driver.findElement(By.linkText("View on GitHub"));
        // Get the text content of the specified element
        System.out.println(githubButton.getText());


    }

    @Test
    void shouldFindDojoLink() {
        driver.get("https://todomvc.com");
        WebElement dojoLink = driver.findElement(By.linkText("Dojo"));
        // Get the text content of the specified element
        System.out.println("Found link: " + dojoLink.getText());

    }

    @Test
    void shouldFindReactLink() {
        driver.get("https://todomvc.com");
        WebElement dojoLink = driver.findElement(By.partialLinkText("React"));
        // Get the text content of the specified element
        System.out.println("Found link: " + dojoLink.getText());

    }



    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
