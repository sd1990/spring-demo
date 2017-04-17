package org.songdan.goods.service.impl;

import org.songdan.datasource.DataSources;
import org.songdan.datasource.DatasourceRouteAnnotation;
import org.songdan.goods.dao.IGoodsDao;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Songdan
 * @date 2017/4/12 14:02
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    @DatasourceRouteAnnotation(dataSource = DataSources.GOODS)
    @Transactional(propagation = Propagation.REQUIRED)
    public Goods save(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Override
    @DatasourceRouteAnnotation(dataSource = DataSources.GOODS)
    @Transactional(propagation = Propagation.REQUIRED)
    public void decreaseGoodsStock(String goodsId) {
        goodsDao.decreaseGoodsStock(goodsId);
    }

    @Override
    @DatasourceRouteAnnotation(dataSource = DataSources.GOODS)
    @Transactional(propagation = Propagation.SUPPORTS)
    public Goods getById(String goodsId) {
        return goodsDao.selectByGoodsId(goodsId);
    }
}
