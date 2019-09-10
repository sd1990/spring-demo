package org.songdan.spring.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    public static Object getBean(String id) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(id);
    }

    public static <T> T getBean(Class<T> clazz) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }

        return ctx.getBean(clazz);
    }

    /**
     * 装配self manager object
     * @param existingBean 需要被装配的对象
     */
    public static void autowireBean(Object existingBean) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }

        ctx.getAutowireCapableBeanFactory().autowireBean(existingBean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationcontext)
            throws BeansException {
        ctx = applicationcontext;
    }

}
