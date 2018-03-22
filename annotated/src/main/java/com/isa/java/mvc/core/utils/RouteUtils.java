package com.isa.java.mvc.core.utils;

import com.isa.java.mvc.core.extractor.ReflectiveRouteMappingExtractor;
import com.isa.java.mvc.core.extractor.RouteMappingExtractor;
import com.isa.java.mvc.core.extractor.RouteMappingInfo;
import com.isa.java.mvc.core.router.HttpRouter;
import java.util.Set;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class RouteUtils {

    public static HttpRouter getRouter() {
        RouteMappingExtractor routeMappingExtractor = new ReflectiveRouteMappingExtractor();
        Set<RouteMappingInfo> allMappings = routeMappingExtractor.getAllMappings();
        return null;
    }
}
