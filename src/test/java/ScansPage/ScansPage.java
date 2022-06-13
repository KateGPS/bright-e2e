package ScansPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScansPage {
    private final WebDriver driver;
    public ScansPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpened() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.titleIs("Scans · Bright Security"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
