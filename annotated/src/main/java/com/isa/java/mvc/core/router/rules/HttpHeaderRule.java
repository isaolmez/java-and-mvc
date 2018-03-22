package com.isa.java.mvc.core.router.rules;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class HttpHeaderRule implements RouteRule {

    private final String[] headers;

    public HttpHeaderRule(String[] headers) {
        this.headers = headers;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        for (String header : headers) {
            if (StringUtils.isBlank(request.getHeader(header))) {
                return false;
            }
        }

        return true;
    }
}
