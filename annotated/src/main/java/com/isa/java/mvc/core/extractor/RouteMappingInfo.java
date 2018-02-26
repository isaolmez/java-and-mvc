package com.isa.java.mvc.core.extractor;

import com.isa.java.mvc.core.common.HttpMethod;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteMappingInfo {

    private String path;
    private HttpMethod httpMethod;
    private Method handlerMethod;
    private Class<?> handler;
}
