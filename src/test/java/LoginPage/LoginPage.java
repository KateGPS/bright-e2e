package LoginPage;

import ScansPage.ScansPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By emailFieldLocator = By.id("mat-input-0");
    private final By passwordFieldLocator = By.id("mat-input-1");
    private final By signInBtnLocator = By.cssSelector("auth-login-form form > button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://app.neuralegion.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    public void setEmail(String userName) {
        driver.findElement(emailFieldLocator).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    public ScansPage clickSignInButton() {
        driver.findElement(signInBtnLocator).click();
        return new ScansPage(driver);
    }

    public boolean isOpened() {
        return driver.findElement(By.cssSelector("mat-card-title.mat-card-title")).getText().equals("Sign in");
    }
}