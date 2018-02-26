package com.isa.java.mvc.core.utils;

import com.isa.java.mvc.core.extractor.ReflectiveRouteMappingExtractor;
import com.isa.java.mvc.core.extractor.RouteMappingExtractor;
import com.isa.java.mvc.core.extractor.RouteMappingInfo;
import com.isa.java.mvc.core.handler.HttpHandler;
import com.isa.java.mvc.core.router.HttpRouter;
import com.isa.java.mvc.core.router.SimpleHttpRouter;
import com.isa.java.mvc.core.router.SimpleHttpRouter.Builder;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class RouteUtils {

    public static HttpRouter getRouter() {
        RouteMappingExtractor routeMappingExtractor = new ReflectiveRouteMappingExtractor();
        Set<RouteMappingInfo> allMappings = routeMappingExtractor.getAllMappings();
        Builder routeBuilder = SimpleHttpRouter.builder();
        for (RouteMappingInfo mapping : allMappings) {
            routeBuilder.when(mapping.getHttpMethod(), mapping.getPath(), new HttpHandler() {

                @Override
                public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
                    try {
                        Object handler = mapping.getHandler().newInstance();
                        Method handlerMethod = mapping.getHandlerMethod();
                        Object result = handlerMethod.invoke(handler);
                        response.getWriter().println(result);
                    } catch (InstantiationException e) {
                        log.error("Error occurred", e);
                    } catch (IllegalAccessException e) {
                        log.error("Error occurred", e);
                    } catch (InvocationTargetException e) {
                        log.error("Error occurred", e);
                    }
                }
            });
        }

        return routeBuilder.build();
    }
}
