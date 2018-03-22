package com.isa.java.mvc.core.router.registry;

import com.isa.java.mvc.core.exception.MvcException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlerMethod {

    public static HandlerMethod of(Handler handler, Method method) {
        return new HandlerMethod(handler, method);
    }

    private Handler handler;

    private Method method;

    public Object invoke() throws MvcException {
        try {
            return method.invoke(handler);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MvcException(e);
        }
    }
}
