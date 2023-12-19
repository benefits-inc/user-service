package com.benefits.userservice.common.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface Business {
    @AliasFor(annotation = Service.class)
    String value() default "";
}
