package com.isa.java.mvc.core.router;


import com.isa.java.mvc.core.common.HttpMethod;
import com.isa.java.mvc.core.handler.HttpHandler;
import com.isa.java.mvc.core.handler.NoOpHttpHandler;
import com.isa.java.mvc.core.router.rules.PathPatternMatchRule;
import com.isa.java.mvc.core.router.rules.RouteRule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkArgument;

public class SimpleHttpRouter implements HttpRouter {
    private final ConcurrentMap<HttpMethod, ConcurrentMap<RouteRule, HttpHandler>> routes;
    private final HttpHandler defaultHandler;

    SimpleHttpRouter(ConcurrentMap<HttpMethod, ConcurrentMap<RouteRule, HttpHandler>> routes,
                     HttpHandler defaultHandler) {
        this.routes = routes != null ? routes : new ConcurrentHashMap<>();
        this.defaultHandler = defaultHandler;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void route(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpHandler httpHandler = findMatchingHandler(request);
        httpHandler.handle(request, response);
    }

    private HttpHandler findMatchingHandler(HttpServletRequest httpServletRequest) {
        HttpMethod httpMethod = HttpMethod.valueOf(httpServletRequest.getMethod());
        ConcurrentMap<RouteRule, HttpHandler> methodRoutes = routes.get(httpMethod);
        if (methodRoutes != null) {
            for (RouteRule routeRule : methodRoutes.keySet()) {
                if (routeRule.test(httpServletRequest)) {
                    return methodRoutes.get(routeRule);
                }
            }
        }

        return defaultHandler;
    }

    public static class Builder {
        private ConcurrentMap<HttpMethod, ConcurrentMap<RouteRule, HttpHandler>> routes = new ConcurrentHashMap<>();
        private HttpHandler defaultHandler = new NoOpHttpHandler();

        public Builder get(String pathPattern, HttpHandler httpHandler) {
            checkPathPatternRegistration(pathPattern, httpHandler);

            RouteRule routeRule = new PathPatternMatchRule(pathPattern);
            addRoute(HttpMethod.GET, routeRule, httpHandler);
            return this;
        }

        public Builder get(RouteRule routeRule, HttpHandler httpHandler) {
            checkRouteRuleRegistration(routeRule, httpHandler);

            addRoute(HttpMethod.GET, routeRule, httpHandler);
            return this;
        }

        public Builder post(String pathPattern, HttpHandler httpHandler) {
            checkPathPatternRegistration(pathPattern, httpHandler);

            RouteRule routeRule = new PathPatternMatchRule(pathPattern);
            addRoute(HttpMethod.POST, routeRule, httpHandler);
            return this;
        }

        public Builder post(RouteRule routeRule, HttpHandler httpHandler) {
            checkRouteRuleRegistration(routeRule, httpHandler);

            addRoute(HttpMethod.POST, routeRule, httpHandler);
            return this;
        }

        public Builder otherwise(HttpHandler httpHandler) {
            this.defaultHandler = httpHandler;
            return this;
        }

        private void checkPathPatternRegistration(String pathPattern, HttpHandler httpHandler) {
            checkArgument(pathPattern != null, "Path pattern should be set");
            checkArgument(httpHandler != null, "Handler should be set");
        }

        private void checkRouteRuleRegistration(RouteRule routeRule, HttpHandler httpHandler) {
            checkArgument(routeRule != null, "Rule should be set");
            checkArgument(httpHandler != null, "Handler should be set");
        }

        private void addRoute(HttpMethod httpMethod, RouteRule routeRule, HttpHandler httpHandler) {
            ConcurrentMap<RouteRule, HttpHandler> methodRoutes
                    = routes.compute(httpMethod, (key, value) -> value != null ? value : new ConcurrentHashMap<>());
            methodRoutes.put(routeRule, httpHandler);
        }

        public HttpRouter build() {
            checkArgument(defaultHandler != null, "Default handler should be set!");

            return new SimpleHttpRouter(routes, defaultHandler);
        }
    }
}
