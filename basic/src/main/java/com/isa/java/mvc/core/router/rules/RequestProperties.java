package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.utils.UrlUtils;
import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Data
@Builder
public class RequestProperties {
    private final String method;
    private final String path;

    public static RequestProperties get(HttpServletRequest httpServletRequest) {
        String method = httpServletRequest.getMethod();
        URL requestURL = UrlUtils.getUrl(httpServletRequest.getRequestURL().toString());

        return RequestProperties.builder()
                .method(method)
                .path(requestURL.getPath())
                .build();
    }
}
