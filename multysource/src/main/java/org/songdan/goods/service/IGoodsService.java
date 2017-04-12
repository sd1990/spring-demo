package org.songdan.goods.service;

import org.songdan.goods.model.Goods;

/**
 * @author Songdan
 * @date 2017/4/12 14:01
 */
public interface IGoodsService {

    Goods save(Goods goods);

    void decreaseGoodsStock(String goodsId);

    Goods getById(String goodsId);
}
