package com.adjust.testlibrary.util.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class Browser implements WebDriver, Serializable {

    private static final long serialVersionUID = 1L;

    private WebDriver driver;
    private BrowserType browserType;

    private Wait<WebDriver> wait;

    public Browser(WebDriver driver, BrowserType browserType) {
        this.browserType = browserType;
        this.driver = driver;

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    public Browser(WebDriver driver) {
        this(driver, BrowserType.CHROME);
    }

    public void get(String url) {
        driver.get(url);
        Waiter.waitForAjaxLoad(this);
    }

    public String getCurrentUrl() {
        Waiter.waitForAjaxLoad(this);
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        return wait.until(driver -> driver.findElement(by));
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.quit();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void scrollIntoView(WebElement element) {
        try {
            Waiter.waitForAjaxLoad(this);
            wait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception ignored) {
        }
    }

    public void sendKeys(WebElement element, String key) {
        if (null != key) {
            scrollIntoView(element);
            element.sendKeys(key);
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("value");
    }
}
