package com.isa.java.mvc.core.controller;

import com.isa.java.mvc.core.annotation.RestRoute;
import com.isa.java.mvc.core.annotation.RouteMapping;
import com.isa.java.mvc.core.common.HttpMethod;

@RestRoute
public class TestController {

    @RouteMapping(path = "/hello", method = HttpMethod.GET)
    public String hello() {
        return "hello";
    }

    @RouteMapping(path = "/hello2", method = HttpMethod.GET)
    public String hello2() {
        return "second hello";
    }
}
