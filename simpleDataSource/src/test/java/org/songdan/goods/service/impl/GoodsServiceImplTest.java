package org.songdan.goods.service.impl;

import org.junit.Test;
import org.songdan.base.JunitBase;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class GoodsServiceImplTest extends JunitBase {

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testSave() throws Exception {
        Goods goods = new Goods();
        goods.setId("g0000002");
        goods.setGoodsName("香蕉");
        goods.setPrice(new BigDecimal("2.99"));
        goodsService.save(goods);
    }
}
