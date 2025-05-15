import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class HelloMakers {
    public static void main(String[] args) throws Exception{

        // Use WebDriver to open a new instance of ChromeDriver
        // Comment out the ChromeDriver line:
// WebDriver driver = new ChromeDriver();
// ... and replace with the below
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();

        // Instruct the driver to browse to the Makers website
        driver.get("https://makers.tech");

        // Take a screenshot of what's currently on the page,
        // and store it in a file 'makers.png' in your project root
        takeScreenshot(driver, "makers.png");

        // Find the title of the webpage (the value inside the HTML
        // <title> element) and print it to the terminal
        System.out.println(driver.getTitle());

        // Close down Selenium and end the test
        driver.quit();
    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}
