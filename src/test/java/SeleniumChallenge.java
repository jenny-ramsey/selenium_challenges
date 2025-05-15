import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumChallenge {
    private static ChromeDriver driver;


    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void testFlowMakersWebsite() throws Exception {
        // Instruct Selenium to browse to https://makers.tech
        driver.get("https://makers.tech");
        String title = driver.getTitle();
        System.out.println(title);
        // Assert that the page title includes the text "Building the future".
        assertTrue(title.contains("Building the future"));
        Thread.sleep(2000);

        // Assert that the page has a link that contains the text "Code of Conduct".
        WebElement codeOfConductLink = driver.findElement(By.linkText("Code of Conduct"));
        Assertions.assertNotNull(codeOfConductLink);
        new Actions(driver).moveToElement(codeOfConductLink).perform();

        // Assert that if you click the "Code of Conduct" link, you are taken to the page
        // https://makers.tech/code-of-conduct and that the page title includes the text "Code of conduct".
        codeOfConductLink.click();
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();
        assertEquals("https://makers.tech/code-of-conduct", currentUrl);
        assertEquals("Code of conduct", currentTitle);
        takeScreenshot(driver, "CoC.png");

        // Browse back to the homepage.
        driver.navigate().back();
        Thread.sleep(2000);
        takeScreenshot(driver, "MakersHomePage.png");

       // Assert that if you click the "FAQ" link, you are taken to the FAQ page in a new window.
        WebElement faqLink = driver.findElement(By.linkText("FAQ"));
        new Actions(driver).moveToElement(faqLink).perform();
        faqLink.click();
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        takeScreenshot(driver, "FAQPage.png");

        // On the FAQ page, enter "badger" in the search box,
        // and confirm that the results page contains the text No results for "badger".
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys("badger");
        Thread.sleep(2000);
        takeScreenshot(driver, "FAQBadgerInput.png");
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement noResultsHeading = driver.findElement(By.cssSelector("h1.kb-search-results__heading"));
        assertEquals("No results for \"badger\"", noResultsHeading.getText());
        takeScreenshot(driver, "FAQBadgerResult.png");

    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver, String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

}
