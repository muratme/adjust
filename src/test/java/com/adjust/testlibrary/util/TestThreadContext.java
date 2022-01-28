package com.adjust.testlibrary.util;

import com.adjust.testlibrary.util.browser.Browser;

public class TestThreadContext {

    private static final InheritableThreadLocal<Browser> threadContext = new InheritableThreadLocal<>();

    public static Browser getThreadContext() {
        return threadContext.get();
    }

    public static void setThreadContext(Browser context) {
        threadContext.set(context);
    }
}
