package com.isa.java.mvc.core.router.registry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappingRegistration {

    public static MappingRegistration of(Handler handler, HandlerMethod handlerMethod) {
        return new MappingRegistration(handler, handlerMethod);
    }

    private Handler handler;
    private HandlerMethod handlerMethod;
}
