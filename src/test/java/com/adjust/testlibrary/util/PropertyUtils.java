package com.adjust.testlibrary.util;

import org.junit.platform.commons.util.StringUtils;

public class PropertyUtils {

    private static String getProperty(PropertyConstants propertyConstants) {
        String systemProperty = System.getProperty(propertyConstants.key);
        return StringUtils.isNotBlank(systemProperty) ? systemProperty : propertyConstants.defaultValue;
    }

    public static String getRemoteOption() {
        return getProperty(PropertyConstants.REMOTE_DRIVER);
    }

    public static String getHeadlessOption() {
        return getProperty(PropertyConstants.HEADLESS_DRIVER);
    }

    public static String getBrowserType() {
        return getProperty(PropertyConstants.BROWSER_TYPE);
    }
}

enum PropertyConstants {

    REMOTE_DRIVER("webdriver.remote.option", "false"),
    HEADLESS_DRIVER("webdriver.chrome.headless", "false"),
    BROWSER_TYPE("browser.type", "chrome");

    public String key;
    public String defaultValue;

    PropertyConstants(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
}
