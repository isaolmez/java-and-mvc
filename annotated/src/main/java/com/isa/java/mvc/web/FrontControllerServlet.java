package com.isa.java.mvc.web;

import com.isa.java.mvc.core.router.HttpRouter;
import com.isa.java.mvc.core.utils.RouteUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrontControllerServlet extends HttpServlet {

    private HttpRouter httpRouter;

    @Override
    public void init() throws ServletException {
        log.info("Initializing Http router...");
        httpRouter = RouteUtils.getRouter();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpRouter.route(request, response);
    }
}
