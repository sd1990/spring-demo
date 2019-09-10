package org.songdan.spring.mybatis.dao;

import org.songdan.spring.mybatis.po.BizOriginFee;
import org.springframework.stereotype.Component;

@Component
public interface FeeMapper extends OriginFeeMapper{

    BizOriginFee selectByBizId(int bizId);

    int insert(BizOriginFee bizOriginFee);

}
