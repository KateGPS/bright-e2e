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
    private final By menuButtonLocator = By.cssSelector("share-app-header mat-toolbar-row > div:first-child .mat-button-wrapper mat-icon");
    private final By refreshButtonLocator = By.cssSelector("share-page-refresh-button .mat-icon");
    private final By createScanButtonLocator = By.cssSelector("scans-scans-toolbar button mat-icon");
    private final By filterButtonLocator = By.cssSelector("share-table-filter button");
    private final By sortButtonLocator = By.cssSelector("share-table-sort button .mat-button-wrapper span");

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
    public WebElement getMenuButton() {return driver.findElement(menuButtonLocator);}
    public WebElement getRefreshButton() {return driver.findElement(refreshButtonLocator);}
    public WebElement getCreateScanButton() {return driver.findElement(createScanButtonLocator);}
    public WebElement getFilterButton() {return driver.findElement(filterButtonLocator);}
    public WebElement getSortButton() {return driver.findElement(sortButtonLocator);}

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