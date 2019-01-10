package com.ds.mall.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tb
 * @date 2019/1/10 16:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Cache {

    String key() default "";

    CacheScope scope() default CacheScope.application;

    int expire() default 720;

    String desc() default "";

    Class[] result() default Object.class;


}
