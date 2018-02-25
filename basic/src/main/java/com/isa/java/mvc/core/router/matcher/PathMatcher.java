package com.isa.java.mvc.core.router.matcher;

public interface PathMatcher {
    boolean matches(String regexPattern, String target);
}
