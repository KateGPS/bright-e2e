import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class StylesCheck {

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
        final String email = "catbubliktest1@yandex.ru";
        final String passwordText = "f@BzSJbGX68XAwr";

        WebElement loginInput = driver.findElement(By.id("mat-input-0"));
        WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
        loginInput.sendKeys(email);
        passwordInput.sendKeys(passwordText);
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        submitButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("scans-scans-toolbar button:nth-of-type(1)")));

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void CheckMenuButtonColor() {

        // Arrange

        //Act
        WebElement menuButton = driver.findElement(By.cssSelector("share-app-header mat-toolbar-row > div:first-child .mat-button-wrapper mat-icon"));
        String menuButtonColor = Color.fromString(menuButton.getCssValue("color")).asHex();
        final String expectedMenuButtonColor = "#07737d";

        //Assert
        Assert.assertEquals(menuButtonColor, expectedMenuButtonColor);

    }

    @Test
    public void CheckRefreshButtonColor() {

        // Arrange

        //Act
        WebElement refreshButton = driver.findElement(By.cssSelector("share-page-refresh-button .mat-icon"));
        String RefreshButtonColor = Color.fromString(refreshButton.getCssValue("color")).asHex();
        final String expectedRefreshButtonColor = "#07737d";

        //Assert
        Assert.assertEquals(RefreshButtonColor, expectedRefreshButtonColor);

    }

    @Test
    public void CheckScanButtonColor() {

        // Arrange

        //Act
        WebElement createScanButton = driver.findElement(By.cssSelector("scans-scans-toolbar button mat-icon"));
        String createScanButtonColor = Color.fromString(createScanButton.getCssValue("color")).asHex();
        final String expectedCreateScanButtonColor = "#07737d";

        //Assert
        Assert.assertEquals(createScanButtonColor, expectedCreateScanButtonColor);

    }

    @Test
    public void CheckFilterButtonColor() {

        // Arrange

        //Act
        WebElement filterButton = driver.findElement(By.cssSelector("share-table-filter button"));
        String filterButtonColor = Color.fromString(filterButton.getCssValue("color")).asHex();
        final String expectedFilterButtonColor = "#07737d";

        //Assert
        Assert.assertEquals(filterButtonColor, expectedFilterButtonColor);

    }

    @Test
    public void CheckSortButtonColor() {

        // Arrange

        //Act
        WebElement sortButton = driver.findElement(By.cssSelector("share-table-sort button .mat-button-wrapper span"));
        String sortButtonColor = Color.fromString(sortButton.getCssValue("color")).asHex();
        final String expectedSortButtonColor = "#07737d";

        //Assert
        Assert.assertEquals(sortButtonColor, expectedSortButtonColor);


    }
}


