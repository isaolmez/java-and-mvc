package com.isa.java.mvc.core.extractor;

import com.isa.java.mvc.core.model.HttpMethod;
import com.isa.java.mvc.core.router.rules.HttpHeaderRule;
import com.isa.java.mvc.core.router.rules.HttpMethodRule;
import com.isa.java.mvc.core.router.rules.PathPatternRule;
import com.isa.java.mvc.core.router.rules.PathRule;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class RouteMappingInfo {

    private final HttpMethodRule httpMethodRule;
    private final PathRule pathRule;
    private final PathPatternRule pathPatternRule;
    private final HttpHeaderRule httpHeaderRule;

    public static RuleBuilder ruleBuilder(){
        return new RuleBuilder();
    }

    public static class RuleBuilder {
        private HttpMethod method;
        private String[] paths;
        private String[] pathPatterns;
        private String[] headers;

        public RuleBuilder method(HttpMethod httpMethod) {
            this.method = httpMethod;
            return this;
        }

        public RuleBuilder paths(String[] paths) {
            this.paths = paths;
            return this;
        }

        public RuleBuilder pathPatterns(String[] pathPatterns) {
            this.pathPatterns = pathPatterns;
            return this;
        }

        public RuleBuilder headers(String[] headers) {
            this.headers = headers;
            return this;
        }

        public RouteMappingInfo build() {
            return RouteMappingInfo.builder()
                    .httpMethodRule(new HttpMethodRule(method))
                    .pathRule(new PathRule(paths))
                    .pathPatternRule(new PathPatternRule(pathPatterns))
                    .httpHeaderRule(new HttpHeaderRule(headers))
                    .build();
        }
    }
}
