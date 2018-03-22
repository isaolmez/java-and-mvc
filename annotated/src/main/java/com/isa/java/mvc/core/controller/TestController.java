package com.isa.java.mvc.core.controller;

import com.isa.java.mvc.core.annotation.RestRoute;
import com.isa.java.mvc.core.annotation.RouteMapping;
import com.isa.java.mvc.core.model.HttpMethod;

@RestRoute
public class TestController {

    @RouteMapping(paths = "/hello", method = HttpMethod.GET, headers = "X-Get")
    public String helloGet() {
        return "Hello GET";
    }

    @RouteMapping(paths = "/hello", method = HttpMethod.POST, headers = "X-Post")
    public String helloPost() {
        return "Hello POST";
    }

    @RouteMapping(pathPatterns = "/hello/*", method = HttpMethod.POST)
    public String helloPostPattern() {
        return "Hello POST";
    }

    @RouteMapping(paths = "/hello", method = HttpMethod.PUT)
    public String helloPut() {
        return "Hello PUT";
    }
}
