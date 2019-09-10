package org.songdan.spring.mybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ArrayUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.songdan.spring.mybatis.SpringBeanUtil;
import org.songdan.spring.mybatis.convertor.Convertor;
import org.songdan.spring.mybatis.convertor.ConvertorHolder;
import org.songdan.spring.mybatis.convertor.impl.OriginFeeConvertor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: Songdan
 * @create: 2019-07-23 20:52
 **/
@Aspect
@Component
@Slf4j
public class DataReadControlAop {

    @Around("execution(public * org.songdan.spring.mybatis.dao.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method targetMethod = getTargetMethod(pjp);
        ReadControl readControl = getDataControl(targetMethod);
        if (readControl != null) {
            //流量灰度
            boolean isGray = true;
            if (isGray) {
                log.info("invocationBean:[{}] method:[{}] execute gray", pjp.getTarget().getClass().getName(), targetMethod.getName());
                OriginFeeConvertor originFeeConvertor = new OriginFeeConvertor();
                Class beanCls = readControl.targetBeanCls();
                Method[] declaredMethods = beanCls.getMethods();
                Method invocationMethod = null;
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.getName().equals(targetMethod.getName())
                            && ArrayUtil.equals(declaredMethod.getParameterTypes(), targetMethod.getParameterTypes())) {
                        invocationMethod = declaredMethod;
                    }
                }
                if (invocationMethod != null) {
                    Convertor convertor = ConvertorHolder.set(originFeeConvertor);
                    try {
                        Object bean = SpringBeanUtil.getBean(beanCls.getSimpleName().substring(0,1).toLowerCase()+beanCls.getSimpleName().substring(1));
                        Object[] args = pjp.getArgs();
                        log.info("invocationBean:[{}] method:[{}] execute args:[{}]", beanCls.getName(), invocationMethod.getName());
                        return invocationMethod.invoke(bean, args);
                    } finally {
                        ConvertorHolder.set(convertor);
                    }
                } else {
                    log.error("originMethod:{} can't find invocationMethod", targetMethod.getName());
                    throw new IllegalArgumentException("我也不知道想表达什么。。。。");
                }
            }

        }
        return pjp.proceed();
    }

    public ReadControl getDataControl(Method targetMethod) {
        if (targetMethod == null) {
            return null;
        }
        ReadControl annotation = targetMethod.getAnnotation(ReadControl.class);
        if (annotation != null) {
            return annotation;
        }
        return targetMethod.getDeclaringClass().getAnnotation(ReadControl.class);
    }

    public Method getTargetMethod(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        if (signature == null) return null;

        if (signature instanceof MethodSignature) {

            //检测方法级注解
            MethodSignature methodSignature = (MethodSignature) signature;
            return methodSignature.getMethod();
        }

        return null;
    }

}
