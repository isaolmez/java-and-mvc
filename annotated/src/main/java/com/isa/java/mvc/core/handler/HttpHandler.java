package com.isa.java.mvc.core.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface HttpHandler {
    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
