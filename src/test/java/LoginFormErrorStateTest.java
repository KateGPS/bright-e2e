import pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.AppColors;

import java.time.Duration;

public class LoginFormErrorStateTest {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void LoginWithoutEmailAndPassword() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        //Assert
        Assert.assertEquals(loginPage.getSigninButton().isEnabled(), false);
    }

    @Test
    public void LoginWithoutEmail() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setPassword("f@BzSJbGX68XAwr");
        loginPage.blurElement(loginPage.passwordFieldInput());

        // Assert
        Assert.assertEquals(loginPage.getSigninButton().isEnabled(), false);
    }

    @Test
    public void LoginWithoutPassword() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.blurElement(loginPage.emailFieldInput());

        // Assert
        Assert.assertEquals(loginPage.getSigninButton().isEnabled(), false);
    }

    @Test
    public void LoginWithEmailWithoutAtSymbol() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setEmail("catbubliktest1yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");


        // Assert
        String emailFieldOutlineColor = Color.fromString(loginPage.getEmailFieldOutline().getCssValue("color")).asHex();
        Assert.assertEquals(emailFieldOutlineColor, AppColors.errorColor);

        Assert.assertEquals(loginPage.getSigninButton().isEnabled(), false);
    }

        @Test
        public void LoginWithEmailWithoutAtSymbolAndDomainName () {

            // Arrange
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            Assert.assertTrue(loginPage.isOpened());

            // Act
            loginPage.setEmail("catbubliktest1ru");
            loginPage.setPassword("f@BzSJbGX68XAwr");

            // Assert
            String emailFieldOutlineColor = Color.fromString(loginPage.getEmailFieldOutline().getCssValue("color")).asHex();
            Assert.assertEquals(emailFieldOutlineColor, AppColors.errorColor);

            Assert.assertEquals(loginPage.getSigninButton().isEnabled(), false);
        }

    }