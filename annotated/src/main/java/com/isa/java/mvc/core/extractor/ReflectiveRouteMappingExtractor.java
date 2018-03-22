package com.isa.java.mvc.core.extractor;

import static org.reflections.ReflectionUtils.withAnnotation;

import com.isa.java.mvc.core.annotation.RestRoute;
import com.isa.java.mvc.core.annotation.RouteMapping;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

public class ReflectiveRouteMappingExtractor implements RouteMappingExtractor {

    @Override
    public Set<RouteMappingInfo> getAllMappings() {
        final Set<RouteMappingInfo> allMappings = new HashSet<>();
        Reflections reflections = new Reflections("com.isa.java.mvc");
        Set<Class<?>> routes = reflections.getTypesAnnotatedWith(RestRoute.class);
        for (Class routeClass : routes) {
            allMappings.addAll(getMappingsForRoute(routeClass));
        }

        return Collections.unmodifiableSet(allMappings);
    }

    private Set<RouteMappingInfo> getMappingsForRoute(Class<?> routeClass) {
        final Set<RouteMappingInfo> mappings = new HashSet<>();
        Set<Method> routeMappingMethods = ReflectionUtils.getAllMethods(routeClass, withAnnotation(RouteMapping.class));
        for (Method routeMappingMethod : routeMappingMethods) {
            RouteMapping routeMappingAnnotation = routeMappingMethod.getDeclaredAnnotation(RouteMapping.class);
            RouteMappingInfo routeMappingInfo = RouteMappingInfo.builder()
                    .handlerMethod(routeMappingMethod)
                    .handler(routeClass)
                    .httpMethod(routeMappingAnnotation.method())
                    .paths(routeMappingAnnotation.paths())
                    .pathPatterns(routeMappingAnnotation.pathPatterns())
                    .headers(routeMappingAnnotation.headers())
                    .build();
            mappings.add(routeMappingInfo);
        }

        return mappings;
    }
}
