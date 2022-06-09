package LoginPage;

import ScansPage.ScansPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class LoginPage {

    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://app.neuralegion.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    private final By emailField = By.id("mat-input-0");
    private final By passwordField = By.id("mat-input-1");
    private final By signInBtn = By.cssSelector("auth-login-form form > button");

    public void setEmail(String userName) {
        driver.findElement(emailField).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public ScansPage clickSignInButton() {
        driver.findElement(signInBtn).click();
        return new ScansPage(driver);
    }

    public boolean atPage() {
        return driver.findElement(By.cssSelector("mat-card-title.mat-card-title")).getText().equals("Sign in");
    }
}
