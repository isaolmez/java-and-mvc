package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.utils.UrlUtils;
import java.net.URL;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProperties {

    private final String method;
    private final String path;
    private final String[] headers;

    public static RequestProperties get(HttpServletRequest request) {
        String method = request.getMethod();
        URL requestURL = UrlUtils.getUrl(request.getRequestURL().toString());
        String[] headers = Stream.of(request.getHeaderNames()).toArray(size -> new String[size]);

        return RequestProperties.builder()
                .method(method)
                .headers(headers)
                .path(requestURL.getPath())
                .build();
    }
}
