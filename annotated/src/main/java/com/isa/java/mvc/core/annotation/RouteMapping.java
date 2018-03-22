package com.isa.java.mvc.core.annotation;

import com.isa.java.mvc.core.model.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RouteMapping {

    HttpMethod method() default HttpMethod.GET;

    String[] paths() default {};

    String[] pathPatterns() default {};

    String[] headers() default {};
}
