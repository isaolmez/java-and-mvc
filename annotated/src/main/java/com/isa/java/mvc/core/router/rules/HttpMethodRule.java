package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.model.HttpMethod;
import javax.servlet.http.HttpServletRequest;

public class HttpMethodRule implements RouteRule {

    private final HttpMethod httpMethod;

    public HttpMethodRule(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public boolean test(HttpServletRequest request) {
        HttpMethod currentHttpMethod = HttpMethod.fromValue(request.getMethod());
        return httpMethod == currentHttpMethod;
    }
}
