package com.adjust.testlibrary.util;

public class Constants {

    public interface Project {
        String ADJUST = "adjust";
    }

    public interface TestType {
        String INTEGRATION = "integration";
        String SELENIUM = "selenium";
    }

    public interface JUnit {
        String SELENIUM_TEST = Project.ADJUST + "_" + TestType.SELENIUM;
        String API_TEST = Project.ADJUST + "_" + TestType.INTEGRATION;
    }

    public interface Url {
        String WIKI_BASE_URL = "https://en.wikipedia.org";
        String WIKI_SEARCH_URL = WIKI_BASE_URL + "/w/index.php?search";
    }
}
