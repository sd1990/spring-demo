package org.songdan.spring.swak.core.spring;

import org.songdan.spring.swak.annotations.SwakMethod;
import org.songdan.spring.swak.annotations.SwakTag;
import org.songdan.spring.swak.core.SwakSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.RuntimeBeanReference;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * 生成组合代理对象
 *
 * @author: Songdan
 * @create: 2020-03-28 22:13
 **/
public class SwakProxyFactoryBean<T> implements FactoryBean<T>, InitializingBean, BeanFactoryAware {

    private Class<T> proxyClass;

    private List<RuntimeBeanReference> instancesReferenceList;

    private List<T> instancesList;

    private Map<String, T> instanceMap;

    private BeanFactory beanFactory;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{proxyClass}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                SwakMethod annotation = method.getAnnotation(SwakMethod.class);
                if (method.getDeclaringClass().equals(Object.class)) {
                    return method.invoke(instancesList, args);
                }
                if (annotation == null) {
                    return method.invoke(instancesList.get(0), args);
                }
                Set<String> tags = SwakSession.getTags();
                List<T> targetList = new ArrayList<>(instancesReferenceList.size());
                for (String tag : tags) {
                    T instance = instanceMap.get(tag);
                    if (instance != null) {
                        targetList.add(instance);
                    }
                }
                //获取编排的规则，对taglist进行编排
                //需要对结果进行个性化处理，可以在配置里面配置处理的bean
//                List<Object> resultList = new ArrayList<>();
//                for (T t : targetList) {
//                    Object result = method.invoke(targetList.get(targetList.size() - 1), args);
//                    resultList.add(result);
//                }
//                return method.invoke(targetList.get(targetList.size()-1),args);
                return method.invoke(targetList.get(0),args);
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return proxyClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, T> instanceMap = new HashMap<>();
        this.instancesList = new ArrayList<>();
        for (RuntimeBeanReference runtimeBeanReference : instancesReferenceList) {
            String beanName = runtimeBeanReference.getBeanName();
            T o = beanFactory.getBean(beanName, proxyClass);
            instancesList.add(o);
            SwakTag swakTag = o.getClass().getAnnotation(SwakTag.class);
            for (String tag : swakTag.tags()) {
                instanceMap.put(tag, o);
            }
        }
        this.instanceMap = instanceMap;

    }

    public Class<T> getProxyClass() {
        return proxyClass;
    }

    public void setProxyClass(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
    }

    public List<RuntimeBeanReference> getInstancesReferenceList() {
        return instancesReferenceList;
    }

    public void setInstancesReferenceList(List<RuntimeBeanReference> instancesReferenceList) {
        this.instancesReferenceList = instancesReferenceList;
    }

    public List<T> getInstancesList() {
        return instancesList;
    }

    public void setInstancesList(List<T> instancesList) {
        this.instancesList = instancesList;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
