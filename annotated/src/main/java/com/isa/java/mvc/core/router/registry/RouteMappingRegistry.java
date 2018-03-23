package com.isa.java.mvc.core.router.registry;

import com.isa.java.mvc.core.extractor.RouteMappingInfo;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.http.HttpServletRequest;

public class RouteMappingRegistry {

    private final ConcurrentMap<RouteMappingInfo, MappingRegistration> mappingRegistry = new ConcurrentHashMap<>();

    public void registerMapping(RouteMappingInfo routeMappingInfo, MappingRegistration mappingRegistration) {
        mappingRegistry.put(routeMappingInfo, mappingRegistration);
    }

    public MappingRegistration getMappingRegistration(HttpServletRequest request) {
        for (RouteMappingInfo routeMappingInfo : mappingRegistry.keySet()) {
            boolean matches = true;
            if (routeMappingInfo.getHttpMethodRule() != null && !routeMappingInfo.getHttpMethodRule().test(request)) {
                matches = false;
            }

            if (routeMappingInfo.getHttpHeaderRule() != null && !routeMappingInfo.getHttpHeaderRule().test(request)) {
                matches = false;
            }

            if (routeMappingInfo.getPathPatternRule() != null && !routeMappingInfo.getPathPatternRule().test(request)) {
                matches = false;
            }

            if (routeMappingInfo.getPathRule() != null && !routeMappingInfo.getPathRule().test(request)) {
                matches = false;
            }

            if (matches) {
                return mappingRegistry.get(routeMappingInfo);
            }
        }

        return null;
    }
}
