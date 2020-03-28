package org.songdan.spring.simple.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * 延迟加载后处理器
 *
 * @author: Songdan
 * @create: 2020-02-14 15:38
 **/
@Component
public class LazyInitFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {

            if (!beanDefinitionName.equals("lazyInitFactoryPostProcessor")) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                if (beanDefinition instanceof AbstractBeanDefinition) {
                    AbstractBeanDefinition abstractBeanDefinition = ((AbstractBeanDefinition) beanDefinition);
                    if (!abstractBeanDefinition.isLazyInit()) {
                        abstractBeanDefinition.setLazyInit(true);
                    }
                }
            }
        }

    }

}
