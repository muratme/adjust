package com.adjust.testlibrary.test.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(name= "search")
    public WebElement searchText;

    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;

    @FindBy(className = "oo-ui-indicator-clear")
    public WebElement indicatorClear;

    @FindBy(className = "mw-search-exists")
    public WebElement searchExists;

    @FindBy(className = "mw-search-createlink")
    public WebElement searchNotExist;

    //Search IN

    @FindBy(className = "mw-advancedSearch-expandablePane-namespaces")
    public WebElement expandableNameSpaces;

    @FindBy(css = "input[value='defaultNamespaces']")
    public WebElement defaultNamespaces;

    @FindBy(css = "input[value='discussion']")
    public WebElement discussion;

    @FindBy(css = "input[value='generalHelp']")
    public WebElement generalHelp;

    @FindBy(css = "input[value='all']")
    public WebElement all;

    @FindBy(css = ".oo-ui-tagMultiselectWidget-group>.oo-ui-tagItemWidget>.oo-ui-labelElement-label")
    public List<WebElement> labels;
}
