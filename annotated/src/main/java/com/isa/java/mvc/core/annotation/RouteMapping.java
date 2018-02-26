package com.isa.java.mvc.core.annotation;

import com.isa.java.mvc.core.common.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RouteMapping {

    String path();

    HttpMethod method();
}
