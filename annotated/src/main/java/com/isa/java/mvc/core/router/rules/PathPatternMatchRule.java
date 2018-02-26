package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.router.matcher.PathMatcher;
import com.isa.java.mvc.core.router.matcher.PathPatternMatcher;

import javax.servlet.http.HttpServletRequest;

public class PathPatternMatchRule implements RouteRule {
    private final String pathPattern;
    private final PathMatcher pathMatcher = new PathPatternMatcher();

    public PathPatternMatchRule(String pathPattern) {
        this.pathPattern = pathPattern;
    }

    @Override
    public boolean test(HttpServletRequest httpServletRequest) {
        RequestProperties requestProperties = RequestProperties.get(httpServletRequest);
        return pathMatcher.matches(pathPattern, requestProperties.getPath());
    }
}
