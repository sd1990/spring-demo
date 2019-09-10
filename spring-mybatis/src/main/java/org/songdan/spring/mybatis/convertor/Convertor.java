package org.songdan.spring.mybatis.convertor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Convertor<T> {

    T convert(ResultSet resultSet) throws SQLException;

}
