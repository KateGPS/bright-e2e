import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ScansPage;
import utils.AppColors;

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
    public void fromLightToDarkModeSwitcherTest() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        Assert.assertTrue(scansPage.isOpened());

        //Act
        scansPage.waitForElementToBeClickableAndClick(scansPage.getAccountButton());
        scansPage.clickThemeSwitcherToggle();


        //Assert
        scansPage.blurElement(scansPage.getAccountButton());
        String scansTextColor = Color.fromString(scansPage.getScansText().getCssValue("color")).asHex();
        Assert.assertEquals(scansTextColor, AppColors.scansTextColorDarkTheme);

        String toolbarRowColor = Color.fromString(scansPage.getToolbarRow().getCssValue("backgroundColor")).asHex();
        Assert.assertEquals(toolbarRowColor, AppColors.toolbarColorDarkTheme);

        Assert.assertTrue(scansPage.isDarkThemeEnabled());
    }

    @Test (description = "From Dark to Light mode switcher test")
    public void fromDarkToLightModeSwitcherTest() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        Assert.assertTrue(scansPage.isOpened());

        scansPage.waitForElementToBeClickableAndClick(scansPage.getAccountButton());
        scansPage.clickThemeSwitcherToggle();

        //Act
        scansPage.clickThemeSwitcherToggle();

        //Assert
        scansPage.blurElement(scansPage.getAccountButton());
        String scansTextColor = Color.fromString(scansPage.getScansText().getCssValue("color")).asHex();
        Assert.assertEquals(scansTextColor, AppColors.scansTextColorLightTheme);

        String toolbarRowColor = Color.fromString(scansPage.getToolbarRow().getCssValue("backgroundColor")).asHex();
        Assert.assertEquals(toolbarRowColor, AppColors.toolbarColorLightTheme);

        Assert.assertTrue(scansPage.isLightThemeEnabled());

    }
}
