package com.adjust.testlibrary.util.resource;

import java.util.HashMap;

public class HttpHeaders extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;

    public HttpHeaders() {}

    public HttpHeaders(String name, String value) {
        put(name, value);
    }

    public HttpHeaders add(String name, String value) {
        put(name, value);
        return this;
    }
}
