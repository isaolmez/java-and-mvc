package com.isa.java.mvc.core.extractor;

import com.isa.java.mvc.core.model.HttpMethod;
import com.isa.java.mvc.core.router.rules.HttpHeaderRule;
import com.isa.java.mvc.core.router.rules.HttpMethodRule;
import com.isa.java.mvc.core.router.rules.PathPatternRule;
import com.isa.java.mvc.core.router.rules.PathRule;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@RequiredArgsConstructor
@Builder
public class RouteMappingInfo {

    private final HttpMethodRule httpMethodRule;
    private final PathRule pathRule;
    private final PathPatternRule pathPatternRule;
    private final HttpHeaderRule httpHeaderRule;

    public static RuleBuilder ruleBuilder() {
        return new RuleBuilder();
    }

    public static class RuleBuilder {

        private HttpMethodRule methodRule;
        private PathRule pathRule;
        private PathPatternRule pathPatternRule;
        private HttpHeaderRule headerRule;

        public RuleBuilder method(HttpMethod httpMethod) {
            if (httpMethod != null) {
                methodRule = new HttpMethodRule(httpMethod);
            } else {
                methodRule = null;
            }

            return this;
        }

        public RuleBuilder paths(String[] paths) {
            if (ArrayUtils.isNotEmpty(paths)) {
                pathRule = new PathRule(paths);
            } else {
                pathRule = null;
            }

            return this;
        }

        public RuleBuilder pathPatterns(String[] pathPatterns) {
            if (ArrayUtils.isNotEmpty(pathPatterns)) {
                pathPatternRule = new PathPatternRule(pathPatterns);
            } else {
                pathPatternRule = null;
            }

            return this;
        }

        public RuleBuilder headers(String[] headers) {
            if (ArrayUtils.isNotEmpty(headers)) {
                headerRule = new HttpHeaderRule(headers);
            } else {
                headerRule = null;
            }

            return this;
        }

        public RouteMappingInfo build() {
            return RouteMappingInfo.builder()
                    .httpMethodRule(methodRule)
                    .pathRule(pathRule)
                    .pathPatternRule(pathPatternRule)
                    .httpHeaderRule(headerRule)
                    .build();
        }
    }
}
