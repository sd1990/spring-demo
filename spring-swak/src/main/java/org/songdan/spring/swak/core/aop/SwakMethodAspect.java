package org.songdan.spring.swak.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2020-03-28 17:16
 **/
//@Aspect
//@Component
public class SwakMethodAspect {

    @Pointcut("@annotation(org.songdan.spring.swak.annotations.SwakMethod)")
    public void checkPoint() {
    }

    @Around("checkPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
