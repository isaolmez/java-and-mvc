package com.isa.java.mvc.core.router.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathPatternMatcher implements PathMatcher {
    @Override
    public boolean matches(String regexPattern, String target) {
        Pattern patternRegex = Pattern.compile(regexPattern);
        Matcher matcher = patternRegex.matcher(target);
        return matcher.matches();
    }
}
