package org.songdan.spring.simple.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

/**
 * @author: Songdan
 * @create: 2020-03-20 14:45
 **/
public @interface CustomBean {

    @AliasFor(annotation = Service.class)
    String value() default "";

}
