package com.isa.java.mvc.web;

import com.isa.java.mvc.core.utils.UrlUtils;
import com.isa.java.mvc.core.handler.NoOpHttpHandler;
import com.isa.java.mvc.core.router.HttpRouter;
import com.isa.java.mvc.core.router.SimpleHttpRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class FrontControllerServlet extends HttpServlet {

    private HttpRouter httpRouter;

    @Override
    public void init() throws ServletException {
        httpRouter = SimpleHttpRouter.builder()
                .get("/echo", (req, res) -> res.getWriter().print("echo"))
                .get("/echo.*", this::handleEcho)
                .otherwise(new NoOpHttpHandler())
                .build();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpRouter.route(request, response);
    }

    private void handleEcho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        URL requestURL = UrlUtils.getUrl(request.getRequestURL().toString());
        writer.println(requestURL.getPath());
    }
}
