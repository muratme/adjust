package com.adjust.testlibrary.util;

import com.adjust.testlibrary.exception.JSONException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtils {

    private static final ObjectMapper mapper = createMapper();

    private JSONUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = (new ObjectMapper()).registerModule((Module)new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        return mapper;
    }

    public static <T> T readJSON(String content, Class<T> clazz) throws JSONException {
        try {
            return (T)mapper.readValue(content, clazz);
        } catch (Exception e) {
            throw new JSONException("JSON Parse Error", e);
        }
    }

    public static <T> String writeJSON(Object object) throws JSONException {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JSONException("JSON Parse Error", e);
        }
    }
}
