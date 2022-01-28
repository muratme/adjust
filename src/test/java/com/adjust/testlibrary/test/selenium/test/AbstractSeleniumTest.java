package com.adjust.testlibrary.test.selenium.test;

import com.adjust.testlibrary.config.TestConfig;
import com.adjust.testlibrary.util.TestThreadContext;
import com.adjust.testlibrary.util.browser.Browser;
import com.adjust.testlibrary.util.browser.BrowserUtils;
import com.adjust.testlibrary.util.extension.WatcherExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {TestConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class AbstractSeleniumTest {

    @RegisterExtension
    @Autowired
    public WatcherExtension watcherExtension;

    @Autowired
    private BrowserUtils browserUtils;

    public Browser browser;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        browser = browserUtils.doCreateBrowser(testInfo);
        TestThreadContext.setThreadContext(browser);
        init();
    }

    @AfterEach
    public void tearDown() {
        if (null != browser)
            browser.close();
    }

    public void init() {
        //overrides by test class
    }
}
