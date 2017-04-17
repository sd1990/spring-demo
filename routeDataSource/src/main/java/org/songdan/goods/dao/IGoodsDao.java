package org.songdan.goods.dao;

import org.songdan.goods.model.Goods;

/**
 * @author Songdan
 * @date 2017/4/12 14:00
 */
public interface IGoodsDao {

    Goods insert(Goods goods);

    void decreaseGoodsStock(String goodsId);

    Goods selectByGoodsId(String goodsId);
}
