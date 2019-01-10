package com.ds.mall.cache;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tb
 * @date 2019/1/10 16:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoCacheConfig.class)
@Documented
@Inherited
public @interface EnableMallCache {

}
