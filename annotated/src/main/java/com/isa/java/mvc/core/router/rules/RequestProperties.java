package com.isa.java.mvc.core.router.rules;

import com.isa.java.mvc.core.utils.UrlUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProperties {

    private final String method;
    private final String contextPath;
    private final String path;
    private final String[] headers;

    public static RequestProperties get(HttpServletRequest request) {
        String method = request.getMethod();
        URL requestURL = UrlUtils.getUrl(request.getRequestURL().toString());
        List<String> headerNameList = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            headerNameList.add(headerNames.nextElement());
        }

        return RequestProperties.builder()
                .method(method)
                .headers(headerNameList.toArray(new String[headerNameList.size()]))
                .contextPath(request.getContextPath())
                .path(requestURL.getPath())
                .build();
    }

    public String getPath() {
        return path.replaceFirst(contextPath, "");
    }
}
