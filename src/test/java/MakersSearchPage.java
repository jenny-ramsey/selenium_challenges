// in MakersSearchPage.java

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakersSearchPage {
    protected WebDriver driver;
    private By searchBoxBy = By.cssSelector(".kb-search__bar .kb-search__input");
    private By searchResultsHeadingBy = By.cssSelector("h1");

    public MakersSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://faq.makers.tech/en/knowledge");
    }

    public void searchFor(String searchTerm) {
        WebElement searchBox = driver.findElement(searchBoxBy);
        searchBox.sendKeys(searchTerm);
        searchBox.sendKeys(Keys.ENTER);
    }

    public String getSearchResultsHeading() {
        return driver.findElement(searchResultsHeadingBy).getText();
    }

    public void waitForResultsText(String resultsText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(searchResultsHeadingBy,resultsText));
    }



}
