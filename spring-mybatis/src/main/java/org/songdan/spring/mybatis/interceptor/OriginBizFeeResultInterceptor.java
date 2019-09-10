package org.songdan.spring.mybatis.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.songdan.spring.mybatis.convertor.Convertor;
import org.songdan.spring.mybatis.convertor.ConvertorHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: Songdan
 * @create: 2019-07-23 17:57
 **/
@Intercepts({@Signature(
        type = ResultSetHandler.class,
        method = "handleResultSets",
        args = {Statement.class})})
@Component
@Configuration
public class OriginBizFeeResultInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Convertor convertor = ConvertorHolder.get();
        if (convertor == null) {
            return invocation.proceed();
        }
        Object[] args = invocation.getArgs();
        // 获取到当前的Statement
        Statement stmt = (Statement) args[0];
        // 通过Statement获得当前结果集
        ResultSet resultSet = stmt.getResultSet();
        List<Object> resultList = new ArrayList<>();
        while (resultSet != null && resultSet.next()) {
            resultList.add(convertor.convert(resultSet));
        }
        return resultList;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
