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

public class StylesCheck {

    private WebDriver driver;

    @BeforeSuite
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened());

        loginPage.setEmail("catbubliktest1@yandex.ru");
        loginPage.setPassword("f@BzSJbGX68XAwr");
        ScansPage scansPage = loginPage.clickSignInButton();

        Assert.assertTrue(scansPage.isOpened());
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void CheckMenuButtonColor() {

        // Arrange
        ScansPage scansPage = new ScansPage(driver);

        //Act
        String menuButtonColor = Color.fromString(scansPage.getMenuButton().getCssValue("color")).asHex();

        //Assert
        Assert.assertEquals(menuButtonColor, AppColors.menuButtonColor);

    }

    @Test
    public void CheckRefreshButtonColor() {

        // Arrange
        ScansPage scansPage = new ScansPage(driver);

        //Act
        String RefreshButtonColor = Color.fromString(scansPage.getRefreshButton().getCssValue("color")).asHex();

        //Assert
        Assert.assertEquals(RefreshButtonColor, AppColors.refreshButtonColor);

    }

    @Test
    public void CheckScanButtonColor() {

        // Arrange
        ScansPage scansPage = new ScansPage(driver);

        //Act
        String createScanButtonColor = Color.fromString(scansPage.getCreateScanButton().getCssValue("color")).asHex();

        //Assert
        Assert.assertEquals(createScanButtonColor, AppColors.createScanButtonColor);

    }

    @Test
    public void CheckFilterButtonColor() {

        // Arrange
        ScansPage scansPage = new ScansPage(driver);

        //Act
        String filterButtonColor = Color.fromString(scansPage.getFilterButton().getCssValue("color")).asHex();

        //Assert
        Assert.assertEquals(filterButtonColor, AppColors.filterButtonColor);

    }

    @Test
    public void CheckSortButtonColor() {

        // Arrange
        ScansPage scansPage = new ScansPage(driver);

        //Act
        String sortButtonColor = Color.fromString(scansPage.getSortButton().getCssValue("color")).asHex();

        //Assert
        Assert.assertEquals(sortButtonColor, AppColors.sortButtonColor);
    }
}


