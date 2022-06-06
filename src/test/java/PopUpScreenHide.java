import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class PopUpScreenHide {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.get("https://app.neuralegion.com/login");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test (description = "turn off cookies")
    public void HidePopUpScreenCookieBanner() {

        //Act
        WebElement cookiesBanner = driver.findElement(By.cssSelector("body .hs-cookie-notification-position-bottom"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(cookiesBanner));
        //Assert
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", cookiesBanner);
    }

    @Test (description = "turn off intercom")
    public void HidePopUpScreenIntercomApp() {

        //Act
        WebElement intercomApp = driver.findElement(By.cssSelector(".mat-typography .intercom-lightweight-app"));

        //Assert
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", intercomApp);
    }
}
