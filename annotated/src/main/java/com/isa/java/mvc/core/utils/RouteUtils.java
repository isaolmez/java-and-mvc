package com.isa.java.mvc.core.utils;

import com.isa.java.mvc.core.extractor.ReflectiveRouteMappingExtractor;
import com.isa.java.mvc.core.extractor.RouteMappingExtractor;
import com.isa.java.mvc.core.handler.NoOpHttpHandler;
import com.isa.java.mvc.core.router.HttpRouter;
import com.isa.java.mvc.core.router.SimpleHttpRouter;
import com.isa.java.mvc.core.router.registry.RouteMappingRegistry;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class RouteUtils {

    public static HttpRouter getRouter() {
        RouteMappingExtractor routeMappingExtractor = new ReflectiveRouteMappingExtractor();
        RouteMappingRegistry mappingRegistry = routeMappingExtractor.initializeMappingRegistry();
        return new SimpleHttpRouter(mappingRegistry, new NoOpHttpHandler());
    }
}
