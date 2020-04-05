package com.sagarandcompany.swagger.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Header {
    String name();

    boolean required();
}
