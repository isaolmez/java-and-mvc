package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.router.matcher.PathMatcher;
import com.isa.java.mvc.core.router.matcher.PathPatternMatcher;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

public class PathPatternRule implements RouteRule {

    private final String[] pathPatterns;

    private final PathMatcher pathMatcher = new PathPatternMatcher();

    public PathPatternRule(String[] pathPatterns) {
        this.pathPatterns = pathPatterns;
    }

    @Override
    public boolean test(HttpServletRequest httpServletRequest) {
        RequestProperties requestProperties = RequestProperties.get(httpServletRequest);
        return Arrays.stream(pathPatterns)
                .anyMatch(pathPattern -> pathMatcher.matches(pathPattern, requestProperties.getPath()));
    }
}
