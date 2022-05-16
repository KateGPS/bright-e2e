import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void openBrowser() {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    @AfterTest
    public void closeBrowser() {
        this.driver.quit();
    }

    @Test
    public void Login() {
        // Launch website
        driver.get("https://app.neuralegion.com/login");

        // Get the WebElement corresponding to the Login
        WebElement login = driver.findElement(By.id("mat-input-0"));

        // Get the WebElement corresponding to the Password
        WebElement password = driver.findElement(By.id("mat-input-1"));

        login.sendKeys("catbubliktest1@yandex.ru");
        password.sendKeys("f@BzSJbGX68XAwr");
        System.out.println("Text Field Set");

        //Find login button
        WebElement submit = driver.findElement(By.cssSelector("auth-login-form form > button"));
        submit.click();
        System.out.println("Login Done with Click");

        // Verify Create Scan button is clickable
        try {
            WebElement buttonSc = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("ng-component mat-toolbar-row button")));
            System.out.println("Create Scan button is clickable");
        } catch (Exception e) {
            System.out.println("Create Scan button isn't clickable");
        }

        // Verify title has changed
        Assert.assertEquals(driver.getTitle(), "Scans · NeuraLegion", "title hasn't changed to “Scans · NeuraLegion”");
        System.out.println("title has changed to “Scans · NeuraLegion”");

        // Verify top toolbar has “Scans” text
        WebElement loginElement = driver.findElement(By.cssSelector("mat-toolbar-row share-breadcrumb span"));
        String loginText = loginElement.getText();
        String expectedText = "Scans";

        if (loginText.equals(expectedText)) {
            System.out.println("toolbar has “Scans” text");
        } else {
            System.out.println("toolbar has no “Scans” text");
        }

    }
}


