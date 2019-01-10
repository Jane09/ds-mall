package com.ds.mall.cache.annotation;

import com.ds.mall.cache.parser.AbstractKeyGenerator;
import com.ds.mall.cache.parser.DefaultKeyGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tb
 * @date 2019/1/10 16:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface CacheClear {

    String prefix() default "";

    String key() default "";

    String[] keys() default "";

    Class<? extends AbstractKeyGenerator> generator() default DefaultKeyGenerator.class;
}
