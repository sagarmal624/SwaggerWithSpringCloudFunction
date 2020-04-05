package com.sagarandcompany.swagger.annotation;

import com.sagarandcompany.swagger.util.SwaggerInType;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Params {
    String name() default "";

    SwaggerInType in();

    String dataType();

    String description() default "";

    boolean required() default false;

    Class<? extends Object> payloadClass() default Object.class;
}
