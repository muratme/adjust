package com.adjust.testlibrary.util.browser;

import com.adjust.testlibrary.util.PropertyUtils;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

@Component
public class CapabilityUtils
{
    private DesiredCapabilities getDesiredCapabilities(TestInfo testInfo) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        if (null != testInfo)
            capabilities.setCapability("name", testInfo.getDisplayName());

        return capabilities;
    }

    public ChromeOptions getChromeDriverOptions(TestInfo testInfo) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems

        if (PropertyUtils.getHeadlessOption().equals("true"))
            options.addArguments("--headless");

        DesiredCapabilities capabilities = getDesiredCapabilities(testInfo);
        capabilities.setBrowserName("chrome");

        options.merge(capabilities);

        return options;
    }

    public FirefoxOptions getFirefoxOptions(TestInfo testInfo) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("window-size=1920,1080");
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems

        if (PropertyUtils.getHeadlessOption().equals("true"))
            options.addArguments("--headless");

        DesiredCapabilities capabilities = getDesiredCapabilities(testInfo);
        capabilities.setBrowserName("firefox");

        options.merge(capabilities);

        return options;
    }
}
