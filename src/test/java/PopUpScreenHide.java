import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class PopUpScreenHide {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.get("https://app.neuralegion.com/login");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test (description = "turn off cookies and intercome")
    public void PopUpScreenHide() {

        WebElement cookiesField = driver.findElement(By.cssSelector("body .hs-cookie-notification-position-bottom"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(cookiesField));
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", cookiesField);


        WebElement intercomeApp = driver.findElement(By.cssSelector(".mat-typography .intercom-lightweight-app"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", intercomeApp);
    }
}
