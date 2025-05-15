/* Browse to https://www.metoffice.gov.uk
Locate the search box
Search for the town or city where you live
If a list of suggestions appears, select one of the items from the list
If you get an error saying that the element is not interactable or can't be located, see the tips below
On the resulting page, get and print the title of the page which includes the name of your town or city
Take a screenshot, save it as localweather.png */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class MetOfficeTest {
    public static void main(String[] args) throws Exception {
        // Set up WebDriver
        WebDriver driver = new ChromeDriver();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#ccc-recommended-settings > span")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("location-search-input")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("location-search-input")).sendKeys("sheff");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[data-result-index=\"0\"]")).click();
        Thread.sleep(2000);

        // Print the title
            System.out.println("Page title: " + driver.getTitle());

            // Take screenshot
            takeScreenshot(driver, "localweather.png");
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
