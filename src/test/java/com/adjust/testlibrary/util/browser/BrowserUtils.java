package com.adjust.testlibrary.util.browser;

import com.adjust.testlibrary.util.PropertyUtils;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class BrowserUtils {

    @Autowired
    private CapabilityUtils capabilityUtils;

    public Browser doCreateBrowser(TestInfo testInfo) {
        WebDriver driver = createDriver(testInfo);
        setups(driver);

        return new Browser(driver);
    }

    private WebDriver createDriver(TestInfo testInfo) {
        return (PropertyUtils.getRemoteOption().equals("true")) ? createRemoteDriver(testInfo) : createLocalDriver(testInfo);
    }

    private WebDriver createLocalDriver(TestInfo testInfo) {

        if (PropertyUtils.getBrowserType().equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
            return new ChromeDriver(capabilityUtils.getChromeDriverOptions(testInfo));
        }

        if (PropertyUtils.getBrowserType().equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
            return new FirefoxDriver(capabilityUtils.getFirefoxOptions(testInfo));
        }

        throw new RuntimeException("WebDriver Instance could not be created!");
    }

    private WebDriver createRemoteDriver(TestInfo testInfo) {
        try {
            URL remoteUrl = URI.create("http://localhost:4444/wd/hub").toURL();

            if (PropertyUtils.getBrowserType().equals("firefox"))
                return new RemoteWebDriver(remoteUrl, capabilityUtils.getFirefoxOptions(testInfo));

            if (PropertyUtils.getBrowserType().equals("chrome"))
                return new RemoteWebDriver(remoteUrl, capabilityUtils.getChromeDriverOptions(testInfo));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("WebDriver Instance could not be created!");
    }


    private void setups(WebDriver driver) {
        setTimeOuts(driver);
        setMaximizedWindow(driver);
    }

    private void setMaximizedWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    private void setTimeOuts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
    }
}
