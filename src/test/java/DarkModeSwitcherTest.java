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

public class DarkModeSwitcherTest {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test (description = "Dark mode switcher test")
    public void fromDefaultToDarkModeSwitcherTest() {

        // Arrange
        driver.get("https://app.neuralegion.com/login");
        final String emailText = "catbubliktest1@yandex.ru";
        final String passwordText = "f@BzSJbGX68XAwr";

        WebElement emailField = driver.findElement(By.id("mat-input-0"));
        emailField.sendKeys(emailText);
        WebElement password = driver.findElement(By.id("mat-input-1"));
        password.sendKeys(passwordText);
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        submitButton.click();

        //Act
        WebElement accountButton = driver.findElement(By.cssSelector("mat-toolbar :nth-child(4)"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountButton));
        accountButton.click();
        WebElement themeSwitcher = driver.findElement(By.cssSelector("share-theme-switcher mat-slide-toggle"));
        themeSwitcher.click();

        //Assert
        WebElement overlayContainer = driver.findElement(By.className("cdk-overlay-container"));
        overlayContainer.click();

        WebElement scansText = driver.findElement(By.cssSelector("share-app-header share-breadcrumb"));
        String scansTextColor = Color.fromString(scansText.getCssValue("color")).asHex();
        final String expectedScansTextColor = "#f2f4f5";
        Assert.assertEquals(scansTextColor, expectedScansTextColor);

        WebElement toolbar = driver.findElement(By.cssSelector("share-app-header mat-toolbar"));
        String toolbarColor = Color.fromString(toolbar.getCssValue("backgroundColor")).asHex();
        final String expectedToolbarColor = "#1e2528";
        Assert.assertEquals(toolbarColor, expectedToolbarColor);

        String bodyClassAttribute = driver.findElement(By.tagName("body")).getAttribute("class");
        Assert.assertTrue(bodyClassAttribute.contains("theme-dark"));
    }

    @Test (description = "From Dark to Default mode switcher test")
    public void fromDarkToDefaultModeSwitcherTest() {

        // Arrange
        driver.get("https://app.neuralegion.com/login");
        final String emailText = "catbubliktest1@yandex.ru";
        final String passwordText = "f@BzSJbGX68XAwr";

        WebElement emailField = driver.findElement(By.id("mat-input-0"));
        emailField.sendKeys(emailText);
        WebElement password = driver.findElement(By.id("mat-input-1"));
        password.sendKeys(passwordText);
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        submitButton.click();

        WebElement accountButton = driver.findElement(By.cssSelector("mat-toolbar :nth-child(4)"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountButton));
        accountButton.click();
        WebElement themeSwitcher = driver.findElement(By.cssSelector("share-theme-switcher mat-slide-toggle"));
        themeSwitcher.click();

        //Act
        themeSwitcher.click();

        //Assert
        WebElement overlayContainer = driver.findElement(By.className("cdk-overlay-container"));
        overlayContainer.click();

        WebElement scansText = driver.findElement(By.cssSelector("share-app-header share-breadcrumb"));
        String scansTextColor = Color.fromString(scansText.getCssValue("color")).asHex();
        final String expectedScansTextColor = "#131719";
        Assert.assertEquals(scansTextColor, expectedScansTextColor);

        WebElement toolbar = driver.findElement(By.cssSelector("share-app-header mat-toolbar"));
        String toolbarColor = Color.fromString(toolbar.getCssValue("backgroundColor")).asHex();
        final String expectedToolbarColor = "#ffffff";
        Assert.assertEquals(toolbarColor, expectedToolbarColor);

        String bodyClassAttribute = driver.findElement(By.tagName("body")).getAttribute("class");
        Assert.assertTrue(bodyClassAttribute.contains("theme-default"));
    }
}
