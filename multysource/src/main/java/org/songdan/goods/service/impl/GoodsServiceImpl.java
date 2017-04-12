package org.songdan.goods.service.impl;

import org.songdan.goods.dao.IGoodsDao;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Songdan
 * @date 2017/4/12 14:02
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public Goods save(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Override
    public void decreaseGoodsStock(String goodsId) {
        goodsDao.decreaseGoodsStock(goodsId);
    }

    @Override
    public Goods getById(String goodsId) {
        return goodsDao.selectByGoodsId(goodsId);
    }
}
