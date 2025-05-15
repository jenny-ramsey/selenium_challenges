import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.File;
import java.util.List;


public class ToDoReactTest {

    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser () {
        driver = new ChromeDriver();
    }

    @DisplayName("Test adding todo items")
    @ParameterizedTest
    @CsvSource({
            "Buy milk",
            "Call Kathryn",
            "Finish Selenium sections",
            "Read a book",
            "Start couch to 5k",
            "Water the plants",
            "Clean the fridge",
            "Sort out garden fence",
            "Return parcels",
            "Call bank"
    })

    void addTodoItem(String todoText) {
        driver.get("https://todomvc.com/examples/react/dist/");
        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys(todoText + Keys.ENTER);

        List<WebElement> items = driver.findElements(By.cssSelector(".todo-list li"));
        boolean found = items.stream().anyMatch(item -> item.getText().equals(todoText));
        assertTrue(found, "To-do item '" + todoText + "' was not added.");
    }


    @Test
    void exploreReactFunctionality() throws Exception{
        driver.get("https://todomvc.com/examples/react/dist/");
        assertEquals("TodoMVC: React", driver.getTitle());

        WebElement todoTextArea = driver.findElement(By.id("todo-input"));
        todoTextArea.sendKeys("Buy some milk" + Keys.ENTER);
        List<WebElement> todoItems = driver.findElements(By.cssSelector("li[data-testid='todo-item']"));
        assertTrue(todoItems.size() == 1, "Only one todo item");

        WebElement todoCount = driver.findElement(By.className("todo-count"));
        assertEquals("1 item left!", todoCount.getText());

        WebElement checkBox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkBox.click();

        assertEquals("0 items left!", todoCount.getText());

        takeScreenshot(driver, "todomvcreact.png");
    }



    public static void takeScreenshot(WebDriver webdriver, String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}

