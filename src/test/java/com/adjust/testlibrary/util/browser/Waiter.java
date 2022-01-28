package com.adjust.testlibrary.util.browser;

import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private static final String DOCUMENT_READY_STATE_SCRIPT = "return document.readyState";
    private static final String AJAX_WAIT_SCRIPT = "return typeof jQuery != 'undefined' && jQuery.active != 0";

    private Waiter() {
        throw new IllegalStateException("Utility class");
    }

    public static void waitForAjaxLoad(Browser browser)
    {
        waitForReadyState(browser, AJAX_WAIT_SCRIPT);
    }

    private static void waitForReadyState(Browser browser, String script) {
        try {
            sleep(250L);
            JavascriptExecutor executor = (JavascriptExecutor) browser.getDriver();
            boolean stillRunningAjax = Boolean.parseBoolean(executor.executeScript(script).toString());
            boolean isDocumentReadyState = executor.executeScript(DOCUMENT_READY_STATE_SCRIPT).equals("complete");

            int i = 0;
            while (!isDocumentReadyState && stillRunningAjax && i < 2) {
                i++;
                sleep(TimeUnit.SECONDS.toMillis(1L));
                stillRunningAjax = Boolean.parseBoolean(executor.executeScript(script).toString());
            }
        } catch (Exception e) {
            sleep(1000);
        }

    }

    public static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
