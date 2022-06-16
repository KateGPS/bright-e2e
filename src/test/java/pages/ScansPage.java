package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ScansPage extends BasePage {

    private final By themeSwitcherToggleLocator = By.cssSelector("share-theme-switcher mat-slide-toggle");
    private final By accountButtonLocator = By.cssSelector("mat-toolbar :nth-child(4)");
    private final By overlayContainerLocator = By.className("cdk-overlay-container");
    private final By scansTextLocator = By.cssSelector("share-app-header share-breadcrumb");
    private final By toolbarRowLocator = By.cssSelector("share-app-header mat-toolbar");
    private final By bodyLocator = By.tagName("body");


    public ScansPage(WebDriver driver) {
        super(driver, "https://app.neuralegion.com/scans");
    }

    public WebElement getScansText() {
        return driver.findElement(scansTextLocator);
    }

    public WebElement getToolbarRow() {
        return driver.findElement(toolbarRowLocator);
    }

    public WebElement getAccountButton() {
        return driver.findElement(accountButtonLocator);
    }

    public void clickThemeSwitcherToggle() {
        driver.findElement(themeSwitcherToggleLocator).click();
    }

    public boolean isOpened() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.titleIs("Scans · Bright Security"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDarkThemeEnabled() {
        return driver.findElement(bodyLocator).getAttribute("class").contains("theme-dark");
    }

    public boolean isLightThemeEnabled() {
        return driver.findElement(bodyLocator).getAttribute("class").contains("theme-default");
    }

}