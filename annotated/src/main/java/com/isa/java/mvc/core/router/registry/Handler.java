package com.isa.java.mvc.core.router.registry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Handler {

    public static Handler of(Object handler) {
        return new Handler(handler);
    }

    private Object handler;
}
