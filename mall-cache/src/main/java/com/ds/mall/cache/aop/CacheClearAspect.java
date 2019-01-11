package com.ds.mall.cache.aop;

import com.ds.mall.cache.annotation.CacheClear;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/10 16:55
 */
@Aspect
@Component
public class CacheClearAspect {

    @Pointcut("@annotation(com.ds.mall.cache.annotation.CacheClear)")
    public void point() {
    }

    @Around("point()&&@annotation(clear)")
    public Object interceptor(ProceedingJoinPoint joinPoint, CacheClear clear) throws Throwable {
        
        return null;
    }
}
