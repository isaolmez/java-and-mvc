package com.isa.java.mvc.core.router.registry;

import com.isa.java.mvc.core.exception.MvcException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Handler {

    public static Handler of(Class handlerClass) {
        try {
            return new Handler(handlerClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MvcException(e);
        }
    }

    private Object handler;
}
