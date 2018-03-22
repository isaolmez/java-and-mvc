package com.isa.java.mvc.core.router;


import com.isa.java.mvc.core.extractor.RouteMappingInfo;
import com.isa.java.mvc.core.handler.HttpHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHttpRouter implements HttpRouter {

    private final List<RouteMappingInfo> routeMappingInfos;
    private final HttpHandler defaultHandler;

    SimpleHttpRouter(List<RouteMappingInfo> routeMappingInfos, HttpHandler defaultHandler) {
        this.routeMappingInfos = routeMappingInfos != null ? routeMappingInfos : new ArrayList<>();
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void route(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Routing the request...");
        RouteMappingInfo routeMappingInfo = findMatchingMappingInfo(request);
//        httpHandler.handle(request, response);
    }

    private RouteMappingInfo findMatchingMappingInfo(HttpServletRequest httpServletRequest) {
        for (RouteMappingInfo routeMappingInfo : routeMappingInfos) {
            boolean matches = true;
            if (!routeMappingInfo.getHttpMethodRule().test(httpServletRequest)) {
                matches = false;
            }

            if (!routeMappingInfo.getHttpMethodRule().test(httpServletRequest)) {
                matches = false;
            }

            if (!routeMappingInfo.getPathPatternRule().test(httpServletRequest)) {
                matches = false;
            }

            if (routeMappingInfo.getPathRule().test(httpServletRequest)) {
                matches = false;
            }

            if (matches) {
                return routeMappingInfo;
            }
        }

        return null;
    }
}
