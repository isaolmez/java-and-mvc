package com.isa.java.mvc.core.router.rules;

import javax.servlet.http.HttpServletRequest;

public class NoOpAcceptRule implements RouteRule {

    public static final NoOpAcceptRule ACCEPT = new NoOpAcceptRule();

    @Override
    public boolean test(HttpServletRequest request) {
        return true;
    }
}
