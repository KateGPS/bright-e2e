package LoginPage;

import ScansPage.ScansPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By emailFieldInputLocator = By.id("mat-input-0");
    private final By emailFieldOutlineLocator = By.cssSelector("auth-login-form form > mat-form-field .mat-form-field-outline-thick");
    private final By passwordFieldInputLocator = By.id("mat-input-1");
    private final By signInBtnLocator = By.cssSelector("auth-login-form form > button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://app.neuralegion.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    public void setEmail(String userName) {
        driver.findElement(emailFieldInputLocator).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordFieldInputLocator).sendKeys(password);
    }

    public WebElement getSigninButton(){
        return driver.findElement(signInBtnLocator);
    }

    public WebElement getEmailFieldOutline() {
        return driver.findElement(emailFieldOutlineLocator);
    }

    public void blurEmailField() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].blur();", driver.findElement(emailFieldInputLocator));
    }

    public void blurPasswordField() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].blur();", driver.findElement(passwordFieldInputLocator));
    }

    public ScansPage clickSignInButton() {
        driver.findElement(signInBtnLocator).click();
        return new ScansPage(driver);
    }

    public boolean isOpened() {
        return driver.findElement(By.cssSelector("mat-card-title.mat-card-title")).getText().equals("Sign in");
    }


}