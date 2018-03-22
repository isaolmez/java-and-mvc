package com.isa.java.mvc.core.extractor;

import static org.reflections.ReflectionUtils.withAnnotation;

import com.isa.java.mvc.MyApplication;
import com.isa.java.mvc.core.annotation.Application;
import com.isa.java.mvc.core.annotation.RestRoute;
import com.isa.java.mvc.core.annotation.RouteMapping;
import com.isa.java.mvc.core.router.registry.Handler;
import com.isa.java.mvc.core.router.registry.HandlerMethod;
import com.isa.java.mvc.core.router.registry.MappingRegistration;
import com.isa.java.mvc.core.router.registry.RouteMappingRegistry;
import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

public class ReflectiveRouteMappingExtractor implements RouteMappingExtractor {

    @Override
    public RouteMappingRegistry initializeMappingRegistry(){
        final RouteMappingRegistry mappingRegistry = new RouteMappingRegistry();
        Application application = findApplication();
        Reflections reflections = new Reflections(application.value());
        Set<Class<?>> routes = reflections.getTypesAnnotatedWith(RestRoute.class);
        for (Class routeClass : routes) {
            getMappingsForRoute(routeClass, mappingRegistry);
        }

        return mappingRegistry;
    }

    private Application findApplication() {
        return MyApplication.class.getDeclaredAnnotation(Application.class);
    }

    private RouteMappingRegistry getMappingsForRoute(Class<?> routeClass, RouteMappingRegistry mappingRegistry) {
        Set<Method> routeMappingMethods = ReflectionUtils.getAllMethods(routeClass, withAnnotation(RouteMapping.class));
        for (Method routeMappingMethod : routeMappingMethods) {
            RouteMapping routeMappingAnnotation = routeMappingMethod.getDeclaredAnnotation(RouteMapping.class);
            RouteMappingInfo routeMappingInfo = RouteMappingInfo.ruleBuilder()
                    .method(routeMappingAnnotation.method())
                    .paths(routeMappingAnnotation.paths())
                    .pathPatterns(routeMappingAnnotation.pathPatterns())
                    .headers(routeMappingAnnotation.headers())
                    .build();
            Handler handler = Handler.of(routeClass);
            HandlerMethod handlerMethod = HandlerMethod.of(handler, routeMappingMethod);
            mappingRegistry.registerMapping(routeMappingInfo, MappingRegistration.of(handler, handlerMethod));
        }

        return mappingRegistry;
    }
}
