package com.isa.java.mvc.core.router.rules;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class PathRule implements RouteRule {

    private final String[] paths;

    public PathRule(String[] paths) {
        this.paths = paths;
    }

    @Override
    public boolean test(HttpServletRequest httpServletRequest) {
        RequestProperties requestProperties = RequestProperties.get(httpServletRequest);
        return Arrays.stream(paths)
                .anyMatch(path -> StringUtils.equals(path, requestProperties.getPath()));
    }
}
