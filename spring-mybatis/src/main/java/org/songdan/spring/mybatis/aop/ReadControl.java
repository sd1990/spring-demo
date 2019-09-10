package org.songdan.spring.mybatis.aop;

import org.songdan.spring.mybatis.convertor.Convertor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ReadControl {

    Class<? extends Convertor> convertor();

    Class targetBeanCls();

}
