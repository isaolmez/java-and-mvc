package com.isa.java.mvc.core.utils;

import java.net.MalformedURLException;
import java.net.URL;

public final class UrlUtils {
    public static URL getUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
