package org.songdan.spring.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Songdan
 * @create: 2019-04-19 16:43
 **/
public abstract class JsonTypeHandler<T> implements TypeHandler<T> {

    private Class<? extends T> typeParameterCls;

    public JsonTypeHandler() {
        typeParameterCls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String param = null;
        try {
            param = objectMapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ps.setString(i, param);
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(rs.getString(columnName), typeParameterCls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getResult(ResultSet rs, int columnIndex) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(rs.getString(columnIndex), typeParameterCls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(cs.getString(columnIndex), typeParameterCls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
