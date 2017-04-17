package org.songdan.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP切面
 * @author Songdan
 * @date 2017/4/17 10:17
 */
@Aspect
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class DataSourceRouteAspect {

    private static Logger logger = LoggerFactory.getLogger(DataSourceRouteAspect.class);

    @Pointcut("execution(public * *..service..*(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable{
        logger.info("设置数据源");
        //获取方法上面的注解
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        DatasourceRouteAnnotation annotation = method.getAnnotation(DatasourceRouteAnnotation.class);
        DataSources preDataSource = DataSourceManager.get();
        if(annotation != null){
            DataSourceManager.set(annotation.dataSource());
            logger.info("设置数据源为{}", annotation.dataSource());
        }
        Object object = jp.proceed();
        DataSourceManager.set(preDataSource);
        return object;
    }

}
