package com.ds.mall.cache.aop;

import com.ds.mall.cache.annotation.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = joinPoint.getArgs();

        return null;
    }
}
