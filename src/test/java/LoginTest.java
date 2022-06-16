import pages.LoginPage;
import pages.ScansPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    @AfterMethod
    public void closeBrowser() {
        this.driver.quit();
    }

    @Test
    public void successfulLoginTest() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        // Assert
        Assert.assertTrue(scansPage.isOpened());
    }

    @Test
    public void wrongEmailLoginTest() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setEmail("catbubliktest@yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        // Assert
        Assert.assertFalse(scansPage.isOpened());

    }

    @Test
    public void wrongPasswordLoginTest() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        // Act
        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.setPassword("f@BzSJAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        // Assert
        Assert.assertFalse(scansPage.isOpened());

    }
}

