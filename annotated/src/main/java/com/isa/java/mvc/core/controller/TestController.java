package com.isa.java.mvc.core.controller;

import com.isa.java.mvc.core.annotation.RestRoute;
import com.isa.java.mvc.core.annotation.RouteMapping;
import com.isa.java.mvc.core.model.HttpMethod;

@RestRoute
public class TestController {

    @RouteMapping(paths = "/hello", method = HttpMethod.GET)
    public String helloGet() {
        return "helloGet";
    }

    @RouteMapping(paths = "/hello", method = HttpMethod.GET, headers = "X-Get")
    public String helloGetWithHeader() {
        return "helloGetWithHeader";
    }

    @RouteMapping(paths = "/hello", method = HttpMethod.POST, headers = "X-Post")
    public String helloPost() {
        return "helloPost";
    }

    @RouteMapping(pathPatterns = "/hello/*", method = HttpMethod.POST)
    public String helloPostPattern() {
        return "helloPostPattern";
    }

    @RouteMapping(paths = "/hello", method = HttpMethod.PUT)
    public String helloPut() {
        return "helloPut";
    }
}
