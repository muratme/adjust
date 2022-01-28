package com.adjust.testlibrary.test.selenium.test;

import com.adjust.testlibrary.test.selenium.page.SearchPage;
import com.adjust.testlibrary.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.stream.Collectors;

@Tag(Constants.JUnit.SELENIUM_TEST)
public class SearchSeleniumTest extends AbstractSeleniumTest {

    private SearchPage searchPage;
    private static final String EXISTING_SEARCH_KEYWORD = "computer science";
    private static final String NOT_EXISTING_SEARCH_KEYWORD = "hello kity";

    public void init() {
        searchPage = new SearchPage();
        browser.get(Constants.Url.WIKI_SEARCH_URL);
    }

    @Test
    public void testSearchExist() {
        browser.sendKeys(searchPage.searchText, EXISTING_SEARCH_KEYWORD);
        browser.click(searchPage.submitButton);

        String actual = browser.getText(searchPage.searchExists);
        String expected = String.format("There is a page named \"%s\" on Wikipedia", StringUtils.capitalize(EXISTING_SEARCH_KEYWORD));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchNotExist() {
        browser.sendKeys(searchPage.searchText, NOT_EXISTING_SEARCH_KEYWORD);
        browser.click(searchPage.submitButton);

        Assertions.assertTrue(browser.isDisplayed(searchPage.searchNotExist));

        String actual = browser.getText(searchPage.searchNotExist);
        String expected = String.format("The page \"%s\" does not exist. You can ask for it to be created, but consider checking the search results below to see whether the topic is already covered.", StringUtils.capitalize(NOT_EXISTING_SEARCH_KEYWORD));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testClearSearchInput() {
        browser.sendKeys(searchPage.searchText, EXISTING_SEARCH_KEYWORD);

        Assertions.assertEquals(EXISTING_SEARCH_KEYWORD, browser.getValue(searchPage.searchText));
        browser.click(searchPage.indicatorClear);

        Assertions.assertEquals("", browser.getValue(searchPage.searchText));
    }

    @Test
    public void testSearchInLabels() {
        Set<String> discussionLabels = getLabelsBy(searchPage.discussion);
        Set<String> generalHelpLabels = getLabelsBy(searchPage.generalHelp);
        Set<String> all = getLabelsBy(searchPage.all);

        Assertions.assertTrue(all.containsAll(discussionLabels));
        Assertions.assertTrue(all.containsAll(generalHelpLabels));
        Assertions.assertFalse(discussionLabels.containsAll(generalHelpLabels));
        Assertions.assertFalse(generalHelpLabels.containsAll(discussionLabels));
    }

    private Set<String> getLabelsBy(WebElement checkBox) {
        browser.get(Constants.Url.WIKI_SEARCH_URL);

        browser.click(searchPage.expandableNameSpaces);
        browser.click(checkBox);

        return searchPage.labels.stream().map(WebElement::getText).collect(Collectors.toSet());
    }
}
