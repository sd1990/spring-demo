package org.songdan.spring.mybatis.convertor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songdan.spring.mybatis.convertor.Convertor;
import org.songdan.spring.mybatis.po.BizOriginFee;
import org.songdan.spring.mybatis.po.Fee;
import org.songdan.spring.mybatis.po.OriginFee;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Songdan
 * @create: 2019-07-23 19:03
 **/
public class OriginFeeConvertor implements Convertor<BizOriginFee> {

    @Override
    public BizOriginFee convert(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int bizId = resultSet.getInt("biz_id");
        int mode = resultSet.getInt("mode");
        String data = resultSet.getString("data");
        ObjectMapper objectMapper = new ObjectMapper();
        BizOriginFee bizOriginFee = new BizOriginFee();
        try {
            Fee fee = objectMapper.readValue(data, Fee.class);
            OriginFee originFee = fee.getOriginFee();
            BeanUtils.copyProperties(originFee, bizOriginFee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bizOriginFee.setId(id);
        bizOriginFee.setBizId(bizId);
        bizOriginFee.setMode(mode);
        return bizOriginFee;
    }
}
