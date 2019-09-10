package org.songdan.spring.mybatis.dao;

import org.songdan.spring.mybatis.aop.ReadControl;
import org.songdan.spring.mybatis.convertor.impl.OriginFeeConvertor;
import org.songdan.spring.mybatis.po.BizOriginFee;
import org.springframework.stereotype.Component;

@Component
public interface OriginFeeMapper {

    @ReadControl(convertor = OriginFeeConvertor.class, targetBeanCls = FeeMapper.class)
    BizOriginFee selectByBizId(int bizId);

    int insert(BizOriginFee bizOriginFee);

}
