package com.isa.java.mvc.core.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(404, "Not found!");
    }
}
