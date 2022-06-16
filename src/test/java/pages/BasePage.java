package pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final String url;

    public BasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void open() {
        driver.get(url);
    }
    public void blurElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].blur()", element);
    }

    public void waitForElementToBeClickableAndClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public abstract boolean isOpened();
}

