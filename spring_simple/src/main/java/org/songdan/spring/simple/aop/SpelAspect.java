package org.songdan.spring.simple.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.songdan.spring.simple.Spel;
import org.songdan.spring.simple.SpelParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2019-09-10 11:38
 **/
@Aspect
@Component
public class SpelAspect {


    private SpelParser spelParser = new SpelParser();


    @Pointcut("@annotation(org.songdan.spring.simple.Spel)")
    public void checkPoint() {
    }

    @Around("checkPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature pointSignature = (MethodSignature) joinPoint.getSignature();
        Method method = pointSignature.getMethod();
        String[] parameterNames = pointSignature.getParameterNames();
        Object[] parameterArgs = joinPoint.getArgs();
        Map<String, Object> map = new HashMap<>(parameterNames.length);
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            Object arg = parameterArgs[i];
            map.put(parameterName, arg);
        }
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariables(map);
        Spel spel = method.getAnnotation(Spel.class);
        if (spelParser.parse(spel.express()).getValue(evaluationContext, Boolean.class)) {
            System.out.println("spel express pass");
        }
        return joinPoint.proceed();
    }

}
