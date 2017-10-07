package com.crmsys.security.annotation;

import com.crmsys.comm.ResourceConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {

    ResourceConstant value();

    boolean ignore() default false;
}
