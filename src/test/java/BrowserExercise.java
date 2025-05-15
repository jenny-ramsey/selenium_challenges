import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class BrowserExercise {
    public static void main(String[] args) throws Exception{

        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("https://www.whatismybrowser.com/");
        takeScreenshot(firefoxDriver, "screenshot_firefox.png");
        System.out.println("Firefox screenshot saved as screenshot_firefox.png");
        firefoxDriver.quit();

        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.whatismybrowser.com/");
        takeScreenshot(chromeDriver, "screenshot_chrome.png");
        System.out.println("Chrome screenshot saved as screenshot_firefox.png");
        firefoxDriver.quit();

    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}

