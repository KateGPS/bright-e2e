import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginFormErrorStateTest {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void LoginWithoutEmailAndPassword() {

        // Arrange
        driver.get("https://app.neuralegion.com/login");

        //Assert
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        Assert.assertEquals(submitButton.isEnabled(), false);
    }

    @Test
    public void LoginWithoutEmail() {

        // Arrange
        final String email = "catbubliktest1@yandex.ru";
        driver.get("https://app.neuralegion.com/login");

        // Act
        WebElement loginInput = driver.findElement(By.id("mat-input-0"));
        loginInput.sendKeys(email);

        // Assert
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        Assert.assertEquals(submitButton.isEnabled(), false);

    }


    @Test
    public void LoginWithoutPassword() {

        // Arrange
        final String passwordText = "f@BzSJbGX68XAwr";
        driver.get("https://app.neuralegion.com/login");

        // Act
        WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
        passwordInput.sendKeys(passwordText);

        // Assert
        WebElement submitButton = driver.findElement(By.cssSelector("auth-login-form form > button"));
        Assert.assertEquals(submitButton.isEnabled(), false);

    }

    @Test
    public void LoginWithEmailWithoutAtSymbol() {

        // Arrange
        final String email = "catbubliktest1yandex.ru";
        driver.get("https://app.neuralegion.com/login");

        // Act
        WebElement loginInput = driver.findElement(By.id("mat-input-0"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
        passwordInput.click();

        // Assert
        WebElement emailField = driver.findElement(By.cssSelector("auth-login-form form > mat-form-field .mat-form-field-outline-thick"));

        String emailFieldBorderColor = Color.fromString(emailField.getCssValue("color")).asHex();
        final String expectedBorderColor = "#995e00";
        Assert.assertEquals(emailFieldBorderColor, expectedBorderColor);
    }

    @Test
    public void LoginWithEmailWithoutAtSymbolAndDomainName() {

        // Arrange
        final String email = "catbubliktest1yu";
        driver.get("https://app.neuralegion.com/login");

        // Act
        WebElement loginInput = driver.findElement(By.id("mat-input-0"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
        passwordInput.click();

        // Assert
        WebElement emailField = driver.findElement(By.cssSelector("auth-login-form form > mat-form-field .mat-form-field-outline-thick"));

        String emailFieldBorderColor = Color.fromString(emailField.getCssValue("color")).asHex();
        final String expectedBorderColor = "#995e00";
        Assert.assertEquals(emailFieldBorderColor, expectedBorderColor);
    }
}
