package com.isa.java.mvc.core.model;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    private static Map<String, HttpMethod> valueToEnum;

    static {
        valueToEnum = new HashMap<>();
        for (HttpMethod value : values()) {
            valueToEnum.put(value.name().toLowerCase(), value);
            valueToEnum.put(value.name().toUpperCase(), value);
        }
    }

    public static HttpMethod fromValue(String value) {
        return valueToEnum.get(value);
    }
}
