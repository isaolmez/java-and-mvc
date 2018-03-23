package com.isa.java.mvc.core.router;


import com.isa.java.mvc.core.handler.HttpHandler;
import com.isa.java.mvc.core.router.registry.MappingRegistration;
import com.isa.java.mvc.core.router.registry.RouteMappingRegistry;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHttpRouter implements HttpRouter {

    private final RouteMappingRegistry routeMappingRegistry;
    private final HttpHandler defaultHandler;

    public SimpleHttpRouter(RouteMappingRegistry routeMappingRegistry, HttpHandler defaultHandler) {
        this.routeMappingRegistry = routeMappingRegistry;
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void route(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Routing the request...");
        MappingRegistration mappingRegistration = findMatchingMappingInfo(request);
        if (mappingRegistration == null) {
            defaultHandler.handle(request, response);
        } else {
            ((HttpHandler) (request1, response1) -> {
                Object result = mappingRegistration.getHandlerMethod().invoke();
                response1.getWriter().print(result);
            }).handle(request, response);
        }
    }

    private MappingRegistration findMatchingMappingInfo(HttpServletRequest httpServletRequest) {
        return routeMappingRegistry.getMappingRegistration(httpServletRequest);
    }
}
