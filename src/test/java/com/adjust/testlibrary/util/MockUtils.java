package com.adjust.testlibrary.util;

import com.github.javafaker.Faker;

public class MockUtils {

    public static final Faker MOCK = new Faker();

    private MockUtils() {
        throw new IllegalStateException("Utility class");
    }
}
