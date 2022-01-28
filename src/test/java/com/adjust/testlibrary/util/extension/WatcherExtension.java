package com.adjust.testlibrary.util.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class WatcherExtension implements TestWatcher {

    private static final Logger log = LoggerFactory.getLogger(WatcherExtension.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("SUCCESS [" + context.getDisplayName() + "]");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("FAILED [" + context.getDisplayName() + "]");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("ABORTED [" + context.getDisplayName() + "]");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("DISABLED [" + context.getDisplayName() + "]");
    }
}
