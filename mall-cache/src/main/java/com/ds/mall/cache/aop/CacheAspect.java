package com.ds.mall.cache.aop;

import com.ds.mall.cache.annotation.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 缓存
 * @author tb
 * @date 2019/1/10 16:55
 */
@Aspect
@Component
public class CacheAspect {

    @Pointcut("@annotation(com.ds.mall.cache.annotation.Cache)")
    public void point() {
    }

    @Around("point()&&@annotation(cache)")
    public Object interceptor(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {

        return null;
    }
}
