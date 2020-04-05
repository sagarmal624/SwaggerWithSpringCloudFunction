package com.sagarandcompany.swagger.annotation;

import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiInfo {
    String path();

    HttpMethod method();

    String tagName();

    String description() default "";
}
