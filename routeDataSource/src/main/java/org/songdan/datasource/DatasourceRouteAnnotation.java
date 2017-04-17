package org.songdan.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库路由注解
 * @author Songdan
 * @date 2017/4/17 10:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DatasourceRouteAnnotation {

    DataSources dataSource() default DataSources.CUSTOMER;

}
