package com.isa.java.mvc.core.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface HttpRouter {
    void route(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
