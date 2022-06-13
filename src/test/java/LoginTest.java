import LoginPage.LoginPage;
import ScansPage.ScansPage;
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




/*


        // Launch website
        driver.get("https://app.neuralegion.com/login");

        final String email = "catbubliktest1@yandex.ru";
        final String passwordText = "f@BzSJbGX68XAwr";

        // Get the WebElement corresponding to the Login and password
        WebElement login = driver.findElement(By.id("mat-input-0"));
        WebElement password = driver.findElement(By.id("mat-input-1"));

        //Fill email and password forms
        login.sendKeys(email);
        password.sendKeys(passwordText);
        System.out.println("Text Field Set");

        //Find login button
        WebElement submit = driver.findElement(By.cssSelector("auth-login-form form > button"));
        submit.click();
        System.out.println("Login Done with Click");

        // Verify Create Scan button is clickable
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("scans-scans-toolbar button:nth-of-type(1)")));
            System.out.println("Create Scan button is clickable");
        } catch (Exception e) {
            System.out.println("Create Scan button isn't clickable");
        }

        // Verify title has changed
        Assert.assertEquals(driver.getTitle(), "Scans · NeuraLegion", "title hasn't changed to “Scans · NeuraLegion”");
        System.out.println("Title has changed to “Scans · NeuraLegion”");

        // Verify top toolbar has “Scans” text
        WebElement loginElement = driver.findElement(By.cssSelector("share-app-header share-breadcrumb"));
        String loginText = loginElement.getText();
        final String expectedText = "Scans";
        Assert.assertEquals(expectedText, loginText);
*/

    }


