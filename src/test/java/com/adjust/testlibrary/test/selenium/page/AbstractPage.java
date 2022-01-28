package com.adjust.testlibrary.test.selenium.page;

import com.adjust.testlibrary.util.TestThreadContext;
import com.adjust.testlibrary.util.browser.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.Serializable;

public class AbstractPage implements Serializable {
    private static final long serialVersionUID = 1L;

    public AbstractPage() {
        Browser browser = TestThreadContext.getThreadContext();
        PageFactory.initElements(browser, this);
    }

    @FindBy(linkText = "Üye Girişi")
    public WebElement loginButton;

    @FindBy(css = "div[data-testid='header-login-testid']")
    public WebElement loggedInHeader;
}
