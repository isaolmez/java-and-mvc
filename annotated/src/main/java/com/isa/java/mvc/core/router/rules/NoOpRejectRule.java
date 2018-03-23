package com.isa.java.mvc.core.router.rules;

import javax.servlet.http.HttpServletRequest;

public class NoOpRejectRule implements RouteRule {

    public static final NoOpRejectRule REJECT = new NoOpRejectRule();

    @Override
    public boolean test(HttpServletRequest request) {
        return false;
    }
}
