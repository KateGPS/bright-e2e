package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class ScansPage extends BasePage {
    public ScansPage(WebDriver driver) {
        super(driver, "https://app.neuralegion.com/scans");
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
